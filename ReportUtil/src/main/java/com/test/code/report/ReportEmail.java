package com.test.code.report;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.test.code.extract.DataExtractor;
import com.test.code.util.DateUtil;
import com.test.code.util.PropertiesUtil;

public class ReportEmail {

	public static String getEmailContent() {
		Date startDate = DateUtil.getDateBeforeNDays(new Date(),30);
		Date endDate=new Date();
		StringBuilder sb = new StringBuilder();
		sb.append(getHtmlData(DataExtractor.getData(DataExtractor.updateQueryWithDates(PropertiesUtil.getProperty("CATEGORY_EXPENSE"),startDate,endDate)),"#edf3f5;","#d0ecf5;"));
		appendTag(sb,"table border=\"1\" align=\"center\"");
		sb.insert(0,"<div style=\"font-family: 'Calibri'\"> <div> ");
		sb.insert(0, "<h2 style=\"text-align: center; font-family: 'Calibri'\"> Expense Report of last 30 days</h2>");
		sb.append("</div>");
		StringBuilder sb1 = new StringBuilder();
		sb1.append(getHtmlData(DataExtractor.getData(DataExtractor.updateQueryWithDates(PropertiesUtil.getProperty("PAYMENT_TYPE_EXPENSE"),startDate,endDate)),"#f5f5eb;","#f0f0d8;"));
		appendTag(sb1,"table border=\"1\" align=\"center\"");
		sb1.insert(0,"<div style=\"margin-top:10px; \">");
		sb1.append("</div>");
		sb1.append("</div>");
		//sb1.append("<p/>");
		//StringBuilder sb2 = new StringBuilder();
		//sb2.append(getHtmlData(DataExtractor.getData(DataExtractor.updateQueryWithDates(PropertiesUtil.getProperty("CARD_TYPE_EXPENSE"),startDate,endDate))));
		//appendTag(sb2,"table border=\"1\"");
		//return sb.toString()+sb1.toString()+sb2.toString();
		return sb.toString()+sb1.toString();
	}
	public static void sendEmailReport(){

		// Recipient's email ID needs to be mentioned.
		String to = PropertiesUtil.getProperty("mailRecipientAddress");

		// Sender's email ID needs to be mentioned
		String from = PropertiesUtil.getProperty("mailUser");

		// Assuming you are sending email from through gmails smtp
		String host = "smtp.gmail.com";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// Get the Session object.// and pass username and password
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(PropertiesUtil.getProperty("mailUser"), PropertiesUtil.getProperty("HOME_EXPENSE",true));

			}

		});

		// Used to debug SMTP issues
		session.setDebug(true);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipients(Message.RecipientType.BCC, InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject(PropertiesUtil.getProperty("mailSubject"));

			// Send the actual HTML message.
			String emailContent=getEmailContent();
			message.setContent(emailContent,"text/html");

			System.out.println("sending..."+emailContent);
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}

	public static String getHtmlData(List<List<Object>> data,String color1, String color2){
		int rowCount=0;
		StringBuilder sb = new StringBuilder();
		StringBuilder sbTemp = null;
		DecimalFormat df = new DecimalFormat("0.00");
		df.setMaximumFractionDigits(2);
		for (List<Object> aBook : data) {
			sbTemp =new StringBuilder();
			for (Object field : aBook) {
				String tempColumns="";
				boolean rightAligned=false;
				if (field instanceof String) {
					tempColumns+=(String) field;
				} else if (field instanceof Integer) {
					tempColumns+=(Integer) field;
					rightAligned=true;
				} else if (field instanceof Double) {
					tempColumns+=((Double) field);
					rightAligned=true;
				} else if (field instanceof Date) {
					tempColumns+=((Date) field);
				}else if (field instanceof BigDecimal) {
					tempColumns+=df.format(((BigDecimal) field).doubleValue());
					rightAligned=true;
				}
				if(rowCount==0){
					appendTagwithContent(sbTemp,"th",tempColumns);
				}else{
					appendTagwithContent(sbTemp,(rightAligned)?"td align=\"right\"":"td",tempColumns);
				}
			}
			if(rowCount%2==0)
				appendTagwithContent(sb,"tr style=\"background-color: "+color1+"\"",sbTemp.toString());
			else
				appendTagwithContent(sb,"tr style=\"background-color: "+color2+"\"",sbTemp.toString());
			rowCount++;
		}

		return sb.toString();
	}
	static void appendTagwithContent(StringBuilder sb, String tag, String contents) {
		sb.append('<').append(tag).append('>');
		sb.append(contents);
		sb.append("</").append(tag.split(" ")[0]).append('>');
	}
	static void appendTag(StringBuilder sb,String tag) {
		sb.insert(0, ">");
		sb.insert(0, tag);
		sb.insert(0, "<");
		sb.append("</").append(tag.split(" ")[0]).append('>');
	}

}

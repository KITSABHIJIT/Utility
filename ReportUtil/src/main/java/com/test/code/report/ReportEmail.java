package com.test.code.report;

import java.math.BigDecimal;
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
		appendTagwithContent(sb, "h2", "Expense Report of last 30 days");
		sb.append("<p/>");
		sb.append("<p/>");
		sb.append(getHtmlData(DataExtractor.getData(DataExtractor.updateQueryWithDates(PropertiesUtil.getProperty("CATEGORY_EXPENSE"),startDate,endDate))));
		appendTag(sb,"table border=\"1\"");
		sb.append("<p/>");
		sb.append("<p/>");
		StringBuilder sb1 = new StringBuilder();
		sb1.append(getHtmlData(DataExtractor.getData(DataExtractor.updateQueryWithDates(PropertiesUtil.getProperty("PAYMENT_TYPE_EXPENSE"),startDate,endDate))));
		appendTag(sb1,"table border=\"1\"");
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
			message.setContent(getEmailContent(),"text/html");

			System.out.println("sending...");
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}

	public static String getHtmlData(List<List<Object>> data){
		int rowCount=0;
		StringBuilder sb = new StringBuilder();
		StringBuilder sbTemp = null;
		for (List<Object> aBook : data) {
			sbTemp =new StringBuilder();
			for (Object field : aBook) {
				String tempColumns="";
				if (field instanceof String) {
					tempColumns+=(String) field;
				} else if (field instanceof Integer) {
					tempColumns+=(Integer) field;
				} else if (field instanceof Double) {
					tempColumns+=((Double) field);
				} else if (field instanceof Date) {
					tempColumns+=((Date) field);
				}else if (field instanceof BigDecimal) {
					tempColumns+=(((BigDecimal) field).doubleValue());
				}
				if(rowCount==0){
					appendTagwithContent(sbTemp,"th",tempColumns);
				}else{
					appendTagwithContent(sbTemp,"td",tempColumns);
				}
			}
			appendTagwithContent(sb,"tr",sbTemp.toString());
			rowCount++;
		}

		return sb.toString();
	}
	static void appendTagwithContent(StringBuilder sb, String tag, String contents) {
		sb.append('<').append(tag).append('>');
		sb.append(contents);
		sb.append("</").append(tag).append('>');
	}
	static void appendTag(StringBuilder sb,String tag) {
		sb.insert(0, ">");
		sb.insert(0, tag);
		sb.insert(0, "<");
		sb.append("</").append(tag).append('>');
	}

}

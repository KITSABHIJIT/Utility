package com.myutility.code;

import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailUtil {

	public static void main(String ... args){
		EmailUtil.sendEmail(false,null);
		//EmailUtil.sendEmail(true,"/app/payserv/settapps/rspsender/rspsender/reports/SettlementReport.pdf");
	}

	public static void sendEmail(boolean attachment,String filePath){
		System.out.println("Sending Email:");
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", PropertiesUtil.getProperty("mail.host"));
		Session session = Session.getDefaultInstance(properties);
		try {
			String recipients = PropertiesUtil.getProperty("mail.recipients");	 

			ArrayList recipientsArray=new ArrayList();
			StringTokenizer st = new StringTokenizer(recipients,",");
			while (st.hasMoreTokens()) {
				recipientsArray.add(st.nextToken());
			}

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(PropertiesUtil.getProperty("mail.sender")));
			int sizeTo=recipientsArray.size();
			InternetAddress[] addressTo = new InternetAddress[sizeTo];
			for (int i = 0; i < sizeTo; i++)
			{
				addressTo[i] = new InternetAddress(recipientsArray.get(i).toString()) ;
			}
			message.setRecipients(Message.RecipientType.TO, addressTo);
			message.setSentDate(new Date()); 
			message.setSubject(PropertiesUtil.getProperty("mail.subject"));
			System.out.println("Mail sending to: "+recipientsArray.toString());
			System.out.println("Mail sending from: "+PropertiesUtil.getProperty("mail.sender"));

			MimeBodyPart messagePart = new MimeBodyPart();
			/*messagePart.setContent("<h3>Ha ha ha<h3>"
					+ "<p><img src=\"http://discovermagazine.com/~/media/import/images/b/e/b/chimpmedia.jpg\" alt=\"Smiley face\">"
, "text/html; charset=utf-8");*/
			
			messagePart.setContent("", "text/html; charset=utf-8");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messagePart);
			if(attachment){
				MimeBodyPart attachmentPart = new MimeBodyPart();
				FileDataSource fileDataSource = new FileDataSource(filePath) {
					@Override
					public String getContentType() {
						return "application/octet-stream";
					}
				};
				attachmentPart.setDataHandler(new DataHandler(fileDataSource));
				attachmentPart.setFileName(fileDataSource.getName());
				multipart.addBodyPart(attachmentPart);
				System.out.println("Attachment added: "+fileDataSource.getName());
			}
			message.setContent(multipart);

			Transport.send(message);
			System.out.println("Mail send succussfully");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}

package com.staples.kafka.log.feeder;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.staples.kafka.log.util.PropertiesUtil;

public class TestNewRelic {
	public static void main(String ...strings ) {
		PropertiesUtil.loadProperties("./config/application.properties");
		String input=null;
		String messageStr="2018-11-09 10:08:07,136 [pool-1-thread-12] ERROR (MonerisService.java:2856) - Exception occured in executing procedure AUTHRESVAL :: check The error log table PRCERLOG EXCEPTION";
		//String messageStr="Test Message";
		
		try {
			URL url = new URL(PropertiesUtil.getProperty("relic.host"));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", PropertiesUtil.getProperty("relic.content.type"));
			conn.addRequestProperty("X-Insert-Key", PropertiesUtil.getProperty("relic.key"));
			//conn.setRequestProperty("Content-Encoding", PropertiesUtil.getProperty("relic.content.encoding"));
			messageStr=messageStr.replaceAll("\"", "'");
			input = "{"
					+ "\"eventType\":\"EnterprisePaymentService\","
					+ "\"application\":\"Authorization\","					
					+ "\"jobName\":\"CA_CANRSP1\","
					+ "\"logLevel\":\"response error\"\","
					+ "\"LogFile\":\"ResponseError.log\","
					//+ "\"LogMessage\":\""+URLEncoder.encode(messageStr, PropertiesUtil.getProperty("relic.content.encoding"))
					+ "\"LogMessage\":\""+messageStr
					+"\"}";
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED 
					&& conn.getResponseCode() != HttpURLConnection.HTTP_OK
					&& conn.getResponseCode() != HttpURLConnection.HTTP_ACCEPTED) {
				System.out.println("Failed : HTTP error code : "+ conn.getResponseCode()+" Message: "+input);
			}else {
				System.out.println("Message sent to Relic : (" +  messageStr + ")");
			}
			/*BufferedReader br = new BufferedReader(new InputStreamReader(
						(conn.getInputStream())));
				String output;
				System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					System.out.println(output);
				}*/
			conn.disconnect();
		} catch (IOException e) {
			System.out.println("Message: "+input);
		}
	}
}

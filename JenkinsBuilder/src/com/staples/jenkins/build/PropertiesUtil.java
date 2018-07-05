package com.staples.jenkins.build;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesUtil {
	private final static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
	private static Properties properties = null;
	private static final String filePath = "./config/config.txt";
	public static final String inputDataPath = "./config/inputData.txt";
	
	private static synchronized void loadProperties() {

		if (properties == null) {	
			InputStream is = null;
			try {
				properties = new Properties();
				InputStream in= new FileInputStream(filePath);
				properties.load(in);
			} catch (FileNotFoundException fnfEx) {
				logger.error("Properties file " + filePath
						+ " could not be found");
				fnfEx.printStackTrace();
			} catch (IOException ioEx) {
				logger.error("I/O error while opening Properties file " +filePath);
				ioEx.printStackTrace();
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException ioEx) {
						logger.error("Failed to close InputStream object");
						ioEx.printStackTrace();
					}
				}
			}
		} 
	} 
	
	public static String getProperty(String propertyName) {
		
		loadProperties();
		String propertyValue = properties.getProperty(propertyName);
		if (propertyValue == null) {
			propertyValue = "NULL";
		}
		return propertyValue;
	}
	
	public static void setProperty(String propertyKey, String propertyValue) {
		
		loadProperties();
		if(propertyKey!=null && propertyValue!=null)
		 properties.setProperty(propertyKey, propertyValue);
	}
	

}

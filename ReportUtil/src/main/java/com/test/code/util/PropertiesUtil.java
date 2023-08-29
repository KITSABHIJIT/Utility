package com.test.code.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	private static Properties properties = null;
	private static final String filePath = "./config/config.txt";
	private static Properties properties1 = null;
	private static final String filePath1 = "/Users/abhijit/homeExpenseConfig";
	
	private static synchronized void loadProperties() {

		if (properties == null || properties1 == null) {	
			InputStream is = null;
			try {
				properties = new Properties();
				InputStream in= new FileInputStream(filePath);
				properties.load(in);
				
				properties1 = new Properties();
				InputStream in1= new FileInputStream(filePath1);
				properties1.load(in1);
			} catch (FileNotFoundException fnfEx) {
				System.out.println("Properties file " + filePath
						+ " could not be found");
				fnfEx.printStackTrace();
			} catch (IOException ioEx) {
				System.out.println("I/O error while opening Properties file " +filePath);
				ioEx.printStackTrace();
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException ioEx) {
						System.out.println("Failed to close InputStream object");
						ioEx.printStackTrace();
					}
				}
			}
		} 
	} 

	public static String getProperty(String propertyName) {
		
		loadProperties();
		String propertyValue = properties.getProperty(propertyName);
		return propertyValue;
	}
	
	public static void setProperty(String propertyKey, String propertyValue) {
		
		loadProperties();
		if(propertyKey!=null && propertyValue!=null)
		 properties.setProperty(propertyKey, propertyValue);
	}
	
public static String getProperty(String propertyName, Boolean configTrue) {
		
		loadProperties();
		String propertyValue = properties1.getProperty(propertyName);
		return propertyValue;
	}
	
	public static void setProperty(String propertyKey, String propertyValue, Boolean configTrue) {
		
		loadProperties();
		if(propertyKey!=null && propertyValue!=null)
		 properties1.setProperty(propertyKey, propertyValue);
	}

}

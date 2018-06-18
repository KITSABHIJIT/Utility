package com.test.code;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	private static Properties properties = null;
	private static final String filePath = "application.properties";
	
	private static synchronized void loadProperties() {

		if (properties == null) {	
			InputStream is = null;
			try {
				properties = new Properties();
				ClassLoader classLoader = ProcessUtil.class.getClassLoader();
				is = classLoader.getResourceAsStream(filePath);
				properties = new Properties();
				properties.load(is);
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

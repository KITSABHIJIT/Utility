package com.staples.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.staples.main.MQDetail;

public class PropertiesUtil {
	private static final Logger LOG = LoggerFactory.getLogger(PropertiesUtil.class);
	private static Properties properties = null;
	private static final String filePath = "./config/application.properties";
	private static final String mqfilePath = "./config/MQList.properties";
	private static List<MQDetail> mqDetails;
	public static synchronized void loadProperties() {

		if (properties == null) {	
			InputStream is = null;
			try {
				properties = new Properties();
				InputStream in= new FileInputStream(filePath);
				properties.load(in);
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
		String propertyValue = properties.getProperty(propertyName);
		if (propertyValue == null) {
			propertyValue = "NULL";
		}
		return propertyValue;
	}

	public static void setProperty(String propertyKey, String propertyValue) {
		if(propertyKey!=null && propertyValue!=null)
			properties.setProperty(propertyKey, propertyValue);
	}

	public static synchronized void loadMqDetails() {
		try {
			final List<String> mqData= FileUtil.readFromFile(mqfilePath);
			mqDetails=new ArrayList<MQDetail>();
			for(String obj : mqData) {
				if(null!=obj && obj.indexOf("|")>0) {
					String [] tempObj=obj.split("[|]");
					MQDetail mqDetail=new MQDetail();
					mqDetail.setMqName(tempObj[0]);
					mqDetail.setApplication(tempObj[1]);
					mqDetail.setIfsPath((tempObj.length>2)?tempObj[2]:null);
					mqDetail.setJobName((tempObj.length>3)?tempObj[3]:null);
					mqDetails.add(mqDetail);
				}
			}
		} catch(Exception e) {
			LOG.error("Error loading MQ config", e);
		}
	}
	
	public static List<MQDetail> getMQDetails(){
		return mqDetails;
	}
}

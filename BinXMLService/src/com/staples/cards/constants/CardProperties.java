package com.staples.cards.constants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.staples.cards.exception.ServiceErrorCodes;
import com.staples.cards.exception.ServiceException;
import com.staples.cards.exception.ServiceExceptionFactory;
import com.staples.cards.logger.CardLogger;

public class CardProperties {
	private static Properties props = null;
	private static File configFile;
	public static String 
	// XML node Details
	XML_CARD_VALUE_SEPERATOR,
	XML_CARD_RECORD_SEPERATOR,
	XML_CARD_LOWER_PREFIX_APPENDER,
	XML_CARD_UPPER_PREFIX_APPENDER,
	XML_CARD_NODE_VALUE,
	XML_CARD_INFO_START_TAG,
	XML_CARD_INFO_END_TAG,
	XML_CARD_START_TAG,
	XML_CARD_END_TAG,
	
	GET_CARDINFO_PREPAID_QUERY,
	GET_CARDINFO_CREDIT_QUERY,
	OUTPUT_FILE_PREPAID,
	OUTPUT_FILE_CREDIT,
	OUTPUT_FILE_DEST_DIR,
	DATABASE_SERVER,
	DATABASE_LIBRARY,
	DATABASE_USER,
	DATABASE_PWD,
	
	AMEX_CARD_CLASS_NAME,
	AMEX_PREPAID_BIN,
	AMEX_CREDIT_BIN,
	STAPLES_CREDIT_CARD_CLASS_NAME,
	STAPLES_CREDIT_CARD_BIN,
	STAPLES_OPEN_CREDIT_CARD_CLASS_NAME,
	STAPLES_OPEN_CREDIT_CARD_BIN,
	CONVENIENCE_CREDIT_CARD_CLASS_NAME,
	CONVENIENCE_CREDIT_CARD_BIN,
	STATIC_CARD_SUB_CLASS_PREPAID,
	STATIC_CARD_SUB_CLASS_CREDIT,
	STATIC_PROVIDER,
	STATIC_COUNTRY,
	STATIC_STAPLES_CARD_LENGTH,
	STATIC_AM_CC_CARD_LENGTH;
	
	public static int RECORD_PER_NODE;
	

	public static void initProperties(String cardPropertiesFile) throws ServiceException{

		configFile = new File(cardPropertiesFile);
		InputStream in;
		try {
			in = new FileInputStream(configFile);
			props = new Properties();
			props.load(in);
			/* IFS path for text file*/
			XML_CARD_VALUE_SEPERATOR=loadPropertyAndLog("XML_CARD_VALUE_SEPERATOR");
			XML_CARD_RECORD_SEPERATOR=loadPropertyAndLog("XML_CARD_RECORD_SEPERATOR");
			XML_CARD_LOWER_PREFIX_APPENDER=loadPropertyAndLog("XML_CARD_LOWER_PREFIX_APPENDER");
			XML_CARD_UPPER_PREFIX_APPENDER=loadPropertyAndLog("XML_CARD_UPPER_PREFIX_APPENDER");
			XML_CARD_NODE_VALUE=loadPropertyAndLog("XML_CARD_NODE_VALUE");
			XML_CARD_INFO_START_TAG=loadPropertyAndLog("XML_CARD_INFO_START_TAG");
			XML_CARD_INFO_END_TAG=loadPropertyAndLog("XML_CARD_INFO_END_TAG");
			XML_CARD_START_TAG=loadPropertyAndLog("XML_CARD_START_TAG");
			XML_CARD_END_TAG=loadPropertyAndLog("XML_CARD_END_TAG");
			GET_CARDINFO_PREPAID_QUERY=loadPropertyAndLog("GET_CARDINFO_PREPAID_QUERY");
			GET_CARDINFO_CREDIT_QUERY=loadPropertyAndLog("GET_CARDINFO_CREDIT_QUERY");
			OUTPUT_FILE_PREPAID=loadPropertyAndLog("OUTPUT_FILE_PREPAID");
			OUTPUT_FILE_CREDIT=loadPropertyAndLog("OUTPUT_FILE_CREDIT");
			OUTPUT_FILE_DEST_DIR=loadPropertyAndLog("OUTPUT_FILE_DEST_DIR");
			DATABASE_SERVER=loadPropertyAndLog("DATABASE_SERVER");
			DATABASE_LIBRARY=loadPropertyAndLog("DATABASE_LIBRARY");
			DATABASE_USER=loadProperty("DATABASE_USER");
			DATABASE_PWD=loadProperty("DATABASE_PWD");
			AMEX_CARD_CLASS_NAME=loadPropertyAndLog("AMEX_CARD_CLASS_NAME");
			AMEX_PREPAID_BIN=loadPropertyAndLog("AMEX_PREPAID_BIN");
			AMEX_CREDIT_BIN=loadPropertyAndLog("AMEX_CREDIT_BIN");
			STAPLES_CREDIT_CARD_CLASS_NAME=loadPropertyAndLog("STAPLES_CREDIT_CARD_CLASS_NAME");
			STAPLES_CREDIT_CARD_BIN=loadPropertyAndLog("STAPLES_CREDIT_CARD_BIN");
			STAPLES_OPEN_CREDIT_CARD_CLASS_NAME=loadPropertyAndLog("STAPLES_OPEN_CREDIT_CARD_CLASS_NAME");
			STAPLES_OPEN_CREDIT_CARD_BIN=loadPropertyAndLog("STAPLES_OPEN_CREDIT_CARD_BIN");
			CONVENIENCE_CREDIT_CARD_CLASS_NAME=loadPropertyAndLog("CONVENIENCE_CREDIT_CARD_CLASS_NAME");
			CONVENIENCE_CREDIT_CARD_BIN=loadPropertyAndLog("CONVENIENCE_CREDIT_CARD_BIN");
			STATIC_CARD_SUB_CLASS_PREPAID=loadPropertyAndLog("STATIC_CARD_SUB_CLASS_PREPAID");
			STATIC_CARD_SUB_CLASS_CREDIT=loadPropertyAndLog("STATIC_CARD_SUB_CLASS_CREDIT");
			STATIC_PROVIDER=loadPropertyAndLog("STATIC_PROVIDER");
			STATIC_COUNTRY=loadPropertyAndLog("STATIC_COUNTRY");
			STATIC_STAPLES_CARD_LENGTH=loadPropertyAndLog("STATIC_STAPLES_CARD_LENGTH");
			STATIC_AM_CC_CARD_LENGTH=loadPropertyAndLog("STATIC_AM_CC_CARD_LENGTH");
			
			RECORD_PER_NODE=Integer.parseInt(loadPropertyAndLog("RECORD_PER_NODE"));
			
		} catch (FileNotFoundException e) {
			throw ServiceExceptionFactory.create(e,
					ServiceErrorCodes.FILE_NOT_FOUND_EXCEPTION_CODE,
					ServiceErrorCodes.FILE_NOT_FOUND_EXCEPTION+cardPropertiesFile);
		}catch (IOException e) {
			throw ServiceExceptionFactory.create(e,
					ServiceErrorCodes.PROPERTIES_LOAD_EXCEPTION_CODE,
					ServiceErrorCodes.PROPERTIES_LOAD_EXCEPTION+cardPropertiesFile);
		}
	}
	private static String loadProperty(String propertyName) {
		String str = props.getProperty(propertyName);
		str = str == null ? null : str.trim();
		return (str);
	}

	private static String loadPropertyAndLog(String propertyName) {
		String str = props.getProperty(propertyName);
		str = str == null ? null : str.trim();
		CardLogger.debug("--" + propertyName + "= " + str);
		return (str);
	}
}

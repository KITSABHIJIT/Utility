package com.srccodes.examples;

import org.apache.log4j.Logger;

/**
 * 
 * @author Abhijit Roy
 * @version 1.0
 */
public class Log4jMailNotiferTest {
    private static Logger logger = Logger.getLogger(Log4jMailNotiferTest.class);


	/**
	 * To test whether fatal log sent to email id or not.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// Generate exception
			throw new Exception("Generating exception to test Log4j mail notification...");
		} catch (Exception ex) {
			logger.error("Test Result : ", ex);
		}
	}
}
package com.staples.cards.logger;

import org.apache.log4j.Logger;
public class CardLogger {

	static Logger log4j = Logger.getLogger(CardLogger.class.getName());

	public static void error(String line) {
		log4j.error(line);
	}

	public static void error(String message, Exception ex) {
		ex.printStackTrace();
		log4j.error(message , ex);
	}

	public static void info(String line) {
		log4j.info(line);
	}

	public static void debug(String line) {
		log4j.debug(line);
	}

	public static void debugAndInfo(String line) {
		log4j.debug(line);
		log4j.info(line);
	}

	public static void debugAndConsole(String line) {
		System.out.println(line);
		log4j.debug(line);
	}

	public static void console(String line) {
		System.out.println(line);
	}


}

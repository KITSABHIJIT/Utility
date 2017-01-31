package com.multithread.util;

import java.io.IOException;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

public class Log4JConfig {
	private static final Logger log = Logger.getLogger(Log4JConfig.class);
	 
	public static void configLog(String logFileName)
	{
	//This is the root logger provided by log4j
	Logger rootLogger = Logger.getRootLogger();
	rootLogger.setLevel(Level.DEBUG);
	log.debug("Logfile configured as : "+logFileName);
	//Define log pattern layout
	PatternLayout layout = new PatternLayout("%d{ISO8601} [%t] %-5p %c %x - %m%n");
	 
	//Add console appender to root logger
	rootLogger.addAppender(new ConsoleAppender(layout));
	try
	{
	//Define file appender with layout and output log file name
	RollingFileAppender fileAppender = new RollingFileAppender(layout, logFileName);
	 
	//Add the appender to root logger
	rootLogger.addAppender(fileAppender);
	}
	catch (IOException e)
	{
		log.error("Failed to add appender !!");
	}
	}
}

package com.multithread.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ProxyProperties {
	private static Properties props = null;
	private static File configFile;

	public static int NR_WORKERS, PERMITS,THREADLIMIT;
	// AS400_MAX_CONNECTIONS; // [JDBC Connection Pool]
	private static Logger logger =Logger.getLogger(ProxyProperties.class.getName());

	public static boolean DEBUG, PERF;

	public static void initProperties(String args[]) throws IOException,
			NoSuchAlgorithmException {
		if (args.length < 1) {
			logger.debug("Please specify the property file path!");
			logger.error("Please specify the property file path!");
			logger.error("Terminating Proxy Server now");
			System.exit(1);
		}

		configFile = new File(args[0]);
		InputStream in = new FileInputStream(configFile);
		props = new Properties();
		props.load(in);


		NR_WORKERS = Integer
				.parseInt(loadPropertyAndLog("proxy.worker_threads"));
		PERMITS = Integer
				.parseInt(loadPropertyAndLog("proxy.semaphore.permits"));
		THREADLIMIT = Integer
				.parseInt(loadPropertyAndLog("proxy.semaphore.threadLimit"));
		in.close();
	}

	private static String loadProperty(String propertyName) {
		String str = props.getProperty(propertyName);
		str = str == null ? null : str.trim();
		return (str);
	}

	private static String loadPropertyAndLog(String propertyName) {
		String str = props.getProperty(propertyName);
		str = str == null ? null : str.trim();
		logger.debug("--" + propertyName + "= " + str);
		return (str);
	}
}
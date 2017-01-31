package com.multithread.test;

import java.util.concurrent.Semaphore;

import org.apache.log4j.Logger;
public class ProxyWorker implements Runnable {
	private Semaphore request_semaphore;
	private long proxyEntryTime;
	private ThreadObject srcMsg;
	private Logger logger;

	// constructor
	public ProxyWorker(Semaphore request_semaphore, long proxyEntryTime,
			ThreadObject data,String logFileName) {
		this.request_semaphore = request_semaphore;
		this.proxyEntryTime = proxyEntryTime;
		this.srcMsg = data;
		this.logger = Logger.getLogger(logFileName);
	}

	public void run() {
			long proxyExitTime = System.currentTimeMillis();
			if("AAA".contains(srcMsg.getMessage())){
				logger.debug("Found String AAA");
			}else if("BBB".contains(srcMsg.getMessage())){
				logger.debug("Found String BBB");
			}else{
				logger.debug("Found String CCC");
			}
			logger.debug("Operation done in: " + (proxyExitTime - proxyEntryTime));
			request_semaphore.release();
	}

}
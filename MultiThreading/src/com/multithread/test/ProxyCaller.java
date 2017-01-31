package com.multithread.test;

import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;

import org.apache.log4j.Logger;

import com.myutility.code.DateUtil;
public class ProxyCaller implements Callable {
	private Semaphore request_semaphore;
	private long proxyEntryTime;
	private int srcMsg;
	private Logger logger;

	// constructor
	public ProxyCaller(Semaphore request_semaphore, long proxyEntryTime,
			int srcMsg,String logFileName) {
		this.request_semaphore = request_semaphore;
		this.proxyEntryTime = proxyEntryTime;
		this.srcMsg = srcMsg;
		this.logger = Logger.getLogger(logFileName);;
	}

	@Override
	public Object call() throws Exception {
		logger.debug("Thread : "+srcMsg+" running");
		long proxyExitTime = System.currentTimeMillis();
		srcMsg=srcMsg+1;
		logger.debug("New Value: "+srcMsg);
		logger.debug("Operation done in: " + (proxyExitTime - proxyEntryTime));
		request_semaphore.release();
		logger.debug("Thread completed work in : "+DateUtil.getHrMinSec(proxyExitTime - proxyEntryTime));
		logger.debug("Thread : "+srcMsg+" ending");
		return srcMsg;
	}

}
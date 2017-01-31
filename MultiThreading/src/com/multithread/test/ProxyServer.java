package com.multithread.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

import org.apache.log4j.Logger;

public class ProxyServer {
	private static ExecutorService executorSvc;
	private static Semaphore request_semaphore;
	private static boolean finished;
	static Logger loggger = Logger.getLogger(ProxyServer.class.getName());;
	private static String logFileName;
	public static void main(String[] args) {
		loadAndVerifyProperties(args);
		initializeThreadServices();
		loggger.debug("** PROXY Server initialized successfully **");	
		runProcessExecutors();

	}
	private static ArrayList<ThreadObject> getData(){
		ArrayList<ThreadObject> data=new ArrayList<ThreadObject>();
		for(int i=0;i<20;i++){
			ThreadObject threadObject1=new ThreadObject();
			ThreadObject threadObject2=new ThreadObject();
			ThreadObject threadObject3=new ThreadObject();
			threadObject1.setMessage("AAA"+i);
			threadObject2.setMessage("BBB"+i);
			threadObject3.setMessage("CCC"+i);
			data.add(threadObject1);
			data.add(threadObject2);
			data.add(threadObject3);
		}
		return data;
	}
	private static void loadAndVerifyProperties(String args[]) {
		loggger.debug("******** loadAndVerifyProperties()");
		try {
			ProxyProperties.initProperties(args);
		} catch (Exception ex) {
			loggger.error("Proxy Server: Failed to read properties");
		}
	}
	private static void initializeThreadServices() {
		executorSvc = Executors.newFixedThreadPool(ProxyProperties.NR_WORKERS);
		loggger.debug("Initialized Executor Thread Pool successfully");

		request_semaphore = new Semaphore(ProxyProperties.PERMITS);
		loggger.debug("Initialized Semaphore Permits successfully");	
		loggger.debug("Initialized with "
				+ request_semaphore.availablePermits() + " permits");
	}
	private static void runProcessExecutors() {
		ArrayList<ThreadObject> data=getData();
		int counter=0;
		Iterator itr = data.iterator();
		while (itr.hasNext()) {

			try {

				loggger.debug("Acquiring a available permit - current size: "
						+ request_semaphore.availablePermits());
				request_semaphore.acquire();
				//	Thread.sleep(500);
				loggger.debug("Permit just acquired - available permits presently: "
						+ request_semaphore.availablePermits());
				threadMonitoring(request_semaphore.availablePermits() ,ProxyProperties.PERMITS );
			} catch (InterruptedException ex) {
				loggger.error("Interrupted exception occured at "+System.currentTimeMillis());
				finished = true;
			}

			long proxyEntryTime = System.currentTimeMillis();
			Runnable work = null;
			//Callable<Integer> work= null;
			try {
				work = new ProxyWorker(request_semaphore,
						proxyEntryTime, data.get(counter),logFileName);
				executorSvc.execute(work);
				/*work = new ProxyCaller(request_semaphore,
						proxyEntryTime, data);
				Future<Integer> future =executorSvc.submit(work);
				data=future.get();
				if(data==100){
					finished = true;
				}*/
				counter++;
			} catch (Exception ex) {
				loggger.error(ex.getMessage());
			}
		}
		loggger.error("Shutdown called. Finishing outstanding jobs and stopping the process.");
		executorSvc.shutdown(); // Finish any outstanding jobs
		//End the AS400 CL Job
		System.exit(1);
	}
	
	private static void threadMonitoring(int avlpermit , int permits ) {
		float percentage = 0.0F;
		float avlFpermit = 0.0F;
		float permitsF = 0.0F;
		avlFpermit = avlpermit;
		permitsF = permits;
		percentage = ((avlFpermit/permitsF)*100);
		if (percentage <= ProxyProperties.THREADLIMIT ) {
		loggger.debug("Available Permits reached below threshold % of "+percentage+"for processing.");
		//DataQueueSvc.proxyError("Available Permits reached below threshold % of "+percentage+"for processing.","W");
		}

	}
}

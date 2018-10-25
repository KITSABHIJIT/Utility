package com.staples.util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.mq.MQC;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.staples.main.MQDetail;



public class MQConfigureUtil {

	private static final Logger LOG = LoggerFactory.getLogger(MQConfigureUtil.class);
	private static MQQueueManager MQ_request_Queue_Mgr = null;
	private static MQQueue MQ_request_queue = null;
	
	public static void initializeMQ(){

		// initialize REQUEST QUEUE
		try {
			
			if(StringUtils.isNotBlank(PropertiesUtil.getProperty("mqHost"))) {
				MQEnvironment.hostname = PropertiesUtil.getProperty("mqHost");
			}
			int port=Integer.parseInt(PropertiesUtil.getProperty("mqPort"));
			if(port > 0) {
				MQEnvironment.port = port;
			}
			if(StringUtils.isNotBlank(PropertiesUtil.getProperty("mqChannel"))) {
				MQEnvironment.channel = PropertiesUtil.getProperty("mqChannel");
			}
			MQEnvironment.properties.put(MQC.TRANSPORT_PROPERTY,MQC.TRANSPORT_MQSERIES);  
			MQ_request_Queue_Mgr = new MQQueueManager(PropertiesUtil.getProperty("mqQMgr"));
			LOG.debug("----initializeMQ-----");

		} catch (final Exception e) {
			e.printStackTrace();
			LOG.error("Error initializing request queue.", e);
		}
		LOG.info("Initialized MQ request connection successfully");
	}
	
	
	public static String checkMQDepth(MQDetail  mqDetail){
		String alert=null;	
		try
		{
			@SuppressWarnings("deprecation")
			final int openOptions = MQC.MQOO_INQUIRE | MQC.MQOO_INPUT_AS_Q_DEF;
			MQ_request_queue = MQ_request_Queue_Mgr.accessQueue(mqDetail.getMqName(), openOptions, null, null,null);
			int threshold=Integer.parseInt(PropertiesUtil.getProperty("queueDepthThreshold"));
			int queueDepth=MQ_request_queue.getCurrentDepth();
			if(queueDepth>=threshold) {
				alert="System: "+PropertiesUtil.getProperty("mqHost")+"\nApplication: "+mqDetail.getApplication()+((null!=mqDetail.getIfsPath())?"\nIFS Path: "+mqDetail.getIfsPath():"")+((null!=mqDetail.getJobName())?"\nAS400 Job: "+mqDetail.getJobName():"")+"\nMQ: "+mqDetail.getMqName()+"\nDepth: "+queueDepth+"\n\n";
			}
			LOG.info("System: "+PropertiesUtil.getProperty("mqHost")+" Application: "+mqDetail.getApplication()+((null!=mqDetail.getIfsPath())?" IFS Path: "+mqDetail.getIfsPath():"")+((null!=mqDetail.getJobName())?" AS400 Job: "+mqDetail.getJobName():"")+" MQ: "+mqDetail.getMqName()+" Depth: "+queueDepth);
			postToRelic(mqDetail,queueDepth);
		}
		catch(Exception e){
			e.printStackTrace();
			LOG.error("Error while reading queue depth of "+mqDetail.getMqName()+"["+mqDetail.getApplication()+"]", e);
		}
		return alert;
	}
	
	
	private static void postToRelic(MQDetail  mqDetail,int queueDepth) {
		String input=null;
		try {
			URL url = new URL(PropertiesUtil.getProperty("relic.host"));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", PropertiesUtil.getProperty("relic.content.type"));
			conn.addRequestProperty("X-Insert-Key", PropertiesUtil.getProperty("relic.key"));
			input = "{"
					+ "\"eventType\":\""+PropertiesUtil.getProperty("relic.event.type")+"\","
					+ "\"system\":\""+PropertiesUtil.getProperty("mqHost")+"\","
					+ "\"application\":\""+mqDetail.getApplication()+"\","					
					+ ((null!=mqDetail.getJobName())?"\"jobName\":\""+mqDetail.getJobName()+"\",":"")
					+ ((null!=mqDetail.getIfsPath())?"\"path\":\""+mqDetail.getIfsPath()+"\",":"")
					+ "\"mqName\":\""+mqDetail.getMqName()+"\","
					+ "\"queueDepth\":\""+queueDepth
					+"\"}";
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED 
					&& conn.getResponseCode() != HttpURLConnection.HTTP_OK
					&& conn.getResponseCode() != HttpURLConnection.HTTP_ACCEPTED) {
				LOG.error("Failed : HTTP error code : "+ conn.getResponseCode()+" Message: "+input);
			}
			/*BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}*/
			conn.disconnect();
		} catch (IOException e) {
			LOG.error("Message: "+input,e);
		}
	}
}

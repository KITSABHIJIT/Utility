package main;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.mq.MQC;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;



public class MQConfigure {

	private static final Logger LOG = LoggerFactory.getLogger(MQConfigure.class);

	private static MQQueueManager MQ_request_Queue_Mgr = null;
	private static MQQueue MQ_request_queue = null;

	private static Map<String, MQQueue> RESPONSE_QUEUE_MAP = new HashMap<String, MQQueue>();
	public static void initializeMQ() throws MQException {

		// initialize REQUEST QUEUE
		try {
			
			if(StringUtils.isNotBlank(MQConstants.mqHostName)) {
				MQEnvironment.hostname = MQConstants.mqHostName;
			}

			if(MQConstants.mqHostPort > 0) {
				MQEnvironment.port = MQConstants.mqHostPort;
			}
			if(StringUtils.isNotBlank(MQConstants.mqChannel)) {
				MQEnvironment.channel = MQConstants.mqChannel;
			}
			MQ_request_Queue_Mgr = new MQQueueManager(MQConstants.mqManager);

			final int openOptions = MQC.MQOO_OUTPUT;
			MQ_request_queue = MQ_request_Queue_Mgr.accessQueue(MQConstants.mqQueueName, openOptions, null, null,
					null);
			LOG.debug("----initializeMQ-----");
			//LOG.debug("Queue Name: "+reqQueueConfig.getRequestQualQueueName());

		} catch (final Exception e) {
			LOG.error("Error initializing request queue.", e);
			e.printStackTrace();
		}
		// MQPoolSvc.initialize();
		LOG.info("Initialized MQ request connection successfully");
	}
	
	
	public void sendResponseMessage(String outputXML) 
		{
			try
			{
				
				
				/*MQPutMessageOptions msgOptions = new MQPutMessageOptions();
				MQPutMessageOptions pmo = new MQPutMessageOptions();
				pmo.options = pmo.options + MQC.MQPMO_NEW_MSG_ID;
*/
				
				final MQMessage mqMessage = new MQMessage();
				//mqMessage.expiry= 2880001;//tenth of seconds
				mqMessage.expiry= 288001;//tenth of seconds
				mqMessage.format = MQC.MQFMT_STRING;

				
				mqMessage.replyToQueueManagerName="DAYBREAK3";
				
				//mqMessage.replyToQueueName="PERF_NAD.PAYMENT.RESP.055D.LQ";
				mqMessage.replyToQueueName="PERF_NAD.PAYMENT.RESP.020D.LQ";
				
				/*mqMessage.replyToQueueManagerName="RAINBOW3";
				mqMessage.replyToQueueName="PAYMENT.SERVICES.JENGINE.RES.01.LQ";*/
				
				//mqMessage.replyToQueueName="PAYMENT.SERVICES.GATEWAY.RSP.TST.LQ";
				
				
				//mqMessage.replyToQueueName="PAYMENT.SERVICES.GATEWAY.RSP.TST.LQ";
				
				/*outMsg.replyToQueueManagerName="ESBQAWMB01";
				outMsg.replyToQueueName="COM3SVC.BRK.CREDITAUTH_RESP.QA1.RQ";
				outMsg.replyToQueueManagerName="DAYBREAK3";
				outMsg.replyToQueueName="PAYMENT.AUTH.GATEWAY.SIMULATOR.DV1.LQ";
				outMsg.replyToQueueManagerName="ESBD1MQ01";
				outMsg.replyToQueueName="NGPOS.ESB.CRDTAUT_GPAS_GBO_RSP.DV1";
				
				outMsg.replyToQueueManagerName="ESBQPNR1";
				outMsg.replyToQueueName="COM4SVC.ESB.CRDTAUT_GPAS_GBO_RSP.PRF";
				outMsg.replyToQueueManagerName="POSQ1AS01";
				outMsg.replyToQueueName="PAYMENT.RESP.AQ";
				outMsg.replyToQueueManagerName="DAYBREAK3";
				outMsg.replyToQueueName="PERF_NAD.PAYMENT.RESP.055D.LQ";
				
				outMsg.replyToQueueManagerName="ESBQPNR1";
				outMsg.replyToQueueName="COM4SVC.ESB.CRDTAUT_GPAS_GBO_RSP.PRF";
				
				outMsg.replyToQueueManagerName="LRWFQNASV01";
				outMsg.replyToQueueName="PAYMENT.RESP.AQ";
				
				outMsg.replyToQueueManagerName="POSQ1AS01";
				outMsg.replyToQueueName="PAYMENT.RESP.AQ";*/
				
				System.out.println("outputXML posted into the Queue:: "+ outputXML);
				
				mqMessage.writeString(outputXML);
				MQ_request_queue.put(mqMessage);
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	
}

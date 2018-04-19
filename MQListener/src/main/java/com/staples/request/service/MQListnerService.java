package com.staples.request.service;

import java.io.EOFException;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ibm.mq.MQC;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;
import com.staples.request.util.StringUtil;

@Service
public class MQListnerService {
	private final static Logger logger = LoggerFactory.getLogger(MQListnerService.class.getClass());


	@Value("${request.queue.config}")
	private String queueConfig;

	@Value("${request.mq.host}")
	private String mqHost; 
	@Value("${request.mq.manager}")
	private String mqManager; 
	@Value("${request.mq.channel}")
	private String mqChannel;
	@Value("${request.mq.name}")
	private String mqName;
	@Value("${request.mq.port}")
	private int mqPort;
	private static MQQueueManager MQ_request_Queue_Mgr = null;
	private static MQQueue MQ_request_queue = null;


	@Value("${request.rabbitmq.host}")
	private String rabbitMqHost;
	@Value("${request.rabbitmq.queue}")
	private String rabbitMqName;
	private Channel channel; 

	private void initializeMQ(){
		try
		{
			MQEnvironment.hostname = (!StringUtil.isBlankOrEmpty(mqHost))?mqHost:null;
			MQEnvironment.port = (mqPort > 0)?mqPort:0;
			MQEnvironment.channel = (!StringUtil.isBlankOrEmpty(mqChannel))?mqChannel:null;
			MQEnvironment.properties.put(MQC.TRANSPORT_PROPERTY,MQC.TRANSPORT_MQSERIES_CLIENT); 
			MQ_request_Queue_Mgr = new MQQueueManager(mqManager);
			final int openOptions = MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_OUTPUT | MQC.MQOO_INQUIRE;
			MQ_request_queue = MQ_request_Queue_Mgr.accessQueue(mqName, openOptions, null, null,null);
			logger.info("----MQ Initialized-----");
			logger.info("Qualified Queue Name: "+ mqName);
			logger.info(" Queue Manager: " + mqManager);
			logger.info(" Queue Name: " + mqName);
		}
		catch (final MQException e)
		{
			logger.error("Error initializing request queue.", e);
			e.printStackTrace();
		}
		logger.debug(MQListnerService.class.getName(), "startListening()", "EXITING");
	}

	public void getMessage(final MQMessage message,
			final MQGetMessageOptions gmo) throws MQException{
		if(null!=MQ_request_queue){
			MQ_request_queue.get(message, gmo);
		}else{
			throw new MQException(0,0,null,"MQ is not initialized");
		}
	}

	public void startListeningToMQ(){
		System.out.println(queueConfig);
		initializeMQ();
		boolean finished = false;
		final int waitInterval = 30 * 1000; // in milliseconds

		while (!finished)
		{
			final MQMessage srcMsg = new MQMessage();
			final MQGetMessageOptions gmo = new MQGetMessageOptions();
			gmo.waitInterval = waitInterval;
			gmo.options |= MQC.MQGMO_WAIT;
			gmo.options |= MQC.MQGMO_FAIL_IF_QUIESCING; 		

			try
			{
				getMessage(srcMsg, gmo);

				logger.debug("*******New Message received********");
				logger.debug("Correlation Id: "+StringUtil.getHexString(srcMsg.correlationId)
						+" Message Id: "+StringUtil.getHexString(srcMsg.messageId)
						+" ApplicationIdData: "+srcMsg.applicationIdData
						+" ApplicationOriginData: "+srcMsg.applicationOriginData
						+" PutApplicationName: "+srcMsg.putApplicationName
						+" ReplyToQueueManagerName: "+srcMsg.replyToQueueManagerName
						+" ReplyToQueueName: "+srcMsg.replyToQueueName
						+" Message Expiry: "+srcMsg.expiry);


				String message = srcMsg.readStringOfCharLength(srcMsg.getDataLength());
				logger.info("Message received: "+message);
				//Add you code where this message will be an input
			}
			catch (EOFException e) {
				logger.error("FATAL: Error while reading message from MQ -",e);
			} catch (IOException e) {
				logger.error("FATAL: Error while reading message from MQ -",e);
			}catch (MQException ex){
				logger.error(ex.getMessage(), ex.reasonCode);
				if ((ex.reasonCode == MQException.MQRC_NO_MSG_AVAILABLE) || (ex.reasonCode == 2037)){
					continue;
				}
				else if (ex.reasonCode == MQException.MQRC_Q_MGR_QUIESCING){
					finished = true;
				}
				else
				{
					try{
						Thread.sleep(20000);
						initializeMQ();
						startListeningToMQ();
					}
					catch (final InterruptedException e){
						e.printStackTrace();
					}
				}
			}
		}

	}



	public ConnectionFactory getConnectionFactory(){
		ConnectionFactory connectionFactory=new ConnectionFactory();
		connectionFactory.setHost(rabbitMqHost);
		return connectionFactory;
	}

	public Connection getConnection(){
		Connection connection=null;
		try{
			connection = getConnectionFactory().newConnection();
		}
		catch (IOException e){
			e.printStackTrace();
		} catch (TimeoutException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public Channel getChannel(){

		Channel channel = null;
		try{
			channel=getConnection().createChannel();
			channel.queueDeclare(rabbitMqName, true, false, false, null);
			channel.basicQos(1);
			logger.info("----MQ Initialized-----");
			logger.info("Rabbit Queue Name: "+ rabbitMqName);
			logger.info(" Rabbit Host: " + rabbitMqHost);
		}
		catch(Exception e){
			e.printStackTrace();;
		}
		return channel;
	}
	public void startListeningToRabbitMq(){
		boolean finished = false;
		try {
			channel=getChannel();
			QueueingConsumer consumer = new QueueingConsumer(channel);
			channel.queueDeclare(rabbitMqName, true, false, false,null);
			channel.basicConsume(rabbitMqName, false, consumer);
			QueueingConsumer.Delivery delivery;
			while (!finished)
			{
				delivery = consumer.nextDelivery();
				String message = new String(delivery.getBody());
				logger.info("Message Received:"+message);
				// Add you code where this message will be an input
				channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
				logger.info("Waiting for messages..");
			}
		} catch (ShutdownSignalException e) {
			logger.error("Rabbit MQ Stopped due to Shutdown Signal Exception");
			e.printStackTrace();
			try {
				Thread.sleep(20000);
				channel=getChannel();
				startListeningToRabbitMq();
			} catch (InterruptedException e1) {
				logger.error("Thread Interupted...");
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
			finished=true;
		} catch (ConsumerCancelledException e) {
			e.printStackTrace();
			finished=true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			finished=true;
		} 
	}

	public void startListening(){
		if("RABBITMQ".equalsIgnoreCase(queueConfig)){
			startListeningToRabbitMq();
		}else{
			startListeningToMQ();
		}
	}

	public void postMessageToRabbitMq(String message){
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(rabbitMqHost);
		Connection connection=null;
		Channel channel=null;
		try {
			connection = factory.newConnection();
			channel = connection.createChannel();
			channel.queueDeclare(rabbitMqName, false, false, false, null);
			channel.basicPublish("", rabbitMqName, null, message.getBytes());
			System.out.println(" [x] Sent '" + message + "'");
		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		}finally{
			try {
				if(null!=channel){
					channel.close();
				}
				if(null!=connection){
					connection.close();
				}
			} catch (IOException | TimeoutException e) {
				e.printStackTrace();
			}
		}
	}
}
package com.staples.jenkins.build;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400ConnectionPool;
import com.ibm.as400.access.ConnectionPoolException;
import com.ibm.as400.access.KeyedDataQueue;


public class DataQueueConfig {
	
	private final static Logger logger = LoggerFactory.getLogger(DataQueueConfig.class);
	private static KeyedDataQueue DATA_QUEUE;
	private static final String FORWARD_SLASH = "/";
	private static AS400ConnectionPool connectionPool;

	public DataQueueConfig() {
		connectionPool = getAS400ConnectionPool();
		final StringBuilder builder = new StringBuilder(PropertiesUtil.getProperty("data_queue_sys_lib"));
		builder.append(FORWARD_SLASH).append(PropertiesUtil.getProperty("data_queue_library")).append(FORWARD_SLASH)
		.append(PropertiesUtil.getProperty("data_queue_name"));
		DATA_QUEUE = new KeyedDataQueue(this.getConnection(), builder.toString());
		logger.debug("Data Queue initialized Successfully");
	}

	private static AS400ConnectionPool getAS400ConnectionPool() {
		final AS400ConnectionPool connectionPool = new AS400ConnectionPool();
		// TODO soft code pool configurations
		connectionPool.setMaxConnections(5);
		try {
			connectionPool.fill(PropertiesUtil.getProperty("server"), PropertiesUtil.getProperty("user"),
					PropertiesUtil.getProperty("password"), AS400.DATAQUEUE, 5);
		} catch (final ConnectionPoolException e) {
			//LOG.error("Error initiating AS400 Connection pool", e);
			return null;
		}
		return connectionPool;
	}

	public AS400 getConnection() {
		AS400 as400 = null;
		try {
			as400 = connectionPool.getConnection(PropertiesUtil.getProperty("server"), PropertiesUtil.getProperty("user"),
					PropertiesUtil.getProperty("password"), AS400.DATAQUEUE);
		} catch (final ConnectionPoolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return as400;
	}

	public void returnConnection(final AS400 as400) {
		connectionPool.returnConnectionToPool(as400);
	}

	
	public String buildJobData(BuildLog buildLog,CaRule caRule) {
		//Sample =QPADEV000TDEVSYY    210130DEVSYY    CA_TRIGGR220180510101832CA_ENGS66DCARULE    3CAP551      0CA_066D   CA_ENGS66DCA_FEP_MONS020        /   0     CA_FEP_MONPAYMENTS
		StringBuilder message=new StringBuilder(PropertiesUtil.getProperty("message_prefix"));
		message.append(StringUtil.padString(PropertiesUtil.getProperty("message_user"), 10, false, " "));
		message.append(StringUtil.getCurrentDate("yyMMdd"));
		message.append(PropertiesUtil.getProperty("message_key_value_delimetter"));
		message.append(StringUtil.padString(PropertiesUtil.getProperty("message_user"), 10, false, " "));
		message.append(PropertiesUtil.getProperty("message_trigger"));
		message.append(StringUtil.getCurrentDate("yyyyMMddhhmmss"));
		message.append(StringUtil.padString(buildLog.getAs400JobName(), 10, false, " "));
		message.append(StringUtil.padString(PropertiesUtil.getProperty("message_param1"), 10, false, " "));
		message.append(PropertiesUtil.getProperty("message_kill_command"));
		message.append(StringUtil.padString(caRule.getProgramName(), 12, false, " "));
		message.append(PropertiesUtil.getProperty("message_param3"));
		message.append(StringUtil.padString(caRule.getJobD(), 10, false, " "));
		message.append(StringUtil.padString(buildLog.getAs400JobName(), 10, false, " "));
		message.append(StringUtil.padString(caRule.getControlProgram(), 10, false, " "));
		message.append(caRule.getDelay());
		message.append(StringUtil.padString(caRule.getPriority(), 3, true, "0"));
		message.append(StringUtil.padString(PropertiesUtil.getProperty("message_param2"), 9, true, " "));
		message.append(StringUtil.padString(PropertiesUtil.getProperty("message_param3"), 4, true, " "));
		message.append(StringUtil.padString(caRule.getControlProgram(), 15, true, " "));
		message.append(PropertiesUtil.getProperty("message_suffix"));
		return message.toString();
	}
	
	
	public void sendData(final String data) throws Exception {
		AS400 as400 = null;
		try {
			as400 =  getConnection();
			// write data to queue
			String key=data.split("["+PropertiesUtil.getProperty("message_key_value_delimetter")+"]")[0];
			String value=data.split("["+PropertiesUtil.getProperty("message_key_value_delimetter")+"]")[1];
			logger.debug("Writing to DQ : ");
			logger.debug("Key : "+ key);
			logger.debug("Value : "+ value);
			DATA_QUEUE.write(key,value);
			logger.debug("Write to Data Queue complete");
			//getData() ;
		} catch (final Exception e) {
			throw e;
		} finally {
			if (as400 != null) {
				returnConnection(as400);
			}
		}

	}
	

}

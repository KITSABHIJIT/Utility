package main;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400ConnectionPool;
import com.ibm.as400.access.ConnectionPoolException;
import com.ibm.as400.access.DataQueue;
import com.ibm.as400.access.DataQueueEntry;
import com.ibm.as400.access.ObjectDoesNotExistException;



public class DataQueueConfig {
	
	
	private static DataQueue DATA_QUEUE;
	private static final String FORWARD_SLASH = "/";
	private static AS400ConnectionPool connectionPool;

	public DataQueueConfig() {
		connectionPool = getAS400ConnectionPool();
		final StringBuilder builder = new StringBuilder(MQConstants.DQ_QSYS_LIB);
		builder.append(FORWARD_SLASH).append(MQConstants.DQ_REQUEST_QUEUE_LIB).append(FORWARD_SLASH)
		.append(MQConstants.DQ_REQUEST_QUEUE_NAME);
		DATA_QUEUE = new DataQueue(this.getConnection(), builder.toString());
	}

	private static AS400ConnectionPool getAS400ConnectionPool() {
		final AS400ConnectionPool connectionPool = new AS400ConnectionPool();
		// TODO soft code pool configurations
		connectionPool.setMaxConnections(5);
		try {
			connectionPool.fill(MQConstants.AS400_SERVER, MQConstants.AS400_USER,
					MQConstants.AS400_PASSWORD, AS400.DATAQUEUE, 5);
		} catch (final ConnectionPoolException e) {
			//LOG.error("Error initiating AS400 Connection pool", e);
			return null;
		}
		return connectionPool;
	}

	public AS400 getConnection() {
		AS400 as400 = null;
		try {
			as400 = connectionPool.getConnection(MQConstants.AS400_SERVER, MQConstants.AS400_USER,
					MQConstants.AS400_PASSWORD, AS400.DATAQUEUE);
		} catch (final ConnectionPoolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return as400;
	}

	public void returnConnection(final AS400 as400) {
		connectionPool.returnConnectionToPool(as400);
	}

	public String getData() throws Exception {
		String result = null;
		DataQueueEntry entry;
		try {
			System.out.println("Reading Data *****");
			entry = DATA_QUEUE.read(-1);
			//entry = DATA_QUEUE.read();
			System.out.println("entry : "+entry);
			if (entry != null) {
				result = entry.getString();
			}
		} catch (final ObjectDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final Exception e) {
			e.printStackTrace();
			throw e;
		}
		System.out.println("result :: "+result);
		return result;
	}
	
	
	public void sendData(final String data) {
		

		System.out.println("Writing to DQ : "+ data);
		AS400 as400 = null;
		try {
		
			as400 =  getConnection();
			// write data to queue

		
			DATA_QUEUE.write(data);
			System.out.println("Write to Data Queue complete");
			
			//getData() ;

		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			if (as400 != null) {
				returnConnection(as400);
			}
		}

	}
	

}

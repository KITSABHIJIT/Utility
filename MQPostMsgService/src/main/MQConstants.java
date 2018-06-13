package main;

public interface MQConstants {

	public static final String mqHostName = "DAYBREAK";
	public static final int mqHostPort = 65501;
	public static final String mqChannel = "DAYBREAK3.SVRCONN";
	public static final String mqManager = "DAYBREAK3";

	public static final String mqQueueName = "PAYMENT.SETTLEMENT.GATEWAY.REQ.QA2.LQ";//66D EMV queue
	//public static final String mqQueueName = "PAYMENT.SERVICES.COMM.FDREQ.DEV1.LQ"; // Rappid Connect Queue
	
	//NO_OF_CONNECTION = 1
	public static final String DQ_QSYS_LIB = "/QSYS.LIB";
	public static final String DQ_REQUEST_QUEUE_LIB ="SF027SLIB.LIB";
	public static final String DQ_REQUEST_QUEUE_NAME ="CRDATHREDQ.DTAQ";
	//public static final String DQ_REQUEST_QUEUE_NAME ="D2C_TST_T.DTAQ";
	public static final String  DQ_RESPONSE_QUEUE_LIB ="PR065SLIB.LIB";
	public static final String DQ_RESPONSE_QUEUE_NAME =".DTAQ";
	public static final String DQ_MESSAGE_ID ="";
	
	
	public static final String AS400_SERVER="COSMOS.STAPLES.COM";
	public static final String AS400_USER="DEVDZM";
	public static final String AS400_PASSWORD="cosmos06";
	public static final String AS400_LIB="PRMNTDLIB";
	public static final String DPS_AS400_LIB="PRMNTDLIB";
	public static final String AS400_MAX_CONNECTION_SIZE="25";
	
	
	
	
	//## Data Queue config
/*	public static final String AS400_SERVER="DAYBREAK.STAPLES.COM";
	public static final String AS400_USER="CDIUSR";
	public static final String AS400_PASSWORD="CDIUSR";
	public static final String AS400_LIB="PS017DLIB";
	public static final String DPS_AS400_LIB="PS017DLIB";

	//NO_OF_CONNECTION = 1
	public static final String DQ_QSYS_LIB ="/QSYS.LIB";
	public static final String DQ_REQUEST_QUEUE_LIB ="PS017DLIB.LIB";
	public static final String DQ_REQUEST_QUEUE_NAME ="D2C_TST_T.DTAQ";
	public static final String DQ_RESPONSE_QUEUE_LIB ="PS017DLIB.LIB";
	public static final String DQ_RESPONSE_QUEUE_NAME ="";*/
	//DQ_MESSAGE_ID =
	
}

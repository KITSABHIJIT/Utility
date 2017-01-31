package main;

public interface MQConstants {

	public static final String mqHostName = "Rainbow";
	public static final int mqHostPort = 65501;
	public static final String mqChannel = "SYSTEM.DEF.SVRCONN";
	public static final String mqManager = "Rainbow3 ";

	//public static final String mqQueueName = "PAYMENT.SERVICES.GATEWAY.REQ.TST.LQ";//66D EMV queue
	public static final String mqQueueName = "PAYMENT.SETTLEMENT.AMX.GATEWAY.LQ"; // Rappid Connect Queue
	
	//NO_OF_CONNECTION = 1
	public static final String DQ_QSYS_LIB = "/QSYS.LIB";
	public static final String DQ_REQUEST_QUEUE_LIB ="PRMNTDLIB.LIB";
	public static final String DQ_REQUEST_QUEUE_NAME ="CIT_ATH_T.DTAQ";
	//public static final String DQ_REQUEST_QUEUE_NAME ="D2C_TST_T.DTAQ";
	public static final String  DQ_RESPONSE_QUEUE_LIB ="PRMNTDLIB.LIB";
	public static final String DQ_RESPONSE_QUEUE_NAME =".DTAQ";
	public static final String DQ_MESSAGE_ID ="";
	
	
	public static final String AS400_SERVER="DAYBREAK.STAPLES.COM";
	public static final String AS400_USER="CDIUSR";
	public static final String AS400_PASSWORD="CDIUSR";
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

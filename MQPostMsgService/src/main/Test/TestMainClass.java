package main.Test;

import java.util.Map;

public  class TestMainClass {

	public static Map<String, String> nameMap;
	/**
	 * @param args
	 */
	
	/* private static volatile Instrumentation globalInstr;
	  public static void premain(String args, Instrumentation inst) {
	    globalInstr = inst;
	  }
	  public static long getObjectSize(Object obj) {
	    if (globalInstr == null)
	      throw new IllegalStateException("Agent not initted");
	    return globalInstr.getObjectSize(obj);
	  }*/

	   

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		/*	String decimalStr = "204980.91";
		
			String noDecimalStr = decimalStr;

			
			System.out.println(generateStan("53", 6));*/
			
		/*	String stanSentRecvRsp = "Stan Sent/ Received :"+000001+"/"+000001;
			System.out.println(" stanSentRecvRsp :: "+stanSentRecvRsp);
			
			String substr = stanSentRecvRsp.substring(stanSentRecvRsp.indexOf(":")+1, stanSentRecvRsp.length());
			System.out.println(" substr :: "+substr);
			String stanSent =  substr.substring(0, substr.indexOf("/"));
			System.out.println(" stanSent :: "+stanSent);
			String stanReceived =  substr.substring(substr.indexOf("/")+1,substr.length());
			System.out.println(" stanReceived :: "+stanReceived);
			
			if(stanSent.equalsIgnoreCase(stanReceived))
			{
				System.out.println("same STAN");
			}
			*/
			
			
			String echoMessage = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><GMF xmlns=\"com/firstdata/Merchant/gmfV4.02\"><AdminRequest><CommonGrp><TxnType>EchoTest</TxnType><LocalDateTime>20160302141533</LocalDateTime><TrnmsnDateTime>20160302191533</TrnmsnDateTime><STAN>005596</STAN><TPPID>RST005</TPPID><TermID>00000001</TermID><MerchID>RCTST0000006164</MerchID><GroupID>10001</GroupID><MerchEcho>EchoTest1452722395425</MerchEcho></CommonGrp></AdminRequest></GMF>";
			
			System.out.println(echoMessage.substring(echoMessage.indexOf("<STAN>")+"<STAN>".length(), echoMessage.indexOf("</STAN>")));
			
			
			// noDecimalStr = Double.toString(dblObject);
			//
			// DecimalFormat twoDec = new DecimalFormat("0.00");
			// twoDec.setGroupingUsed(false);
			// noDecimalStr = twoDec.format(dblObject);

			/*int x = decimalStr.indexOf('.');
			if (x > 0)
			{
				noDecimalStr = decimalStr.substring(0, x)
						+ decimalStr.substring(x + 1);
			}
			else if (x == 0)
			{
				noDecimalStr = decimalStr.substring(1, decimalStr.length());
			}
			else if (x < 0)
			{
				// code should not reach here
			}
		*/
		
		
		
		/*long heapSize = Runtime.getRuntime().totalMemory(); 
		
		System.out.println("Get current size of heap in bytes : "+ heapSize);
		
		// Get maximum size of heap in bytes. The heap cannot grow beyond this size.// Any attempt will result in an OutOfMemoryException.
		long heapMaxSize = Runtime.getRuntime().maxMemory();
		System.out.println("Get maximum size of heap in bytes. The heap cannot grow beyond this size. : "+heapMaxSize);

		nameMap = new HashMap<String, String>();
		for(int i = 0 ; i<1000 ; i++)
		{
			nameMap.put(String.valueOf(i), "Test Value");
		}
		
		
		//System.out.println(globalInstr.getObjectSize(nameMap)); 
		
		 // Get amount of free memory within the heap in bytes. This size will increase // after garbage collection and decrease as new objects are created.
		long heapFreeSize = Runtime.getRuntime().freeMemory(); 
		System.out.println("Get amount of free memory within the heap in bytes : "+heapFreeSize);*/

	}
	

	 
	public static String generateStan(String stan, int len){
		 
		 if(stan.length() == len)
		 {
			 return stan;
		 }
		 else if(stan.length() < len)
		 {
			 return String.format("%06d", Integer.parseInt(stan));
		 }
		 else
		 {
			 return stan.substring(stan.length()-len, stan.length());
		 }
		
	 }

	
	
}

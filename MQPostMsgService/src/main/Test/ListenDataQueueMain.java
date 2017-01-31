package main.Test;

import main.DataQueueConfig;

public class ListenDataQueueMain {

	public static DataQueueConfig as400Config;
	
	public static void main(String args[])
	{
		as400Config = new DataQueueConfig();
		try
		{
			//as400Config.getData();
			as400Config.sendData("Credit                          Authorization                   2015-10-21-10.10.13.95306709000USA001SSCSSCi1nYmSNI0ZJV                    90000006004336                  0016035517910018448        122015          000000000700000000000700                60110160014100T3URRT                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ");
			
			as400Config.getData();
			
			/*ReadDataQ readDataQ = new ReadDataQ();
			readDataQ.setDaemon(true);
			readDataQ.start();*/
			
			/*while(true)
			{
				final String dqRequestData = as400Config.getData();
				System.out.println("dqRequestData :: "+ dqRequestData);
				//get the data out of the DataQueueEntry object.
				if(dqRequestData == null){
					System.out.println(Thread.currentThread().getName() + ":- Data Queue is empty!!");
				}	
			}*/
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public static class ReadDataQ extends Thread
	{
		public void run()
		{
			while (true) {
			try
			{
			 String result = as400Config.getData();
			 System.out.println("result ::"+result);
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			}
		}
	}
	
}

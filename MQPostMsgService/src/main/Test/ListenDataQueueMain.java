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
			for(int i=0;i<4;i++)
				as400Config.sendData("718433051500000001GBPAUTHORIZATION");
	//		as400Config.sendData(data);
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

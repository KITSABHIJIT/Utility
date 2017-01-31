package main;

import java.io.BufferedReader;
import java.io.FileReader;
public class MQPostMessage {
	
	public static void main(String[] args)
	{
		try
		{
			MQConfigure.initializeMQ();
			System.out.println("Inside main class");

			WriteXMLFile writeXMLFile = new WriteXMLFile();
			MQConfigure mQConfigure = new MQConfigure();
			
			mQConfigure.sendResponseMessage(writeXMLFile.writeXmlFile());	
			
			/*for(int i = 0 ; i<1 ; i++)
			{
				mQConfigure.sendResponseMessage(writeXMLFile.writeXmlFile());	
				//Thread.sleep(100);
			}
			*/
			System.out.println("Message posting into Queue completed");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}

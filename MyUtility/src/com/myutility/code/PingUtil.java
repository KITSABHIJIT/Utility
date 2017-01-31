package com.myutility.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PingUtil {

	 public static void runSystemCommand(String command) {

			try {
				Process p = Runtime.getRuntime().exec(command);
				BufferedReader inputStream = new BufferedReader(
						new InputStreamReader(p.getInputStream()));

				String s = "";
				// reading output stream of the command
				while ((s = inputStream.readLine()) != null) {
					System.out.println(s);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	 public static void pingTest(String command){
	        String pingResult = "";
	        try {
	            Runtime r = Runtime.getRuntime();
	            Process p = r.exec(command);

	            BufferedReader in = new BufferedReader(new
	            InputStreamReader(p.getInputStream()));
	            String inputLine;
	            while ((inputLine = in.readLine()) != null) {
	                System.out.println(inputLine);
	                pingResult += inputLine+"\n";
	            }
	            in.close();
	        } catch (IOException e) {
	            System.out.println(e);
	            pingResult += e+"\n";
	        }
	        FileUtil.writeToFile(pingResult, "pingResult.txt");
	
	 }
		public static void main(String[] args) {
			
			//runSystemCommand(args[0]+" " + args[1]);
			pingTest(args[0]+" " + args[1]);

		
		}

}

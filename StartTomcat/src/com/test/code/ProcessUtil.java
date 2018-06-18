package com.test.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessUtil {

	public static void runMavenCommand() {
		try {
			String [] commands=PropertiesUtil.getProperty("commands").split("[|]");
			ProcessBuilder builder = new ProcessBuilder(commands);
			builder.redirectErrorStream(true);
			Process p = builder.start();
			System.out.println("Process Started");
			/* The stream obtains data from the standard output stream of the process represented by this Process object. */
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
			/* The stream obtains data from the error output stream of the process represented by this Process object. */
			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

			String Input;
			while ((Input = stdInput.readLine()) != null) {
				System.out.println(Input);
			}            

			String Error;
			while ((Error = stdError.readLine()) != null) {
				System.out.println(Error);
			}

			while (true) {
				try {
					System.out.println("Tomcat is running ...");
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

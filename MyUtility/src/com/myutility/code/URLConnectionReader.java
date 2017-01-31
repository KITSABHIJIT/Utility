package com.myutility.code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;

public class URLConnectionReader {
	public static void main(String[] args){
		String result = "";
	try{
		URL oracle = new URL(args[0]);
		URLConnection yc = oracle.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				yc.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null) 
			System.out.println(inputLine);
		 result += inputLine+"\n";
		in.close();
		FileUtil.writeToFile(result, "result.txt");
	}catch(Exception e){
		File file = new File("result.txt");
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			PrintStream ps = new PrintStream(file);
			e.printStackTrace(ps);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	}
}
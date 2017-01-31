package com.myutility.code;

public class MainCall {
	public static final String QUERY="query";
	public static final String SERVER="server";
	public static final String LIBRARY="library";
	public static final String OUTPUTFORMAT="outputFormat";
	public static final String SENDEMAIL="sendEmail";
	public static void main(String ...strings){
		//String eods=PropertiesUtil.getProperty("lomseods");
		//String fsa=PropertiesUtil.getProperty("lomsfsa");
		//QueryUtil.runCountQuery(eods,"eods",true);
		//QueryUtil.runCountQuery(fsa,"fsa",true);
		
		String eods=PropertiesUtil.getProperty("somseods");
		String fsa=PropertiesUtil.getProperty("somsfsa");
		QueryUtil.runCountQuery(eods,"eods",false);
		QueryUtil.runCountQuery(fsa,"fsa",false);
	}
	

	public static void sendDataOnEmail(){
		String query=PropertiesUtil.getProperty(QUERY);
		String server=PropertiesUtil.getProperty(SERVER);
		String library=PropertiesUtil.getProperty(LIBRARY);
		String outputFormat=PropertiesUtil.getProperty(OUTPUTFORMAT);
		boolean sendEmail=Boolean.valueOf(PropertiesUtil.getProperty(SENDEMAIL));
		QueryUtil.runSelectQuery(query,server,library,outputFormat,sendEmail);
	}
	
}

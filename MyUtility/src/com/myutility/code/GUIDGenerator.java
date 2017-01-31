package com.myutility.code;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class GUIDGenerator {
	private static String DATE_FORMAT_3= "yyyyMMddHHmmssSSSSSS";
	public static String getGuidDateTime(){
		return (new SimpleDateFormat(DATE_FORMAT_3).format(new Date()));
	}
	private static String generateRandomNumber(){
		
		Random randomGenerator = new Random();
	      int randomInt = randomGenerator.nextInt(1000000);
		return ""+randomInt;
	}
    public static String  getGUID(String parentGUID , String invoiceNo, String OrderNo) {
		String POSGUID = "";
		String randomNumber = "";
		String guid = "";
		randomNumber = generateRandomNumber();
		System.out.println("Before Adding Zeros: "+(parentGUID.substring(0,3)+"  POSGUID : "+POSGUID+"   GUID DateandTime : "+getGuidDateTime()+" RandomNumber : "+randomNumber));
		guid = parentGUID.substring(0,3)+""+POSGUID+getGuidDateTime()+randomNumber;
		System.out.println((guid.length())+"   Zeros Added : "+(32 -(guid.length())));
		for ( int i = 1 ; i <= 32 -(guid.length()) ; i++) {
			POSGUID += "0";
		}
		String generatedGuid = (parentGUID.substring(0,3) + "" + POSGUID +getGuidDateTime()+randomNumber).substring(0,32);
		return generatedGuid;
	}
    
    public static void main(String ...strings ){
    	/*System.out.println(getGUID("COM","454265","7650023546"));
    	System.out.println(getGUID("COM","454266","7650023547"));
    	System.out.println(getGUID("COM","454267","7650023548"));
    	System.out.println(getGUID("COM","454268","7650023549"));
    	System.out.println(getGUID("COM","454269","7650023550"));
    	System.out.println(getGUID("COM","454270","7650023551"));
    	System.out.println(getGUID("COM","454271","7650023552"));
    	System.out.println(getGUID("COM","454272","7650023553"));*/
    	System.out.println(getGUID("COM","454273","7650023554"));
    	System.out.println(getGUID("COM","454274","7650023555"));
    	
    	
    }
}

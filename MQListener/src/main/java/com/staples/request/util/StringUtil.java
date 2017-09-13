package com.staples.request.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class StringUtil {
	private static final byte[] HEX_CHAR_TABLE = { (byte) '0', (byte) '1',
		(byte) '2', (byte) '3', (byte) '4', (byte) '5', (byte) '6',
		(byte) '7', (byte) '8', (byte) '9', (byte) 'a', (byte) 'b',
		(byte) 'c', (byte) 'd', (byte) 'e', (byte) 'f' };
	public static String padZeros(String s, int padLength,boolean left) {
		s= s.trim();
		if(s.length()>=padLength){
			return (s);
		}
		int 
		strlen = s.length(),
		n = padLength-strlen;
		String s1="";
		for (int i=0; i<n; i++){
			s1= s1 + "0";
		}
		if(left)
			s1 = s1 + s;
		else
			s1 = s + s1;
		return (s1);
	}

	public static boolean isBlankOrEmpty(Object obj){
		if(null==obj){
			return true;
		}else{
			String temp=obj.toString();
			if("".equals(temp.trim())){
				return true;
			}else{
				return false;
			}
		}
	}

	public static String validateString(String obj){
		if(null==obj){
			return "";
		}else{
			return obj.trim();
		}
	}

	public static int getStrToInt(String str){
		int result=0;
		if(isBlankOrEmpty(str)){
			return result;
		}else{
			return Integer.parseInt(str);
		}
	}
	public static String getIntToStr(int value){
		if(isBlankOrEmpty(value)){
			return "";
		}else{
			return String.valueOf(value);
		}
	}
	public static String trim(String str){
		if(null==str){
			return "";
		}else{
			return str.trim();
		}
	}

	public static void main(String ...strings ){
		double value=0.92;
		System.out.println((int)value);

	}

	public static long convertIntoNumeric(String xVal) {
		Long numericField = 12345678910L;
		try {
			numericField = Long.parseLong(xVal);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return numericField;
	}

	public static String deriveInvoiceNo(String gcnInvoiceNo) // Returns only // the Numeric
	{ 
		String str = null;
		if(!isBlankOrEmpty(gcnInvoiceNo)){
			int index = gcnInvoiceNo.indexOf('#');
			if (index > 0) {
				str = gcnInvoiceNo.substring(index + 1); 
			}
		}
		return str;

	}

	public static String customFormat(String value, int decimalValue, int decimalPlace ) {
		if(!StringUtil.isBlankOrEmpty(value)){
			try{
				int intValue=Integer.parseInt(value);
				double doubleValue=intValue/Math.pow(10,decimalValue);
				return String.format("%."+decimalPlace+"f", doubleValue);
			} catch(NumberFormatException e){
				return String.format("%."+decimalPlace+"f", 0.00);
			}
		}else{
			return String.format("%."+decimalPlace+"f", 0.00);
		}
	}
	public static String getHexString(final byte[] raw)
	{

		final int len = raw.length;
		final char[] s = new char[len * 2];

		for (int i = 0, j = 0; i < len; i++)
		{
			final int c = raw[i] & 0xff;

			s[j++] = (char) HEX_CHAR_TABLE[c >> 4 & 0xf];
			s[j++] = (char) HEX_CHAR_TABLE[c & 0xf];
		}

		return new String(s);
	}
	
	public static String getCurrDateTime(){
		String DATE_FORMAT= "yyyyMMddHHmmssSSS";
	return (new SimpleDateFormat(DATE_FORMAT).format(new Date()));
	}
	
	public static String generateRandom(){
		Random generator = new Random( 123456 );
		 return (""+(generator.nextInt()+ 1));
	}
	
	public static String generateGuid(String indexRef) {
		try{Thread.sleep(1*10);
		}catch(Exception e) {
			
		}
		String middlePart= generateRandom()+getCurrDateTime();
		String modifiedString = "";
		if (middlePart.length()>29) {
			middlePart = middlePart.substring(0,28);
		}else if (middlePart.length()<29){
			for(int i=0;i<=29-middlePart.length();i++){
				middlePart="0"+middlePart;
			}
		}
		modifiedString = indexRef.concat(middlePart);
		return modifiedString;
	}
}

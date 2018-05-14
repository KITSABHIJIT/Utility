package com.staples.jenkins.build;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StringUtil {

	public static boolean isBlankOrEmpty (String word) {

		if(null!=word && word.trim()!="")
			return false;
		else 
			return true;

	}
	public static String padString(Object obj, int padLength,boolean left,String padString) {
		String s1="";
		if(!isBlankOrEmpty(obj)) {
			String s=obj.toString();
			s= s.trim();
			if(s.length()>=padLength){
				return (s);
			}
			int strlen = s.length(),
			n = padLength-strlen;

			for (int i=0; i<n; i++){
				s1= s1 + padString;
			}
			if(left)
				s1 = s1 + s;
			else
				s1 = s + s1;
		}
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
	
	public static String getCurrentDate(String format) {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(cal.getTime());
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
	public static String getFileNameWithoutExtn(String fileName){
		String file=null;
		File fl=new File(fileName);
		if(fl.exists()){
			if (fl.getName().indexOf(".") > 0) {
				file = fl.getName().substring(0, fl.getName().lastIndexOf("."));
			}
		}
		return file;
	}
}

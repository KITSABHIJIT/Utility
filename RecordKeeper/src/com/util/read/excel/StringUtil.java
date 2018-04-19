package com.util.read.excel;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {

	public static boolean isBlankOrEmpty (String word) {

		if(null!=word && !("".equals(word.trim())))
			return false;
		else 
			return true;

	}
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
	public static String trim(Object str){
		if(null==str){
			return "";
		}else{
			return String.valueOf(str).trim();
		}
	}

	public static void main(String ...strings ){
		//double value=0.92;
		//System.out.println((int)value);
		String data="00000000000000000000009404096506";
		System.out.println(StringUtils.substring(data, 5, 8));
		System.out.println(StringUtils.substring(data, 5, 8));
		System.out.println(StringUtils.substring(data, 8, 13));
		System.out.println(StringUtils.substring(data, 13, 32));
		System.out.println(StringUtils.substring(data, 0, 5));
		System.out.println(StringUtils.substring(data, 13, 23));
		System.out.println(StringUtils.substring(data, 0, 5));
		//detail.getAccount()).substring(0, 8)

	}

	public static long convertIntoNumeric(String xVal) {
		Long numericField = 0L;
		try {
			numericField = Long.parseLong(xVal);
		} catch (Exception ex) {
			//ex.printStackTrace();
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

	public static String decimalPlaceUpto(double value, int decimalPlace ) {
		try{
			return String.format("%."+decimalPlace+"f", value);
		} catch(NumberFormatException e){
			return String.format("%."+decimalPlace+"f", 0.00);
		}
	}

	public static String getSomeDateString(final Date dateTime,final String outputFormat){
		DateFormat converter = new SimpleDateFormat(outputFormat);
		String result=converter.format(dateTime);
		return result;
	}  
	
	public static Date getSomeDate(final String str, final String inputFormat)
		    throws ParseException {
		  final SimpleDateFormat sdf = new SimpleDateFormat(inputFormat);
		  return sdf.parse(str);
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

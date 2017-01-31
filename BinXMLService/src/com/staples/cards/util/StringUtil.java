package com.staples.cards.util;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {
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

	public static String padString(Object sObj, int padLength,boolean left,String appendString) {
		String temp=(null==sObj)?"":sObj.toString();
		String s= temp.trim();
		if(s.length()>=padLength){
			return (s.substring(0, padLength));
		}
		int 
		strlen = s.length(),
		n = padLength-strlen;
		String s1="";
		for (int i=0; i<n; i++){
			s1= s1 + appendString;
		}
		if(left)
			s1 = s1 + s;
		else
			s1 = s + s1;
		return (s1);
	}

	public static List<String> convertArrayToList(String [] objArray){
		List<String> objList=new ArrayList<String>();
		for(String obj:objArray){
			if(null!=obj){
				objList.add(obj);
			}
		}
		return objList;
	}
}

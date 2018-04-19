package com.myutility.code;

public class PrintUtil {
	public static String printInArrow(String data){
		String result="";
		if(!StringUtil.isBlankOrEmpty(data)){
			int length=data.length();
			StringBuffer buffer=new StringBuffer("   _______");
			for(int i=0;i<length;i++){
				buffer.append("_");
			}
			buffer.append("________");
			buffer.append("\n _/       "+data+"       /");
			buffer.append("\n  \\______");
			for(int i=0;i<length;i++){
				buffer.append("_");
			}
			buffer.append("________\\");
			result=buffer.toString();
		}
		return result;
	}
	
	public static void main(String args[]){
		System.out.println(printInArrow("Abhijit Roy"));
	}
}

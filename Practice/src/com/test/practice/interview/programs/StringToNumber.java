package com.test.practice.interview.programs;

public class StringToNumber {
	public static int stringToNumber(String number){
		int result=0;
		char [] numberArray=number.toCharArray();
		int zerAscii=(int)'0';
		for(char c:numberArray){
			result=result*10+((int)c-zerAscii);
		}
		return result;
	}
	
	public static void main(String ... args){
		  String a="0034210";
	        System.out.println("Given String Number: "+a );
	        System.out.println("Integer Number: "+stringToNumber(a));
	}
}

package com.test.practice.interview.programs;

public class StringReverse {

	static String reverseString="";

	private static String reverseString(String str){
		if(str.length()==1){
			return str;
		}else{
			reverseString+=str.charAt(str.length()-1)+reverseString(str.substring(0, str.length()-1));
			return reverseString;
		}
	}

	public static void main(String [] args){
		String test="Abhijit Roy";
		System.out.println("Orginal String: "+test);
		System.out.println("Reversed String: "+reverseString(test));
	}
}

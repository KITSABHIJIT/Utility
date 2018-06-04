package com.test.practice.interview.programs;

public class BinaryCheck {
	public static boolean isBinary(int number){
		boolean isBinary=true;
		while(number>0){
			int temp=number%10;
			if(temp>1){
				isBinary=false;
			}
			number=number/10;
		}
		return isBinary;
	}
	
	 public static void main(String ... args){
	        int a=111;
	        System.out.println("Given Binary Number: "+a );
	        System.out.println("Decimal Number: "+isBinary(a));
	    }
}

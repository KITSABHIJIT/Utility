package com.test.practice.interview.programs;

public class NumberReverse {

	private static int reverseNumber(int number){
		int reverse=0;
		while(number!=0){
			reverse=(reverse*10)+(number%10);
			number=number/10;
		}
		return reverse;
	}
	
	public static void main(String [] args){
		int number=123456;
		System.out.println("Orginal Number: "+number);
		System.out.println("Reversed Number: "+reverseNumber(number));
	}
}

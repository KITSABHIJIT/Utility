package com.test.practice.interview.programs;

public class DecimalToBinary {

	private static void convertToBinary(int decimal){
		int [] binary=new int[25];
		int index=0;
		while(decimal>0){
			binary[index++]=decimal%2;
			decimal=decimal/2;
		}
		for(int i=index-1;i>=0;i--){
			System.out.print(binary[i]);
		}
	}
	
	public static void main(String[] args){
		int number=1001;
		System.out.println("Orginal Number: "+number);
		convertToBinary(number);
	}
}

package com.test.practice.interview.programs;

public class BinaryToDecimal {
	private static int convertToDecimal(int binary){
		int decimal=0;
		int power=0;
		while(true){
			if(binary==0){
				break;
			}else{
				int temp=binary%10;
				decimal+=temp*Math.pow(2,power);
				binary=binary/10;
				power++;
			}
		}
		return decimal;        
	}
    
    public static void main(String ... args){
        int a=10;
        System.out.println("Given Binary Number: "+a );
        System.out.println("Decimal Number: "+convertToDecimal(a));
    }
}

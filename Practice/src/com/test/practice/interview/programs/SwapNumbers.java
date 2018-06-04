package com.test.practice.interview.programs;

public class SwapNumbers {
	 private static void swapNumbers(int num1,int num2){
	        System.out.println("Number 1: "+num1);
	        System.out.println("Number 2: "+num2);
	        num1=num1+num2;
	        num2=num1-num2;
	        num1=num1-num2;
	        System.out.println("After Swap: ");
	        System.out.println("Number 1: "+num1);
	        System.out.println("Number 2: "+num2);
	    }
	    
	    public static void main(String ... args){
	        int a=10,b=32;
	        swapNumbers(a,b);
	    }
}

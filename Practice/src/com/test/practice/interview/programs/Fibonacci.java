package com.test.practice.interview.programs;

public class Fibonacci {
	 private static void generateSeries(int length){
	        int [] fibonacci=new int[length];
	        fibonacci[0]=0;
	        fibonacci[1]=1;
	        for(int i=2;i<length;i++){
	            fibonacci[i]=fibonacci[i-1]+fibonacci[i-2];
	        }
	        System.out.print("fibonacci Series");
	        for(int i:fibonacci){
	            System.out.print(" "+i);
	        }
	    }
	    public static void main(String ... args){
	        int a=20;
	        generateSeries(a);
	    }
}

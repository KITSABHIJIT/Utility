package com.test.practice.interview.programs;

public class FloydTraingle {
	public static void printFloydTraingle(int level){
		int n=0;
        for(int i=0; i<level; i++){
            for(int j=0; j<=i; j++){
                System.out.print(++n+" ");
            }
            System.out.println();
        }
	}
	
	public static void main(String ...strings){
		printFloydTraingle(10);
	}
}

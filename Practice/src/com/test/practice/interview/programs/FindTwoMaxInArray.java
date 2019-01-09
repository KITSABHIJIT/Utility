package com.test.practice.interview.programs;

public class FindTwoMaxInArray {
	 private static int maxOne=0;
	    private static int maxTwo=0;
	    
	    private static void findMax(int [] data){
	        for(int n: data){
	            if(maxOne<n){
	                maxTwo=maxOne;
	                maxOne=n;
	            }else if(maxTwo<n){
	                maxTwo=n;
	            }
	        }
	    }
	    
	    public static void main(String[] args){
	        int [] data={34,6,23,67,8,2,90,4,5,6};
	        findMax(data);
	        System.out.println("Max Number: "+maxOne);
	        System.out.println("Second Max Number: "+maxTwo);
	    }
}

package com.test.practice.interview.programs;

public class FindCommonInArray {
	  private static void findCommon(int [] array1,int [] array2){
	        for(int i=0;i<array1.length;i++){
	            for(int j=0;j<array2.length;j++){
	            if(array1[i]==array2[j]){
	                System.out.println("Common element: "+array1[i]);
	            }
	        }
	    }
	    }
	    
	    
	    public static void main(String [] args){
	        int [] ar1={1,2,6,98,4,7,9,4,4,8};
	        int [] ar2={51,2,56,98,4,75,9,54,764,8};
	        findCommon(ar1,ar2);
	    }
}

package com.test.practice.hackerrank;

import java.io.IOException;
import java.util.Scanner;

public class TimeInWords {
	// Complete the timeInWords function below.
    static String timeInWords(int h, int m) {
    	String result=null;
    	final String[] tensNames = {
    		    "",
    		    "ten",
    		    "twenty",
    		    "thirty",
    		    "forty",
    		    "fifty"
    		  };

    		  final String[] numNames = {
    		    "",
    		    "one",
    		    "two",
    		    "three",
    		    "four",
    		    "five",
    		    "six",
    		    "seven",
    		    "eight",
    		    "nine",
    		    "ten",
    		    "eleven",
    		    "twelve",
    		    "thirteen",
    		    "fourteen",
    		    "fifteen",
    		    "sixteen",
    		    "seventeen",
    		    "eighteen",
    		    "nineteen",
    		  };
    	
    	if(m==0) {
    		result=numNames[h]+" o' clock";
    	}else if(m>0 && m<=30) {
    		if(m==1) {
    			result=numNames[m]+" minute past "+numNames[h];
    		}else if(m==15) {
    			result="quarter past "+numNames[h];
    		}else if(m==30) {
    			result="half past "+numNames[h];
    		}else if(m>19) {
    			result=tensNames[m/10]+" "+numNames[m%10]+" minutes past "+numNames[h];
    		}else {
    			result=numNames[m]+" minutes past "+numNames[h];
    		}
    	}else if(m>30) {
    		if(m==45) {
    			result="quarter to "+numNames[h+1];
    		}else if(60-m>19) {
    			result=tensNames[(60-m)/10]+" "+numNames[(60-m)%10]+" minutes to "+numNames[h+1];
    		}else {
    			result=numNames[60-m]+" minutes to "+numNames[h+1];
    		}
    	}
    	return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int h = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String result = timeInWords(h, m);

        System.out.println(result);

        scanner.close();
    }
}

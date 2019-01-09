package com.test.practice.hackerrank;

import java.io.IOException;
import java.util.Scanner;

public class CountingValleys {
	 static int countingValleys(int n, String s) {
		 int valleyCount=0;
		 char[] stepsArr=s.toCharArray();
		 int altitude=0;
		 for(int i=0;i<stepsArr.length;i++) {
			 if(i==0) {
				 altitude=(stepsArr[i]=='D')?-1:1;
			 }else {
				 altitude=altitude+((stepsArr[i]=='D')?-1:1);
			 }
			 
			 if(i>0 && altitude==0 && (stepsArr[i]=='U')) {
				 valleyCount++;
			 }
		 }
		 return valleyCount;
	    }

	    private static final Scanner scanner = new Scanner(System.in);

	    public static void main(String[] args) throws IOException {

	        int n = scanner.nextInt();
	        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

	        String s = scanner.nextLine();

	        int result = countingValleys(n, s);

	        System.out.println(String.valueOf(result));


	        scanner.close();
	    }
}

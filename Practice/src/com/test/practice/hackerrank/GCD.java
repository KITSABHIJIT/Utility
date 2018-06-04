package com.test.practice.hackerrank;

import java.util.Arrays;

public class GCD {

	public int generalizedGCD(int num, int[] arr)
    {
        // WRITE YOUR CODE HERE
		Arrays.sort(arr);
		int divisor=0;
		int gcd=1;
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr.length;j++) {
				if(i!=j) {
					divisor=divisor+(arr[j]%arr[i]);
				}
			}
			if(divisor==0) {
				gcd=arr[i];
				break;
			}
		}
		return gcd;
    }
    // METHOD SIGNATURE ENDS
	
	public int generateGCD(int num, int[] arr)
    {
		int result=arr[0];
		for(int i=1;i<num;i++) {
			result = getGCD(arr[i], result);
		}
		return result;
    }
	
	public int getGCD(int a, int b)
    {
		if(a==0) {
			return b;
		}else {
			return getGCD(b%a,a);
		}
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GCD gcd=new GCD();
		int [] data=new int[] {10,8,6,20,4};
		System.out.println(gcd.generateGCD(5, data));

	}

}

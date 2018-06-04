package com.test.practice.hackerrank;

import java.util.Scanner;

public class MiniMaxSum {
	/*
	 * Complete the miniMaxSum function below.
	 */
	static void miniMaxSum(int[] arr) {
		/*
		 * Write your code here.
		 */
		int length=arr.length;
		long maxSum=0;
		long minSum=0;
		for(int i=0;i<length;i++){
			long tempSum=0;
			for(int j=0;j<length;j++){                
				if(i!=j){
					tempSum=tempSum+arr[j];
				}
			}
			if(i==0){
				maxSum= tempSum;
				minSum= tempSum;
			}else{
				if(maxSum<tempSum){
					maxSum=tempSum;
				}
				if(minSum>tempSum){
					minSum=tempSum;
				}
			}
		}

		System.out.println(minSum+" "+maxSum);
	}

	private static final Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		int[] arr = new int[5];

		String[] arrItems = scan.nextLine().split(" ");

		for (int arrItr = 0; arrItr < 5; arrItr++) {
			int arrItem = Integer.parseInt(arrItems[arrItr].trim());
			arr[arrItr] = arrItem;
		}

		miniMaxSum(arr);
	}
}

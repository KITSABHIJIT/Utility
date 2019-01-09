package com.test.practice.hackerrank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class BirthdayCakeCandles {

	/*
	 * Complete the birthdayCakeCandles function below.
	 */
	static int birthdayCakeCandles(int n, int[] ar) {
		/*
		 * Write your code here.
		 */
		Arrays.sort(ar);
		long maxHeight=ar[ar.length-1];
		int noOfMaxHeightCandles=0;
		for(int i:ar){
			if(i==maxHeight){
				noOfMaxHeightCandles++;
			}
		}
		return noOfMaxHeightCandles;
	}

	private static final Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int n = Integer.parseInt(scan.nextLine().trim());

		int[] ar = new int[n];

		String[] arItems = scan.nextLine().split(" ");

		for (int arItr = 0; arItr < n; arItr++) {
			int arItem = Integer.parseInt(arItems[arItr].trim());
			ar[arItr] = arItem;
		}

		int result = birthdayCakeCandles(n, ar);

		bw.write(String.valueOf(result));
		bw.newLine();

		bw.close();
	}
}

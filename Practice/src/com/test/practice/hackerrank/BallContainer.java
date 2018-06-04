package com.test.practice.hackerrank;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class BallContainer {

	// Complete the organizingContainers function below.
	static String organizingContainers(int[][] container) {
		String ans = "Possible";
		int[] rt = new int[container.length];
		int[] ct = new int[container.length];
		for (int i = 0; i < container.length; i++) {
			for (int j = 0; j < container.length; j++) {
				rt[i] += container[i][j];
				ct[j] += container[i][j];
			}
		}
		Arrays.sort(rt);
		Arrays.sort(ct);

		for (int i = 0; i < container.length; i++) {
			if (rt[i] != ct[i])
				ans = "Impossible";
		}
		return ans;


	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		int q = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int qItr = 0; qItr < q; qItr++) {
			int n = scanner.nextInt();
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			int[][] container = new int[n][n];

			for (int i = 0; i < n; i++) {
				String[] containerRowItems = scanner.nextLine().split(" ");
				scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

				for (int j = 0; j < n; j++) {
					int containerItem = Integer.parseInt(containerRowItems[j]);
					container[i][j] = containerItem;
				}
			}

			String result = organizingContainers(container);

			System.out.println(result);
		}


		scanner.close();
	}
}

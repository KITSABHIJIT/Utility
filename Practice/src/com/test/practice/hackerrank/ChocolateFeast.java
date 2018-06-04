package com.test.practice.hackerrank;

import java.io.IOException;
import java.util.Scanner;

public class ChocolateFeast {
	// Complete the chocolateFeast function below.
	static int chocolateFeast(int n, int c, int m) {
		int chocolateBought=n/c;
		int wrapper=chocolateBought;
		while(wrapper>=m) {
			chocolateBought=chocolateBought+(wrapper/m);
			wrapper=(wrapper/m)+(wrapper%m);
		}
		return chocolateBought;
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		int t = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int tItr = 0; tItr < t; tItr++) {
			String[] ncm = scanner.nextLine().split(" ");

			int n = Integer.parseInt(ncm[0]);

			int c = Integer.parseInt(ncm[1]);

			int m = Integer.parseInt(ncm[2]);

			int result = chocolateFeast(n, c, m);

			System.out.println(String.valueOf(result));
		}

		scanner.close();
	}
}

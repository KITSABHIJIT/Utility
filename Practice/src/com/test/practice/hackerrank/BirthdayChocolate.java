package com.test.practice.hackerrank;

import java.util.Scanner;

public class BirthdayChocolate {
	static int solve(int n, int[] s, int d, int m){
		// Complete this function
		int combinations=0;
		for(int i=0;i<n;i++){
			int tempSum=0;
			if(i+m<=n) {
				for(int j=i;j<i+m;j++){
					tempSum=tempSum+s[j];
				}
			}
			if(tempSum==d){
				combinations++;
			}
		}
		return combinations;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] s = new int[n];
		for(int s_i=0; s_i < n; s_i++){
			s[s_i] = in.nextInt();
		}
		int d = in.nextInt();
		int m = in.nextInt();
		int result = solve(n, s, d, m);
		System.out.println(result);
	}
}

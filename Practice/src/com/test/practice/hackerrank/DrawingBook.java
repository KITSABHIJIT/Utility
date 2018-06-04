package com.test.practice.hackerrank;

import java.io.IOException;
import java.util.Scanner;

public class DrawingBook {
	static int pageCount(int n, int p) {
		int pageFlippedFromStart=0;
		int pageFlippedFromEnd=0;
		for(int i=2;i<=n;i++) {
			if(i%2==0) {
				if(i<=p) {
					pageFlippedFromStart++;
				}else {
					break;
				}
			}
		}
		
		for(int i=n-1;i>=0;i--) {
			if(i%2!=0) {
				if(i>=p) {
					pageFlippedFromEnd++;
				}else {
					break;
				}
			}
		}
		
		if(pageFlippedFromStart>pageFlippedFromEnd) {
			return pageFlippedFromEnd;
		}else {
			return pageFlippedFromStart;
		}
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        int p = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        int result = pageCount(n, p);

        System.out.println(String.valueOf(result));


        scanner.close();
    }
}

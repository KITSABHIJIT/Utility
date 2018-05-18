package com.test.practice.hackerrank;

import java.io.IOException;
import java.util.Scanner;

public class ElectronicsShop {
	
	static int getMoneySpent(int[] keyboards, int[] drives, int b) {
		int output=-1;
        for(int i=0;i<keyboards.length;i++){
            for(int j=0;j<drives.length;j++){
                if(keyboards[i]+drives[j]<=b) {
                	if(keyboards[i]+drives[j]>output)
                		output=keyboards[i]+drives[j];
                }
            }
        }
        return output;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        String[] bnm = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        int b = Integer.parseInt(bnm[0]);

        int n = Integer.parseInt(bnm[1]);

        int m = Integer.parseInt(bnm[2]);

        int[] keyboards = new int[n];

        String[] keyboardsItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        for (int keyboardsItr = 0; keyboardsItr < n; keyboardsItr++) {
            int keyboardsItem = Integer.parseInt(keyboardsItems[keyboardsItr]);
            keyboards[keyboardsItr] = keyboardsItem;
        }

        int[] drives = new int[m];

        String[] drivesItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        for (int drivesItr = 0; drivesItr < m; drivesItr++) {
            int drivesItem = Integer.parseInt(drivesItems[drivesItr]);
            drives[drivesItr] = drivesItem;
        }

        /*
         * The maximum amount of money she can spend on a keyboard and USB drive, or -1 if she can't purchase both items
         */

        int moneySpent = getMoneySpent(keyboards, drives, b);

        System.out.println(String.valueOf(moneySpent));


        scanner.close();
    }
}

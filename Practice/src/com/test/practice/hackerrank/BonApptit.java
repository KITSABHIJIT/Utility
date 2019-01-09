package com.test.practice.hackerrank;

import java.util.Scanner;

public class BonApptit {
	 private static final Scanner scanner = new Scanner(System.in);
	    public static void main(String args[] ) throws Exception {
	        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
	    	String input1=scanner.nextLine();
	        int dishOrdered=Integer.parseInt(input1.split(" ")[0]);
	        int anaRejectedIndex=Integer.parseInt(input1.split(" ")[1]);
	        int [] bills =new int[dishOrdered];
	        String input2=scanner.nextLine();
	        String [] dishOrderedArr=input2.split(" ");
	        for(int i=0;i<dishOrdered;i++){
	            bills[i]=Integer.parseInt(dishOrderedArr[i]);
	        }
	        long anaCharged=Long.parseLong(scanner.nextLine());
	        
	        long totalBill=0;
	        for(int bill=0;bill<bills.length;bill++){
	            totalBill=(bill!=anaRejectedIndex)?(totalBill+bills[bill]):totalBill;
	        }
	        long anaShare=totalBill/2;
	        if(anaCharged==anaShare){
	            System.out.println("Bon Appetit");
	        }else{
	            System.out.println(anaCharged-anaShare);
	        }
	    }
}

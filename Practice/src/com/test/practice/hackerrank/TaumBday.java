package com.test.practice.hackerrank;

import java.io.IOException;
import java.util.Scanner;

public class TaumBday {
	 // Complete the taumBday function below.
	 static long taumBday(long b, long w, long bc, long wc, long z) {
		 long totalPrice=0;
	        if(bc==wc){
	            totalPrice=(b*bc+w*wc);
	        }else{
	            if(bc>wc){
	                if(bc>z){
	                    if((((b+w)*wc)+(b*z))<((w*wc)+(b*bc))){
	                        totalPrice=(((b+w)*wc)+(b*z));
	                    }else{
	                         totalPrice=((b*bc)+(w*wc));
	                    }
	                    
	                }else{
	                     totalPrice=(b*bc+w*wc);
	                }
	            }else{
	                if(wc>z){
	                    if((((b+w)*bc)+(w*z))<((w*wc)+(b*bc))){
	                        totalPrice=(((b+w)*bc)+(w*z));
	                    }else{
	                    	 totalPrice=((b*bc)+(w*wc));
	                    }
	                }else{
	                	 totalPrice=((b*bc)+(w*wc));
	                }
	            }
	        }
	         return totalPrice;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String[] bw = scanner.nextLine().split(" ");

            long b = (long)(Integer.parseInt(bw[0]));

            long w = (long)(Integer.parseInt(bw[1]));

            String[] bcWcz = scanner.nextLine().split(" ");

            long bc = (long)(Integer.parseInt(bcWcz[0]));

            long wc = (long)(Integer.parseInt(bcWcz[1]));

            long z = (long)(Integer.parseInt(bcWcz[2]));

            long result = taumBday(b, w, bc, wc, z);

            System.out.println(String.valueOf(result));
        }


        scanner.close();
    }
}

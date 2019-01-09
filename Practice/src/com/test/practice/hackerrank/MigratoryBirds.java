package com.test.practice.hackerrank;

import java.io.IOException;
import java.util.Scanner;

public class MigratoryBirds {

    // Complete the migratoryBirds function below.
    static int migratoryBirds(int n, int[] ar) {
        int birdId=0;
        int maxBirdCount=0;
        for(int i=0;i<n;i++){   
            int birdCount=0;
            for(int j=0;j<n;j++){
                if(ar[i]==ar[j]){
                    birdCount++;
                }
            }
            if(maxBirdCount<birdCount){
                maxBirdCount=birdCount;
                birdId=ar[i];
            }
        }
        return birdId;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int arCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] ar = new int[arCount];

        String[] arItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int arItr = 0; arItr < arCount; arItr++) {
            int arItem = Integer.parseInt(arItems[arItr]);
            ar[arItr] = arItem;
        }

        int result = migratoryBirds(arCount, ar);

       System.out.println(String.valueOf(result));


        scanner.close();
    }
}

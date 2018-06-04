package com.test.practice.hackerrank;

import java.io.IOException;
import java.util.Scanner;

public class CavityMap {
	// Complete the cavityMap function below.
    static String[] cavityMap(String[] grid) {
    	String [] result=new String[grid.length];
        for(int i=0;i<grid.length;i++){
            if(i==0 || i==(grid.length-1)){
                result[i]=grid[i];
            }else{
                char[] rowsChars=grid[i].toCharArray();
                char[] rowsCharsPrevious=grid[i-1].toCharArray();
                char[] rowsCharsNext=grid[i+1].toCharArray();
                String newRow="";
                for(int j=0;j<rowsChars.length;j++){
                    if(j==0 || j==(rowsChars.length-1)){
                     newRow=newRow+rowsChars[j];
                    }else{
                        if(rowsChars[j]>rowsCharsPrevious[j] 
                          && rowsChars[j]>rowsCharsNext[j] 
                          && rowsChars[j]>rowsChars[j-1] 
                          && rowsChars[j]>rowsChars[j+1]){
                            newRow=newRow+'X';
                        }else{
                            newRow=newRow+rowsChars[j];
                        }
                    }
                }
                result[i]=newRow;
              }  
           }
         return result;
        }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] grid = new String[n];

        for (int i = 0; i < n; i++) {
            String gridItem = scanner.nextLine();
            grid[i] = gridItem;
        }

        String[] result = cavityMap(grid);

        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);

            if (i != result.length - 1) {
            	System.out.println("\n");
            }
        }

        scanner.close();
    }
}

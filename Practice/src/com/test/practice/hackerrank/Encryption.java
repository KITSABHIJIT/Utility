package com.test.practice.hackerrank;

import java.io.IOException;
import java.util.Scanner;

public class Encryption {
	 // Complete the encryption function below.
    static String encryption(String s) {
        s=s.replaceAll(" ","");
        int lenght=s.length();
        double square=Math.sqrt(lenght);
        double columns=Math.ceil(square);
        double rows=(square== columns)?columns:(columns-1);
        if((columns*rows)<lenght) {
        	rows=rows+1;
        }
        
        String[][] encodedData=new String[(int)rows][(int)columns];
        char[] data=s.toCharArray();
        String output="";
        int counter=0;
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                if(counter==lenght){
                    break;
                }
                encodedData[i][j]=String.valueOf(data[counter]);
                counter++;
            }
        }
        for(int i=0;i<columns;i++){
            for(int j=0;j<rows;j++){
            	output=(null==encodedData[j][i])?output:output+encodedData[j][i];
            }
            output=output+" ";
            
        }
        
        return output;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        String s = scanner.nextLine();

        String result = encryption(s);

        System.out.println(result);

        scanner.close();
    }
}

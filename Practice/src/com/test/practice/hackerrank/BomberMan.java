package com.test.practice.hackerrank;

import java.io.IOException;
import java.util.Scanner;

public class BomberMan {
	static String[] bomberMan(int n, String[] grid) {
		int rows=grid.length;
		int columns=grid[0].toCharArray().length;
		char[][] previousBombGrid=new char[rows][columns];
		char[][] currentBombGrid=new char[rows][columns];
		
		String [] output =new String [rows];
		for(int i=0;i<rows;i++) {
			for(int j=0;j<columns;j++) {
				previousBombGrid[i][j]=grid[i].toCharArray()[j];
			}
		}
		for(int k=1;k<=n;k++) {
			if(k==1) {
				for(int m=0;m<rows;m++){
					for(int p=0;p<columns;p++){
						currentBombGrid[m][p]=previousBombGrid[m][p];
					}
				}
			}
			if(k>1 &&  k%2==0){
				for(int i=0;i<rows;i++){
					for(int j=0;j<columns;j++){
						currentBombGrid[i][j]='O';
					}
				}
			}
			if(k>1 && k%2!=0){
				for(int i=0;i<rows;i++) {
					for(int j=0;j<columns;j++) {
						if(previousBombGrid[i][j]=='O') {
							currentBombGrid[i][j]='.';
							if((j+1)<columns) {
								currentBombGrid[i][j+1]='.';
							}
							if((j-1)>=0) {
								currentBombGrid[i][j-1]='.';
							}
							if((i+1)<rows) {
								currentBombGrid[i+1][j]='.';
							}
							if((i-1)>=0) {
								currentBombGrid[i-1][j]='.';
							}
						}
					}
				}
				for(int m=0;m<rows;m++){
					for(int p=0;p<columns;p++){
						previousBombGrid[m][p]=currentBombGrid[m][p];
					}
				}
			}
		}
		for(int i=0;i<rows;i++) {
			String temp="";
			for(int j=0;j<columns;j++) {
				temp=temp+currentBombGrid[i][j];
			}
			output[i]=temp;
		}
		return output;
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		String[] rcn = scanner.nextLine().split(" ");

		int r = Integer.parseInt(rcn[0]);

		int c = Integer.parseInt(rcn[1]);

		int n = Integer.parseInt(rcn[2]);

		String[] grid = new String[r];

		for (int i = 0; i < r; i++) {
			String gridItem = scanner.nextLine();
			grid[i] = gridItem;
		}

		String[] result = bomberMan(n, grid);

		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i]);

			if (i != result.length - 1) {
				System.out.print("\n");
			}
		}
		scanner.close();
	}
}

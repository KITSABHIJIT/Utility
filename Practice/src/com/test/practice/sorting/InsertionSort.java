package com.test.practice.sorting;

public class InsertionSort {
	/**
	 * http://www.java2novice.com/java-sorting-algorithms/insertion-sort/
	 * @param array
	 */
	public static void insertionSort(int[] array){
		int stepCount=1;
		System.out.print("Original List: ");
		printNumbers(array);
		for(int i=1;i<array.length;i++){
			for(int j=i;j>0;j--){
				if(array[j]<array[j-1]){
					swapNumbers(j, j-1, array);
				}
			}
		}
		System.out.print("Step "+stepCount+": ");
		printNumbers(array);
		stepCount++;
		
		
		
	}
	private static void swapNumbers(int i,int j, int[] array){
		int temp=array[i];
		array[i]=array[j];
		array[j]=temp;
	}
	
	private static void printNumbers(int[] input) {
        for (int i = 0; i < input.length; i++) {
            System.out.print(input[i] + ", ");
        }
        System.out.println("\n");
    }
	
	static public void main(String [] args){
		int[] input = { 4, 2, 9, 6, 23, 12, 34, 0, 1 };
		insertionSort(input);
	}
}

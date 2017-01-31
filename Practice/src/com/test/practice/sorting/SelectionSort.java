package com.test.practice.sorting;

public class SelectionSort {
	/**
	 * http://www.java2novice.com/java-sorting-algorithms/selection-sort/
	 * @param array
	 */
	public static void selectionSort(int[] array){
		System.out.print("Original List: ");
		printNumbers(array);
		int stepCount=1;
		for(int i=0;i<array.length-1;i++){
			int index=i;
			for(int j=i+1;j<array.length;j++){
				if(array[j]<array[index]){
					index=j;
				}
			}
			swapNumbers(index,i,array);
			System.out.print("Step "+stepCount+": ");
			printNumbers(array);
			stepCount++;
		}
	}

	private static void swapNumbers(int i, int j, int[] array){
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
		selectionSort(input);
	}
}

package com.test.practice.sorting;

public class BubbleSort {

	/**
	 * 
	 * @param array
	 * http://www.java2novice.com/java-sorting-algorithms/bubble-sort/
	 * worst-case or average complexity of O(n log n)
	 */
	public static void bubbleSort(int [] array){
		int m=array.length;
		int k;
		int stepCount=1;
		System.out.print("Original List: ");
		printNumbers(array);
		for(int i=m;i>=0;i--){
			for(int j=0;j<m-1;j++){
				k=j+1;
				if (array[j] > array[k]) {
                    swapNumbers(j, k, array);
                }
			}
			System.out.print("Step "+stepCount+": ");
			printNumbers(array);
			stepCount++;
		}
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
		bubbleSort(input);
	}
}

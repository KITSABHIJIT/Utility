package com.test.practice.sorting;

/**
 * http://www.java2novice.com/java-sorting-algorithms/quick-sort/
 * @author RoyAb001
 *
 */
public class QuickSort {
	static int length;
	static int[] array;
	public static void sort(int[] input){
		if(input==null || input.length==0){
			return;
		}
		System.out.print("Original List: ");
		printNumbers(input);
		length=input.length;
		array=input;
		quickSort(0,length-1);
		System.out.print("Sorted List: ");
		printNumbers(array);
	}

	private static void quickSort(int lowerIndex, int higherIndex){
		int i=lowerIndex;
		int j=higherIndex;
		int pivotElement=array[lowerIndex+(higherIndex-lowerIndex)/2];
		while(i<=j){
			while(array[i]<pivotElement){
				i++;
			}
			while(array[j]>pivotElement){
				j--;
			}
			if(i<=j){
				swapNumbers(i, j);
				i++;
				j--;
			}
		}
		
		if(lowerIndex<j){
			quickSort(lowerIndex, j);
		}
		if(i<higherIndex){
			quickSort(i, higherIndex);
		}
	}
	private static void swapNumbers(int i,int j){
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
		//int[] input = { 9, 2, 4, 7, 3, 7, 10 };
		sort(input);
	}
}

package com.test.practice.sorting;

public class MergeSort {
	private int length;
	private int[] array;
	private int[] tempArray;

	public void sort(int[] input){
		if(input==null || input.length==0){
			return;
		}
		System.out.print("Original List: ");
		printNumbers(input);
		this.length=input.length;
		this.array=input;
		this.tempArray=new int[length];
		mergeSort(0,length-1);
		System.out.print("Sorted List: ");
		printNumbers(array);
	}

	private void mergeSort(int lowerIndex,int higherIndex){
		if(lowerIndex<higherIndex){
			int middle=lowerIndex+(higherIndex-lowerIndex)/2;
			mergeSort(lowerIndex, middle);
			mergeSort(middle+1, higherIndex);
			mergeParts(lowerIndex,middle,higherIndex);
		}
	}

	private void mergeParts(int lowerIndex, int middle, int higherIndex){
		for(int i=lowerIndex;i<=higherIndex;i++){
			tempArray[i]=array[i];
		}
		int i=lowerIndex;
		int j=middle+1;
		int k= lowerIndex;
		while(i<=middle && j<=higherIndex){
			if(tempArray[i]<=tempArray[j]){
				array[k]=tempArray[i];
				i++;
			}else{
				array[k]=tempArray[j];
				j++;
			}
			k++;
		}
		while(i<=middle){
			array[k]=tempArray[i];
			k++;
			i++;
		}
	}
	private void printNumbers(int[] input) {
		for (int i = 0; i < input.length; i++) {
			System.out.print(input[i] + ", ");
		}
		System.out.println("\n");
	}

	static public void main(String [] args){
		int[] input = { 4, 2, 9, 6, 23, 12, 34, 0, 1 };
		//int[] input = { 9, 2, 4, 7, 3, 7, 10 };
		MergeSort mergeSort=new MergeSort();
		mergeSort.sort(input);
	}
}

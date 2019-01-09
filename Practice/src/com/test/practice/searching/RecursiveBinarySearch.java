package com.test.practice.searching;

public class RecursiveBinarySearch {
	static String position="";
	public static void main(String[] args){
		int[] array={4, 2, 9, 6, 23,23, 12, 34, 0, 1};
		System.out.println("Given list: "+array);
		System.out.println("23 is present in the list with position: "+search(array, 23));
	}
	
	private static String search(int[] array,int key){
		int start=0;
		int end=array.length-1;
		return binarySearch(array, start, end, key);
	}
	private static String binarySearch(int [] array, int start,int end, int key){
		
		if(start<end){
			int mid=start+(end-start)/2;
			if(key<array[mid]){
				binarySearch(array, start, mid, key);
			}else if(key>array[mid]){
				binarySearch(array, mid+1, end, key);
			}else{
				position= ("".equals(position))?position+(mid+1):position+","+(mid+1);
			}
		}
		return position;
	}
}

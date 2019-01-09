package com.test.practice.searching;

public class BinarySearch {

	public static String binarySearch(int key, int[] array){
		String position="";
		int start=0;
		int end=array.length-1;
		while(start<=end){
			int middle=(start+end)/2;
			if(key==array[middle]){
				position=("".equals(position))?position+(middle+1):position+","+(middle+1);
			}
			if(key<array[middle]){
				end=middle-1;
			}else{
				start=middle+1;
			}
		}
		return position;
	}
	
	public static void main(String[] args){
		int[] array={4, 2, 9, 6, 23,23, 12, 34, 0, 1};
		System.out.println("Given list: "+array);
		System.out.println("23 is present in the list with position: "+binarySearch(23, array));
	}
}

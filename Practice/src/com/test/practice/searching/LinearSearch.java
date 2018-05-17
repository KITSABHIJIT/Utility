package com.test.practice.searching;

public class LinearSearch {

	public static String linearSearch(int value, int[] array){
		String position="";
		for(int i=0;i<array.length-1;i++){
			if(array[i]==value){
				position=("".equals(position))?position+(i+1):position+","+(i+1);
			}
		}
		return position;
	}
	
	public static void main(String[] args){
		int[] array={4, 2, 9, 6, 23,23, 12, 34, 0, 1};
		System.out.println("Given list: "+array);
		System.out.println("23 is present in the list with position: "+linearSearch(23, array));
	}
}

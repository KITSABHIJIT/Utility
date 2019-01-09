package com.test.practice.interview.programs;

public class DisticntElementsArray {

	public static void printDistinct(int [] array){
		
		for(int i=0;i<array.length;i++){
			boolean distinct=true;
			for(int j=0;j<i;j++){
				if(array[i]==array[j]){
					distinct=false;
					break;
				}
			}
			if(distinct){
				System.out.print(array[i]+" ");
			}
		}
	}
	
	public static void main(String ... args){
		int [] data={1,2,3,3,5,6,77,7,6,3,2,1};
		printDistinct(data);
	}
}

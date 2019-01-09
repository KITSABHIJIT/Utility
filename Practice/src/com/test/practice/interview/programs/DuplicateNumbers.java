package com.test.practice.interview.programs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DuplicateNumbers {
	private static String findDuplicateNumber(List<Integer> numbers){
		String duplicate="";
		int numberCount=numbers.size();
		int[] numberArray=new int[numberCount];
		for(int i=0;i<numbers.size();i++){
			numberArray[i]=numbers.get(i);
		}
		Arrays.sort(numberArray);
		for(int i=1;i<numberArray.length;i++){
			if(numberArray[i]==numberArray[i-1]){
				duplicate= ("".equals(duplicate))?duplicate+(numberArray[i]):duplicate+","+(numberArray[i]);
			}
		}
	return duplicate;
	}
	
	public static void main(String[] args){
		List<Integer> data=new ArrayList<Integer>();
		for(int i=10;i<=20;i++){
			data.add(i);
		}
		data.add(18);
		data.add(14);
		System.out.println("Given List: "+data.toString());
		System.out.println("Duplicate Number: "+findDuplicateNumber(data));
	}
}

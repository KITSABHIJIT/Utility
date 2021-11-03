package com.test.practice.searching;

//Name: J1-018-21
//Date: 10/26/21
import java.io.*;      //imports File 
import java.util.*;    //imports Scanner 

public class Searches_Driver
{
	private static int amount = 1325;

	public static void main(String[] args) throws Exception
	{
		String[] apple = input("/Users/abhijit/Downloads/declaration.txt");
		Arrays.sort(apple);  
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			System.out.print("Enter a word: ");
			String target = sc.next();   //Liberty  
			if(target.equals("-1") )
				break;
			Searches.reset();
			int found = Searches.linear(apple, target);
			if(found == -1)
				System.out.println(target + " was not found by the linear search.");
			else
				System.out.println("Linear Search found it at location "+found+" in " + Searches.getLinearCount()+" comparisons.");
			int found2 = Searches.binary(apple, target);
			if(found2 == -1)
				System.out.println(target + " was not found by the binary search.");
			else
				System.out.println("Binary Search found it at location "+found2+" in " + Searches.getBinaryCount()+" comparisons.");
		}
	}

	public static String[] input(String filename) throws Exception
	{
		Scanner infile = new Scanner( new File(filename) );
		String[] array = new String[amount];
		for (int k =0; k<amount; k++) {
			if(infile.hasNext()) {
				array[k] = infile.next();
			}
		}
		infile.close();
		return array;
	}
}

/////////////////////////////////////////////////////////
class Searches
{
	private static int linearCount = 0;
	private static int binaryCount = 0;      

	public static int getLinearCount()
	{
		return linearCount;
	}

	public static int getBinaryCount()
	{
		return binaryCount;
	}

	public static void reset()
	{
		linearCount = 0;
		binaryCount = 0;
	}

	@SuppressWarnings("unchecked")//removes the warning for Comparable
	public static int linear(Comparable[] array, Comparable target)
	{ 
		for (int i = 0; i < array.length-1; i++)
		{
			linearCount++;
			// You are comparing string, hence you have to use a.equals(b)
			if (target.equals(array[i]))
			{
				return i;
			}
		}
		return -1;
	}

	@SuppressWarnings("unchecked")
	public static int binary(Comparable[] array, Comparable target)
	{
		int start = 0;
		int end = array.length-1;
		return binaryhelper(array, target, start, end);
	}

	@SuppressWarnings("unchecked")
	private static int binaryhelper(Comparable[] array, Comparable target, int start, int end)
	{
		binaryCount++;
		if(start<end){
			int mid=start+(end-start)/2;
			if(target.compareTo(array[mid]) < 0){
				return binaryhelper(array, target, start, mid);
			}else if(target.compareTo(array[mid]) > 0){
				return binaryhelper(array, target, mid+1, end);
			}else{
				return mid;
			}
		}
		return -1;
	}
}

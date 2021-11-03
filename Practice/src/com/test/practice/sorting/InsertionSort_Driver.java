package com.test.practice.sorting;

//Name: J1-018-21
//Date: 10/28/21

import java.util.*;
import java.io.*;

public class InsertionSort_Driver
{
	public static void main(String[] args) throws Exception
	{
		//Part 1, for doubles
		int n = (int)(Math.random()*100)+20;
		double[] array = new double[n];
		for(int k = 0; k < array.length; k++)
			array[k] = Math.random()*100;	

		Insertion.sort(array);  //students write
		print(array);

		if( isAscending(array) )
			System.out.println("In order!");
		else
			System.out.println("Out of order  :-( ");
		System.out.println();

		//Part 2, for Strings
		int size = 100;
		Scanner sc = new Scanner(new File("/Users/abhijit/Downloads/declaration.txt"));
		Comparable[] arrayStr = new String[size];
		for(int k = 0; k < arrayStr.length; k++)
			arrayStr[k] = sc.next();	

		Insertion.sort(arrayStr);   //students write
		print(arrayStr);
		System.out.println();

		if( isAscending(arrayStr) )
			System.out.println("In order!");
		else
			System.out.println("Out of order  :-( ");
	}

	public static void print(double[] a)
	{
		// for(int k = 0; k < a.length; k++)
		// System.out.println(a[k]);
		for(double temp: a)         //for-each
			System.out.print(temp+" ");
		System.out.println();
	}

	public static void print(Object[] papaya)
	{
		for(Object temp : papaya)    
			System.out.print(temp+" ");
	}

	public static boolean isAscending(double[] a)
	{
		for (int i = 0; i < a.length - 1; i++) 
		{
			if (a[i] > a[i + 1]) 
			{
				return false;
			}
		}

		return true;
	}

	@SuppressWarnings("unchecked")//this removes the warning for Comparable
	public static boolean isAscending(Comparable[] a)
	{
		for (int i = 0; i < a.length - 1; i++) 
		{
			if (a[i].compareTo(a[i + 1]) > 0)  
			{
				return false;
			}
		}

		return true;
	}
}

//********************

class Insertion
{
	public static void sort(double[] array)
	{ 
		for(int i=1;i<array.length;i++){
			for(int j=i;j>0;j--){
				if(array[j]<array[j-1]){
					shift(array,j-1,j);
				}
			}
		}
	}

	private static void shift(double[] array, int index, int value)
	{
		double temp=array[index];
		array[index]=array[value];
		array[value]=temp;	
	}

	@SuppressWarnings("unchecked")
	public static void sort(Comparable[] array)
	{ 
		for(int i=1;i<array.length;i++){
			for(int j=i;j>0;j--){
				if(array[j].compareTo(array[j-1]) < 0){
					shift(array,j-1,j);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private static void shift(Object[] array, int index, int value)
	{
		Object temp=array[index];
		array[index]=array[value];
		array[value]=temp;	
	}
}
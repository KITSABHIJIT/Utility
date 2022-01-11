package com.test.practice.collections;

import java.util.Arrays;

//Name: J1-018-21
//Date: 11/30/21

/**
 * Implements the cheat sheet's List interface.  Implements generics.
 * The backing array is an array of (E[]) new Object[10];
 * @override toString()
 */ 
public class TJArrayList<E>
{
	private int size;							//stores the number of objects
	private E[] myArray;
	public TJArrayList()                
	{
		myArray = (E[]) new Object[10]; //default constructor instantiates a raw array with 10 cells

		size = 0;
	}
	public int size()
	{
		return size;
	}
	/* appends obj to end of list; increases size;
   must be an O(1) operation when size < array.length, 
      and O(n) when it doubles the length of the array.
	  @return true  */
	public boolean add(E obj)
	{
		if(myArray.length == size)
		{
			E[] nArr = (E[]) new Object[myArray.length * 2];
			for(int i = 0; i < myArray.length; i++)
			{
				nArr[i] = myArray[i];
			}

			myArray = nArr;
		}

		myArray[size] = obj;
		size++;
		return true;
	}
	/* inserts obj at position index.  increments size. 
	 */
	public void add(int index, E obj) throws IndexOutOfBoundsException  //this the way the real ArrayList is coded
	{
		if(index > size || index < 0)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

		E[] nArr = (E[]) new Object[myArray.length];
		for(int i = 0; i < myArray.length; i++)
		{
			nArr[i] = myArray[i];
		}

		for(int i = index + 1; i < nArr.length; i++) 
		{
			nArr[i] = myArray[i - 1];
		}
		nArr[index] = obj;
		myArray = nArr;
		size++;
	}

	/* return obj at position index.  
	 */
	public E get(int index) throws IndexOutOfBoundsException
	{
		if(index >= size || index < 0)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

		return myArray[index];   
	}
	/**
	 * Replaces obj at position index. 
	 * @return the object is being replaced.
	 */  
	public E set(int index, E obj) throws IndexOutOfBoundsException  
	{ 
		E t = null;

		if(index >= size || index < 0) 
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

		t = myArray[index];
		myArray[index] = obj;

		return t;

	}
	/*  removes the node from position index. shifts elements 
  to the left.   Decrements size.
	  @return the object that used to be at position index.
	 */
	public E remove(int index) throws IndexOutOfBoundsException  
	{
		if(index >= size || index < 0)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

		Object x = myArray[index];
		myArray[index] = null;
		int i = index;
		while(i < size)
		{
			myArray[i] = myArray[i+1];
			myArray[i+1] = null;
			i++;
		}
		size--;
		return (E) x;
	}
	/*
		   This method compares objects.
      Must use .equals(), not ==
	 */
	public boolean contains(E obj)
	{
		for(int i = 0; i < size; i++)
		{
			if(myArray[i].equals(obj))
			{
				return true;
			}
		}

		return false;
	}
	/*returns a String of E objects in the array with 
    square brackets and commas.
	 */
	public String toString()
	{
		String arr = new String();
		arr += "[";
		for(int i = 0; i < size; i++) 
		{
			if(i == 0)
			{
				arr += (myArray[i]);
			}else {
				arr += " , " + myArray[i];
			}

		}
		arr += "]";

		return arr.toString();   }
}
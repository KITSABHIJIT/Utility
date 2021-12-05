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
		if(myArray.length-size <= 5){
			myArray = Arrays.copyOf(myArray, myArray.length*2);;
		}
		myArray[size++] = obj;
		return true;
	}
	/* inserts obj at position index.  increments size. 
	 */
	public void add(int index, E obj) throws IndexOutOfBoundsException  //this the way the real ArrayList is coded
	{
		if(index > size || index < 0) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}else {
			E[] tempMyArray= Arrays.copyOf(myArray, myArray.length+1);
			for(int i=index+1;i<tempMyArray.length;i++) {
				tempMyArray[i]=myArray[i-1];
			}
			tempMyArray[index] = obj;
			myArray=tempMyArray;
			size++;
		}

	}

	/* return obj at position index.  
	 */
	public E get(int index) throws IndexOutOfBoundsException
	{
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}else {
			return myArray[index];
		}


	}
	/**
	 * Replaces obj at position index. 
	 * @return the object is being replaced.
	 */  
	public E set(int index, E obj) throws IndexOutOfBoundsException  
	{ 
		E objTemp=null;
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}else {
			objTemp=myArray[index];
			myArray[index] = obj;
		}
		return objTemp;

	}
	/*  removes the node from position index. shifts elements 
  to the left.   Decrements size.
	  @return the object that used to be at position index.
	 */
	public E remove(int index) throws IndexOutOfBoundsException  
	{
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}else{
			Object obj = myArray[index];
			myArray[index] = null;
			int tmp = index;
			while(tmp < size){
				myArray[tmp] = myArray[tmp+1];
				myArray[tmp+1] = null;
				tmp++;
			}
			size--;
			return (E) obj;
		}
	}
	/*
		   This method compares objects.
      Must use .equals(), not ==
	 */
	public boolean contains(E obj)
	{
		boolean contains=false;
		for(int i=0;i<size;i++) {
			if(myArray[i].equals(obj)) {
				contains=true;
				break;
			}
		}
		return contains;
	}
	/*returns a String of E objects in the array with 
    square brackets and commas.
	 */
	public String toString()
	{
		StringBuffer temp=new StringBuffer();
		for(int i=0;i<size;i++) {
			temp.append((i==0)?"["+myArray[i].toString():" , "+myArray[i].toString());
		}
		return temp.append("]").toString();
	}
}
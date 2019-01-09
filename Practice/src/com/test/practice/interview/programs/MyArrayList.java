package com.test.practice.interview.programs;

import java.util.Arrays;

public class MyArrayList {
	private Object[] storage;
	private int actualSize;

	public MyArrayList(){
		storage = new Object[10];
    }

	private void add(Object obj){
		if(storage.length-actualSize<=5){
			increaseSize();
		}
		storage[actualSize]=obj;
		actualSize++;
	}
	private Object get(int index){
		if(index<actualSize){
			return storage[index];
		}else{
			return new ArrayIndexOutOfBoundsException();
		}
	}
	private Object remove(int index){
		if(index<actualSize){
			Object object=storage[index];
			storage[index]=null;
			int counter=index;
			while(counter<actualSize){
				storage[counter]=storage[counter+1];
				storage[counter+1]=null;
				counter++;
			}
			actualSize--;
			return object;
		}else{
			return new ArrayIndexOutOfBoundsException();
		}
	}
	private int size(){
		return actualSize;
	}	
	private void increaseSize(){
		Arrays.copyOf(storage, storage.length*2);
	}
	
	public static void main(String a[]){
        MyArrayList mal = new MyArrayList();
        mal.add(new Integer(2));
        mal.add(new Integer(5));
        mal.add(new Integer(1));
        mal.add(new Integer(23));
        mal.add(new Integer(14));
        for(int i=0;i<mal.size();i++){
            System.out.print(mal.get(i)+" ");
        }
        mal.add(new Integer(29));
        System.out.println("Element at Index 5:"+mal.get(5));
        System.out.println("List size: "+mal.size());
        System.out.println("Removing element at index 2: "+mal.remove(2));
        for(int i=0;i<mal.size();i++){
            System.out.print(mal.get(i)+" ");
        }
    }
	
	
}

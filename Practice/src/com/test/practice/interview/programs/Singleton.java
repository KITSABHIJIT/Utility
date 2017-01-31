package com.test.practice.interview.programs;

public class Singleton {

	private static Singleton singleton;
	static{
		singleton=new Singleton();
	}
	private Singleton() {

	}
	public static Singleton getInstance(){
		return singleton;
	}
	
	private void testMe(){
		System.out.println(" Singleton object created");
	}
	
	public static void main (String [] args){
		Singleton singleton=getInstance();
		singleton.testMe();
	}
}

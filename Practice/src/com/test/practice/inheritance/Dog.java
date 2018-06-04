package com.test.practice.inheritance;

/**
 * 
 * 	Inheritance can be defined as the process where one class acquires the properties (methods and fields) of another. 
 * 	With the use of inheritance the information is made manageable in a hierarchical order.

	The class which inherits the properties of other is known as subclass (derived class, child class) and the 
	class whose properties are inherited is known as superclass (base class, parent class).
 *
 */
interface Animal{}

class Mammals implements Animal{}

public class Dog extends Mammals{

   public static void main(String args[]){

      Dog m = new Dog();
      Dog d = new Dog();

      System.out.println(m instanceof Animal);
      System.out.println(d instanceof Dog);
      System.out.println(d instanceof Animal);
   }
} 

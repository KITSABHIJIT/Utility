package com.test.practice.overriding;

/**
 * 
 * The benefit of overriding is: ability to define a behaviour that's specific to the subclass type which means a 
 * subclass can implement a parent class method based on its requirement.

	In object-oriented terms, overriding means to override the functionality of an existing method.
 *
 */
class Animal{

	   public void move(){
	      System.out.println("Animals can move");
	   }
	}

	class Dog extends Animal{

	   public void move(){
		   super.move();
	      System.out.println("Dogs can walk and run");
	   }
	}

	public class TestDog{

	   public static void main(String args[]){
	      Animal a = new Animal(); // Animal reference and object
	      Animal b = new Dog(); // Animal reference but Dog object

	      a.move();// runs the method in Animal class

	      b.move();//Runs the method in Dog class
	   }
	}
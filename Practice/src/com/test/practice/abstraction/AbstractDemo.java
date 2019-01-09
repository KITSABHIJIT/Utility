package com.test.practice.abstraction;

/**
 * 
 * @author RoyAb001
 * 
 * Object oriented programming Abstraction is a process of hiding 
 * the implementation details from the user, only the functionality 
 * will be provided to the user. In other words user will have the 
 * information on what the object does instead of how it does it.
 */

public class AbstractDemo {
	public static void main(String [] args)
	   {
	      Salary s = new Salary("Mohd Mohtashim", "Ambehta, UP", 3, 3600.00);
	      Employee e = new Salary("John Adams", "Boston, MA", 2, 2400.00);

	      System.out.println("Call mailCheck using Salary reference --");
	      s.mailCheck();
	      System.out.println(s.computePay());

	      System.out.println("\n Call mailCheck using Employee reference--");
	      System.out.println(e.computePay());
	      e.mailCheck();
	    }
}

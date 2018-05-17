package com.test.practice.dataStructure;

import java.util.Enumeration;
import java.util.Vector;

/**
 * 
 * @author RoyAb001
 *
 *The Enumeration interface isn't itself a data structure, but it is very 
 *important within the context of other data structures. The Enumeration 
 *interface defines a means to retrieve successive elements from a data structure.
 */
public class EnumerationTester {
	public static void main(String args[]) {
	      Enumeration days;
	      Vector dayNames = new Vector();
	      dayNames.add("Sunday");
	      dayNames.add("Monday");
	      dayNames.add("Tuesday");
	      dayNames.add("Wednesday");
	      dayNames.add("Thursday");
	      dayNames.add("Friday");
	      dayNames.add("Saturday");
	      days = dayNames.elements();
	      while (days.hasMoreElements()){
	         System.out.println(days.nextElement()); 
	      }
	   }
}

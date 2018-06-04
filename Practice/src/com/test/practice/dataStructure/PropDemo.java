package com.test.practice.dataStructure;

import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 * 
 * 	Properties is a subclass of Hashtable. It is used to maintain lists of values in which the key is a String and the value is also a String.

	The Properties class is used by many other Java classes. For example, it is the type of object returned by System.getProperties( ) 
	when obtaining environmental values.
 *
 */
public class PropDemo {
	public static void main(String args[]) {
		Properties capitals = new Properties();
		Set states;
		String str;

		capitals.put("Illinois", "Springfield");
		capitals.put("Missouri", "Jefferson City");
		capitals.put("Washington", "Olympia");
		capitals.put("California", "Sacramento");
		capitals.put("Indiana", "Indianapolis");

		// Show all states and capitals in hashtable.
		states = capitals.keySet(); // get set-view of keys
		Iterator itr = states.iterator();
		while(itr.hasNext()) {
			str = (String) itr.next();
			System.out.println("The capital of " +
					str + " is " + capitals.getProperty(str) + ".");
		}
		System.out.println();

		// look for state not in list -- specify default
		str = capitals.getProperty("Florida", "Not Found");
		System.out.println("The capital of Florida is "
				+ str + ".");
	}
}

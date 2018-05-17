package com.test.practice.dataStructure;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * 
 * 	Hashtable so that it also implements the Map interface. 
 * 	Thus, Hashtable is now integrated into the collections framework. It is similar to HashMap, but is synchronized.

	Like HashMap, Hashtable stores key/value pairs in a hash table. 
	When using a Hashtable, you specify an object that is used as a key, 
	and the value that you want linked to that key. The key is then hashed, 
	and the resulting hash code is used as the index at which the value is stored within the table.
 *
 */
public class HashTableDemo {
	public static void main(String args[]) {
		// Create a hash map
		Hashtable balance = new Hashtable();
		Enumeration names;
		String str;
		double bal;

		balance.put("Zara", new Double(3434.34));
		balance.put("Mahnaz", new Double(123.22));
		balance.put("Ayan", new Double(1378.00));
		balance.put("Daisy", new Double(99.22));
		balance.put("Qadir", new Double(-19.08));

		// Show all balances in hash table.
		names = balance.keys();
		while(names.hasMoreElements()) {
			str = (String) names.nextElement();
			System.out.println(str + ": " +
					balance.get(str));
		}
		System.out.println(balance.toString());
		// Deposit 1,000 into Zara's account
		bal = ((Double)balance.get("Zara")).doubleValue();
		balance.put("Zara", new Double(bal+1000));
		System.out.println("Zara's new balance: " +
				balance.get("Zara"));
	}
}

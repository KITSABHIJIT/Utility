package com.test.practice.phoneBook;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class PhonebookDictionary {
	public static void main(String[] args) {

		Hashtable<String,Integer> phonebook = new Hashtable<String,Integer>();
		String value,userIp,key;

		phonebook.put("Rihana",233222232);
		phonebook.put("Rihana",1234556);
		phonebook.put("Ricky", 134242444);
		phonebook.put("Peter",224323423);
		phonebook.put("Ron", 988232323);
		System.out.println("Enter char to be searched:"); 		
		Scanner input = new Scanner(System.in);
		userIp = input.next();
		userIp = userIp.toUpperCase();
		Set s = phonebook.entrySet();
		Iterator it = s.iterator();
		boolean flag = true;
		while(it.hasNext()){
			Map.Entry me = (Map.Entry)it.next();
			key = (String)me.getKey();
			key=key.toUpperCase();
			for(int i =0;i<userIp.length();i++){
				flag = true;
				if(userIp.charAt(i) != key.charAt(i)){
					flag = false;
				}
			}
			if(flag){
				System.out.println(key);
				System.out.println(me.getValue());
			}
		}
	}
}

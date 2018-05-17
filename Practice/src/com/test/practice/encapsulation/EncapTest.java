package com.test.practice.encapsulation;

/**
 * 
 * @author RoyAb001
 * 
 * Encapsulation in Java is a mechanism of wrapping the data (variables) 
 * and code acting on the data (methods) together as as single unit. 
 * In encapsulation the variables of a class will be hidden from other classes,
 * and can be accessed only through the methods of their current class, therefore 
 * it is also known as data hiding.
 */
public class EncapTest {
	
	private String name;
	private String idNum;
	private int age;

	public int getAge(){
		return age;
	}

	public String getName(){
		return name;
	}

	public String getIdNum(){
		return idNum;
	}

	public void setAge( int newAge){
		age = newAge;
	}

	public void setName(String newName){
		name = newName;
	}

	public void setIdNum( String newId){
		idNum = newId;
	}
}

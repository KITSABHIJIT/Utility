package com.test.practice.interview.programs;

import java.util.HashMap;
import java.util.Map;

public class HashCodeImpl {

	public static void main(String[] args) {
		Map<Item,String> map=new HashMap<Item,String>();
		map.put(new Item("Banana", 20),"Banana");
		map.put(new Item("Apple", 10),"Apple");
		map.put(new Item("JackFruit", 30),"JackFruit");
		map.put(new Item("Pears", 5),"Pears");
		Item key=new Item("JackFruit", 30);
		System.out.println("Hascode: "+key.hashCode());
		System.out.println("Get Value using Equals method: "+map.get(key));
		
	}

}
class Item{
	private String name;
	private int price;
	
	public Item(String name,int price){
		this.name=name;
		this.price=price;
	}
	
	public int hashCode(){
		System.out.println("Inside HashCode Method...");
		int hashcode=price*20;
		hashcode+=name.hashCode();
		return hashcode;
	}
	
	public boolean equals(Object item){
		System.out.println("Inside equals Method...");
		if(item instanceof Item){
			Item item2=(Item)item;
			return ((item2.price==this.price) && (item2.name.equals(this.name)));
		}else{
			return false;
		}
	}
}
package com.test.practice.phoneBook;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class PhonebookDictionaryExtended {
	public static class Record{
		String firstName,middleName,lastName,emailId,company;
		long home,mobile,work;
		public Record(String firstName,String middleName,String lastName, String email,String company,long home,long mobile, long work){
			this.firstName=firstName;
			this.middleName=middleName;
			this.lastName=lastName;
			this.emailId=email;
			this.company=company;
			this.home=home;
			this.mobile=mobile;
			this.work=work;
		}
		public String getFirstName() {
			return firstName;
		}
		public String getMiddleName() {
			return middleName;
		}
		public String getLastName() {
			return lastName;
		}
		public String getEmailId() {
			return emailId;
		}
		public String getCompany() {
			return company;
		}
		public long getHome() {
			return home;
		}
		public long getMobile() {
			return mobile;
		}
		public long getWork() {
			return work;
		}
		@Override
		public String toString() {
			return "\tfirstName: " + firstName 
					+ ((middleName.isEmpty())?"":"\n\tmiddleName: "+ middleName) 
					+ "\n\tlastName: " + lastName + "\n\temailId: "
					+ emailId + "\n\tcompany: " + company + "\n\thome: " + home
					+ "\n\tmobile: " + mobile + "\n\twork: " + work ;
		}

	}

	public static void main(String[] args) {

		List<Record> recordList=new ArrayList<Record>();
		recordList.add(new Record("Rihana","","Rax","Rihana.rax@abc.com","abc company",1231213,8454464,946143468));
		recordList.add(new Record("Ricky","John","Pott","Ricky.Pott@xyz.com","xyz company",56364564,23123123,7567567));
		recordList.add(new Record("Peter","Perker","Loyd","Peter.Loyd@okm.com","okm company",1231213,8454464,946143468));
		recordList.add(new Record("Ron","","Write","Ron.Write@jng.com","jng company",24607676,979456346,54545));
		
		Hashtable<String,Record> phonebook = new Hashtable<String,Record>();
		String value,userIp,key;

		for(Record record:recordList){
			phonebook.put(record.getFirstName()+(!record.getMiddleName().isEmpty()?" "+record.getMiddleName():"")+(!record.getLastName().isEmpty()?" "+record.getLastName():""),record);
		}
		
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
				System.out.println(me.getValue().toString());
			}
		}
	}
}

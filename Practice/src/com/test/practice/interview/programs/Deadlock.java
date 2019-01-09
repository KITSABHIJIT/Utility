package com.test.practice.interview.programs;

public class Deadlock {

	String str1="Java";
	String str2="Unix";
	
	Thread thread1=new Thread(){
		public void run(){
			while(true){
				synchronized (str1) {
					synchronized (str2) {
						System.out.println(System.currentTimeMillis()+str1+str2);
					}
				}
			}
		}
	};
	
	Thread thread2=new Thread(){
		public void run(){
			while(true){
				synchronized (str2) {
					synchronized (str1) {
						System.out.println(System.currentTimeMillis()+str1+str2);
					}
				}
			}
		}
	};
	
	public static void main(String [] args){
		Deadlock deadlock=new Deadlock();
		deadlock.thread1.start();
		deadlock.thread2	.start();
	}
}

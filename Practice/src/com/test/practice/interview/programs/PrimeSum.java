package com.test.practice.interview.programs;

public class PrimeSum {
	public static int getPrimeSum(int limit){
		int sum=0;
		System.out.print("Prime Numbers: ");
		for(int i=1;i<=limit;i++){
			if(isPrime(i)){
				sum+=i;
				System.out.print(i+" ");
			}
		}
		return sum;
	}
	public static boolean isPrime(int number){
		boolean isPrime=true;
		for(int i=2;i<=number/2;i++){
			if(number%i==0){
				isPrime=false;
				break;
			}
		}
		return isPrime;
	}
	public static void main(String[] args) {
		int limit=10;
		System.out.println("Given Limit: "+limit);
		System.out.println("Prime Sum: "+getPrimeSum(limit));
		
	}

}

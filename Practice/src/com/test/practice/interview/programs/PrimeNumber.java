package com.test.practice.interview.programs;

public class PrimeNumber {
	private static boolean checkPrime(int number){
        boolean isPrime=true;
        if(number==0){
            isPrime= false;
        }
        for(int i=2;i<=number/2;i++){
            if(number%i==0){
                isPrime= false;
                break;
            }
        }
        return isPrime;
        
    }
    
    public static void main(String ... args){
        int a=71;
        System.out.println("Given Number: "+a );
        System.out.println("Prime Number Check: "+checkPrime(a));
    }
}

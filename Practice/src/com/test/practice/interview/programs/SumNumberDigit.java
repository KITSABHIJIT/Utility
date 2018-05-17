package com.test.practice.interview.programs;

public class SumNumberDigit {
	static int sum=0;
    private static int getSum(int number){
        if(number==0){
            return sum;
        }
        sum+=number%10;
        getSum(number/10);
        return sum;
    }
    
    public static void main(String ... args){
        int a=123456789;
        getSum(a);
        System.out.println("Given Number: "+a );
        System.out.println("Sum of all digits: "+sum );
    }
}

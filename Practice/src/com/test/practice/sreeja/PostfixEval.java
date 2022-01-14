package com.test.practice.sreeja;

//Name: J1-018-21
//Date: 01/11/22

import java.util.*;

public class PostfixEval
{
	public static final String operators = "+ - * / % ^ !";

	public static void main(String[] args)
	{
		System.out.println("Postfix  -->  Evaluate");
		ArrayList<String> postfixExp = new ArrayList<String>();
		/*  build your list of expressions here  */

		postfixExp.add("3 4 5 * +");
		postfixExp.add("3 4 * 5 +");
		postfixExp.add("10 20 + -6 6 * +");
		postfixExp.add("3 4 + 5 6 + *");
		postfixExp.add("3 4 5 + * 2 - 5 /");
		postfixExp.add("8 1 2 * + 9 3 / -");
		postfixExp.add("2 3 ^");
		postfixExp.add("20 3 %");
		postfixExp.add("21 3 %");
		postfixExp.add("22 3 %");
		postfixExp.add("23 3 %");
		postfixExp.add("5 !");
		postfixExp.add("1 1 1 1 1 + + + + !");

		for( String pf : postfixExp )
		{
			System.out.println(pf + "\t\t" + eval(pf));
		}
	}

	public static double eval(String exp)
	{
		List<String> postfixParts = new ArrayList<String>(Arrays.asList(exp.split(" ")));
		//create a stack
		Stack<Double> stack=new Stack<>();

		// Scan all characters one by one
		for(String str : postfixParts)
		{
			// If the scanned character is an operand (number here),
			// push it to the stack.
			if(!isOperator(str))
			{
				stack.push(Double.valueOf(str));
			}

			//  If the scanned character is an operator, pop two
			// elements from stack apply the operator
			else
			{
				double val1 = stack.pop();
				double val2 = 0;
				try {
					val2 = stack.pop();
				} catch (EmptyStackException e) {}
				stack.push(eval(val1,val2,str));
			}
		}
		return stack.pop();  
	}

	public static double eval(double val1, double val2, String str)
	{
		double result=0;
		switch(str)
		{
		case "+":
			result = val2+val1;
			break;

		case "-":
			result = val2- val1;
			break;

		case "/":
			result = val2/val1;
			break;

		case "*":
			result = val2*val1;
			break;
		case "%":
			result = val2%val1;
			break;
		case "^":
			result = Math.pow(val2, val1);
			break;
		case "!":
			int i;
			double fact=1;  
			for(i=1;i<=val1;i++){    
				fact=fact*i;    
			}  
			result = fact;
			break;
		} 
		return result;
	}

	public static boolean isOperator(String op)
	{
		if (operators.indexOf(op) != -1)
		{
			return true;
		}

		return false;
	}
}

/****************
Postfix  -->  Evaluate
3 4 5 * +		23
3 4 * 5 +		17
10 20 + -6 6 * +		-6
3 4 + 5 6 + *		77
3 4 5 + * 2 - 5 /		5
8 1 2 * + 9 3 / -		7
2 3 ^		8
20 3 %		2
21 3 %		0
22 3 %		1
23 3 %		2
5 !		120
1 1 1 1 1 + + + + !		120


 *************/

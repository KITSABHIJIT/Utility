package com.test.practice.sreeja;

//Name: J1-018-21
//Date: 01/10/22
import java.util.ArrayList;
import java.util.Stack;

public class ParenMatch
{
	public static final String LEFT  = "([{<";
	public static final String RIGHT = ")]}>";

	public static void main(String[] args)
	{
		System.out.println("Parentheses Match");
		ArrayList<String> parenExp = new ArrayList<String>();
		/* enter test cases here */
		parenExp.add("5 + 7");
		parenExp.add("( 15 + -7 )");
		parenExp.add("); 5 + 7 (");
		parenExp.add("( ( 5.0 - 7.3 ) * 3.5 )");
		parenExp.add("< { 5 + 7 } * 3 >");
		parenExp.add("[ ( 5 + 7 ) * ] 3");
		parenExp.add("( 5 + 7 ) * 3");
		parenExp.add("5 + ( 7 * 3 )");
		parenExp.add("( ( 5 + 7 ) * 3");
		parenExp.add("[ ( 5 + 7 ] * 3 )");
		parenExp.add("[ ( 5 + 7 ) * 3 ] )");
		parenExp.add("( [ ( 5 + 7 ) * 3 ]");
		parenExp.add("( ( ( ) $ ) )");
		parenExp.add("( ) [ ]");

		for( String s : parenExp )
		{
			boolean good = checkParen(s);
			//boolean good = checkParenWithoutStack(s);
			if(good)
				System.out.println(s + "\t good!");
			else
				System.out.println(s + "\t BAD");
		}
	}

	//returns the index of the left parentheses or -1 if it is not there
	public static int isLeftParen(String p)
	{
		return LEFT.indexOf(p);
	}

	//returns the index of the right parentheses or -1 if it is not there
	public static int isRightParen(String p)
	{
		return RIGHT.indexOf(p);
	}

	public static boolean checkParen(String exp)
	{
		/* enter your code here */

		Stack st = new Stack();
		for (int i = 0; i < exp.length(); i++) {
			String c = "" + exp.charAt(i);
			if (LEFT.contains(c)) {
				st.push(c);
			} else if (RIGHT.contains(c)) {
				try {

					String popped = (String)st.pop();
					if (!checkReverseChar(popped,c)) {
						return false;
					}
				} catch (java.util.EmptyStackException err) {
					return false;
				}
			}
		}
		return (st.size() == 0);  
	}
	// Method for doing the parenthesis match without using Stack
	public static boolean checkParenWithoutStack(String exp)
	{
		char[] chars = new char[1000]; 
		int pos = 0;
		for (int i=0; i<exp.length(); i++) {
			char c = exp.charAt(i);
			switch (c) { 
			case '{' : chars[pos++] = '}'; break;
			case '(' : chars[pos++] = ')'; break;
			case '[' : chars[pos++] = ']'; break;
			case ']' : case ')' : case '}' :
				if (pos == 0) { return false; }
				if (chars[pos-1] == c) { pos--; }
				else { return false; }
				break;
			default : break;
			}
		}
		return pos == 0; 
	}
	
	public static boolean checkReverseChar(String s1, String s2) {
		if (s1.equals("(")) {
			return s2.equals(")");
		} else if (s1.equals("<")) {
			return s2.equals(">");
		} else if (s1.equals("[")) {
			return s2.equals("]");
		} else if (s1.equals("{")) {
			return s2.equals("}");
		} else {
			return false;
		}
	}
}

/***************

Parentheses Match
5 + 7		 good!
( 15 + -7 )		 good!
) 5 + 7 (		 BAD
( ( 5.0 - 7.3 ) * 3.5 )		 good!
< { 5 + 7 } * 3 >		 good!
[ ( 5 + 7 ) * ] 3		 good!
( 5 + 7 ) * 3		 good!
5 + ( 7 * 3 )		 good!
( ( 5 + 7 ) * 3		 BAD
[ ( 5 + 7 ] * 3 )		 BAD
[ ( 5 + 7 ) * 3 ] )		 BAD
( [ ( 5 + 7 ) * 3 ]		 BAD
( ( ( ) $ ) )		 good!
( ) [ ]		 good!

 ***************/

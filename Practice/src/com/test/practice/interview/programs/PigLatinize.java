package com.test.practice.interview.programs;

import java.util.Scanner;

public class PigLatinize {
	static boolean isVowel(char ch)   
	{   
		return (ch == 'A' || ch == 'a' || ch == 'E' || ch == 'e' || ch == 'I' || ch == 'i' || ch == 'O' || ch == 'o' || ch == 'U' || ch == 'u');   
	} 

	static String pigLatinWordwithPunctuation(String str)   
	{
		String suffixPunctuation = "";
		for (int i = str.length() - 1; i > 0; i--) {
			if (!Character.isLetter(str.charAt(i))) {
				suffixPunctuation = str.charAt(i) + suffixPunctuation;
			} else {
				break; // Found all punctuation
			}
		}
		
		String prefixPunctuation = "";
		for (int j = 0; j < str.length() - 1; j++) {
			if (!Character.isLetter(str.charAt(j))) {
				prefixPunctuation = prefixPunctuation + str.charAt(j);
			} else {
				break; // Found all punctuation
			}
		}
		// Remove prefix punctuation if any
		str = str.substring(prefixPunctuation.length(), str.length());
		// Remove suffix punctuation if any
		str = str.substring(0, str.length() - suffixPunctuation.length()); 
		// Convert str to pig latin
		// Append punctuation to str
		return prefixPunctuation + pigLatinWord(str) + suffixPunctuation;
	}

	//method that converts a word or string into Pig Latin  
	static String pigLatinWord(String string)   
	{   
		//the index of the first vowel is stored   
		int stringlength=string.length();   
		int index=-1;   
		for (int i=0; i<stringlength; i++)   
		{   
			// Special Cases 1: If qu is before a vowel treat that as a consonant  
			if (i>0 && (string.charAt(i-1)=='q' || string.charAt(i-1)=='Q') && isVowel(string.charAt(i)))   
			{   
				i=i+1;   
			} 
			// Special Cases 2: Conditions for handling Y  
			if (i>0 && string.charAt(i)=='y')   
			{   
				index=i;   
				break;   
			} 
			if (isVowel(string.charAt(i)))   
			{   
				index=i;   
				break;   
			}   
		}   
		//Pig Latin is possible only if vowels is present   
		if(index==-1)   
			return "******NO VOWEL******";   

		if(index==0){
			// If the first character is a vowel, appending "way"   
			if(Character.isUpperCase(string.charAt(0))) {
				return string.substring(index,index+1).toUpperCase() + string.substring(index+1).toLowerCase() + string.substring(0, index) + "way";  
			}else {
				//return string.substring(index) + string.substring(0, 1).toLowerCase() + string.substring(1, index) + "way";
				return string.substring(index) + string.substring(0, index) + "way";
			}
		}else {
			// Take all characters after index (including index). Append all characters which are before index. Finally append "ay"   
			if(Character.isUpperCase(string.charAt(0))) {
				return string.substring(index,index+1).toUpperCase() + string.substring(index+1) + string.substring(0, 1).toLowerCase() + string.substring(1, index) + "ay";  
			}else {
				return string.substring(index) + string.substring(0, index) + "ay";
			}
		}
	}   
	//Driver code   
	public static void main(String args[])   
	{   

		if(args.length == 0) {
			System.out.println("Provide a word or a sentence an press Enter");
			Scanner sc = new Scanner(System.in);
			String text = sc.nextLine();
			String piglatinized = pigLatinWordwithPunctuation(text);
			System.out.println("Your Pig Latin is : " + piglatinized);
		}else {
			System.out.println("Your piglatinized words are: ");
			for(String word : args) {
				String piglatin = pigLatinWordwithPunctuation(word);
				System.out.println("Word: " + word + " ---- " + piglatin);
			}
		}
	}   
}

package com.test.practice.interview.programs;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DuplicateCharsInString {
	private static void printDuplicate(String data){
	       
	       Map<Character,Integer> duplicateMap=new HashMap<Character,Integer>();
	        for(Character c:data.toCharArray()){
	            if(duplicateMap.containsKey(c)){
	                duplicateMap.put(c,duplicateMap.get(c)+1);
	            }else{
	                 duplicateMap.put(c,1);
	            }
	        }
	        Set<Character> charSet=duplicateMap.keySet();
	        for(Character c:charSet){
	            if(duplicateMap.get(c)>1){
	                System.out.println("Character: "+c+" repeated count: "+duplicateMap.get(c));
	            }
	        }

	    }
	    
	    public static void main(String ... args){
	        printDuplicate("https://codepair.hackerrank.com/paper/sxdebikkvpfhxqeaytqovyewxjzgqksd?b=eyJpbnRlcnZpZXdfaWQiOjE4ODU1NCwicm9sZSI6ImludGVydmlld2VyIiwic2hvcnRfdXJsIjoiaHR0cDovL2hyLmdzLzkzZGY4ZCJ9#");
	    }
}

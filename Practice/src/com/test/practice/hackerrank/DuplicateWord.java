package com.test.practice.hackerrank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DuplicateWord {

	public List<String> retrieveMostFrequentlyUsedWords(String literatureText, 
			List<String> wordsToExclude)
	{
		List<String> result =new ArrayList<String>();
		List<String> resultNew =new ArrayList<String>();
		Map<String,Integer> countMap=new HashMap<String,Integer>();
		String [] textArr=literatureText.split(" ");
		for(String text:textArr) {
			if(countMap.containsKey(text)) {
				countMap.put(text, (countMap.get(text)+1));
			}else {
				countMap.put(text,1);
			}
		}
		
		//Map<String,Integer> sortedMap = sortByValue(countMap);
		Iterator iterator = countMap.entrySet().iterator();
		while (iterator.hasNext()) {
			
				Map.Entry mapEntry = (Map.Entry) iterator.next();
				if((Integer)mapEntry.getValue() >1){
					result.add((String)mapEntry.getKey());
					System.out.println("The word  " + mapEntry.getKey()
					+ " : occurs " + mapEntry.getValue() + " times");
				}
		}
		for(String resultText:result) {
			if(!wordsToExclude.contains(resultText)) {
				resultNew.add(resultText);
			}
		}
		return resultNew;
	}
	

	public static void main(String ...strings ) {
		DuplicateWord duplicateWord=new DuplicateWord();
		List<String> data =new ArrayList<String>();
		data.add("and");
		data.add("the");
		duplicateWord.retrieveMostFrequentlyUsedWords("jack and jill went the market to buy cheese cheese is jack favorite food",data );
	}
}

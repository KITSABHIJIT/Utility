package com.test.practice.interview.programs;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;

public class MaxDuplicateWordCount {

	private Map<String, Integer> getWordCount(String fileName){
		Map<String, Integer> wordCountMap=new HashMap<String, Integer>();
		FileInputStream fs=null;
		DataInputStream ds=null;
		BufferedReader br=null;
		try {
			fs =new FileInputStream(fileName);
			ds=new DataInputStream(fs);
			br=new BufferedReader(new InputStreamReader(ds));
			String line="";
			while((line=br.readLine())!=null){
				StringTokenizer stringTokenizer=new StringTokenizer(line, " ");
				while(stringTokenizer.hasMoreTokens()){
					String temp=stringTokenizer.nextToken().toLowerCase();
					if(wordCountMap.containsKey(temp)){
						wordCountMap.put(temp, wordCountMap.get(temp)+1);
					}else{
						wordCountMap.put(temp, 1);
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(fs!=null){fs.close();}
				if(ds!=null){ds.close();}
				if(br!=null){br.close();}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return wordCountMap;
	}
	
	private List<Entry<String,Integer>> sortByValue(Map<String, Integer> wordCountMap){
		Set<Entry<String,Integer>> set=wordCountMap.entrySet();
		List<Entry<String,Integer>> sortedList=new ArrayList<Map.Entry<String,Integer>>(set);
		Collections.sort(sortedList, new Comparator<Map.Entry<String,Integer>>() {
			public int compare(Map.Entry<String,Integer> o1,Map.Entry<String,Integer> o2){
				return(o2.getValue().compareTo(o1.getValue()));
			}
		});
		return sortedList;
	}
	
	public static void main(String [] args){
		MaxDuplicateWordCount mdc = new MaxDuplicateWordCount();
        Map<String, Integer> wordMap = mdc.getWordCount("C:/Users/royab001/Desktop/work done/SNC Data Setup.txt");
        List<Entry<String, Integer>> list = mdc.sortByValue(wordMap);
        for(Map.Entry<String, Integer> entry:list){
            System.out.println(entry.getKey()+" ==== "+entry.getValue());
        }
		
	}
	
}

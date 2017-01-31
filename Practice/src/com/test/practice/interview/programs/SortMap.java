package com.test.practice.interview.programs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class SortMap {
	private static void sortMap(Map<String,Integer> map){
        Set<Entry<String,Integer>> mapSet=map.entrySet();
        List<Entry<String,Integer>> list =new ArrayList<Entry<String,Integer>>(mapSet);
        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>(){
            public int compare(Map.Entry<String,Integer> o1,Map.Entry<String,Integer> o2){
                return o2.getValue().compareTo(o1.getValue());
            }
            
        });
        for(Entry<String,Integer> entry: list){
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }
        
    }
    
    
    public static void main(String ... args){
        Map<String,Integer> data=new HashMap<String,Integer>();
        data.put("Java",10);
        data.put("C++",4);
        data.put("Cobol",8);
        data.put("C",12);
        data.put("Python",1);
        
        sortMap(data);
    }
}

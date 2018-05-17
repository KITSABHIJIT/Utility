package com.test.practice.hackerrank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ReorderLog {
	public List<String> reorderLines(int logFileSize, List<String> logLines) 
	{
		List<String> result =new ArrayList<String>();
		List<String> alphaNumeric =new ArrayList<String>();
		List<String> numeric =new ArrayList<String>();
		Map<String,String> dataMap =new HashMap<String,String>();

		for(String line:logLines) {
			dataMap.put(line.split(" ")[0], getValues(line.split(" ")));
		}

		Iterator iterator = dataMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry mapEntry = (Map.Entry) iterator.next();
			if(((String)mapEntry.getValue()).toUpperCase().equals(((String)mapEntry.getValue()).toLowerCase())){
				numeric.add((String)mapEntry.getKey()+" "+(String)mapEntry.getValue());
			}else {
				alphaNumeric.add((String)mapEntry.getKey()+" "+(String)mapEntry.getValue());
			}
		}

		// Collections.sort(alphaNumeric);
		Collections.sort(alphaNumeric, new MyComparator());
		result.addAll(alphaNumeric);
		result.addAll(numeric);

		return result;
	}

	public String getValues(String [] data) {
		String result="";
		for(int i=1;i<data.length;i++) {
			result=("".equals(result))?data[i]:(result+" "+data[i]);
		}
		return result;
	}

	public static void main(String ...strings ) {
		ReorderLog reorderLog=new ReorderLog();
		List<String> data =new ArrayList<String>();
		data.add("a1 9 2 3 1");
		data.add("g1 act car");
		data.add("zo4 4 7");
		data.add("ab 1off key dog");
		data.add("a8 act zoo");
		reorderLog.reorderLines(5,data);
	}
}
class MyComparator implements Comparator<String>{
	@Override
	public int compare(String o1, String o2) {
		return getValues(o1.split(" ")).compareTo(getValues(o2.split(" ")));
	}   
	public String getValues(String [] data) {
		String result="";
		for(int i=1;i<data.length;i++) {
			result=("".equals(result))?data[i]:(result+" "+data[i]);
		}
		return result;
	}
}

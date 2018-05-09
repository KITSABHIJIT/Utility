package com.test.code.extract;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test.code.util.FileUtil;

public class DataExtractor {
	
	public static Map<String,List<String>> extractDataWithFileName(String sourceDirPath){
		 Map<String,List<String>> resultMap=new HashMap<String,List<String>>();
		File sourceDir=new File(sourceDirPath);
		if(sourceDir.exists()){
			List<String> fileNameList = FileUtil.getListOfFiles(sourceDirPath, true);
			for(String filePath: fileNameList){
				List<String> resultList=new ArrayList<String>();
				System.out.println("Reading file: "+filePath);
				resultList.addAll(FileUtil.readFromFile(filePath));
				resultMap.put(filePath, resultList);
			}
		}else{
			System.out.println("No directory exists like : "+sourceDirPath);
		}
		return resultMap;
	}
	
	public static List<String> extractData(String sourceDirPath){
		List<String> resultList=new ArrayList<String>();
		File sourceDir=new File(sourceDirPath);
		if(sourceDir.exists()){
			List<String> fileNameList = FileUtil.getListOfFiles(sourceDirPath, true);
			for(String filePath: fileNameList){
				System.out.println("Reading file: "+filePath);
				resultList.addAll(FileUtil.readFromFile(filePath));
			}
		}else{
			System.out.println("No directory exists like : "+sourceDirPath);
		}
		return resultList;
	}
}

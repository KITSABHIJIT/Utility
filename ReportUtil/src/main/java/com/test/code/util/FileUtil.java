package com.test.code.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FileUtil {

	public static List<String> getListOfFiles(String directoryName,boolean absolutepath) {
		File directory = new File(directoryName);
		ArrayList<String> files=new ArrayList<String>();
		// get all the files from a directory
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isFile()) {
				files.add((absolutepath)?file.getAbsolutePath():file.getName());
			} else if (file.isDirectory()) {
				files.addAll(getListOfFiles(file.getAbsolutePath(),absolutepath));
			}
		}
		Collections.sort(files, new Comparator<String>() {
			public int compare(String s1, String s2) {
				return s1.compareToIgnoreCase(s2);
			}
		});
		return files;
	}

	public static void writeToFile(String content,String fileName){
		FileOutputStream fop = null;
		File file;
		try {
			file = new File(fileName);
			fop = new FileOutputStream(file,true);
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			// get the content in bytes
			byte[] contentInBytes = content.getBytes();
			fop.write(contentInBytes);
			fop.flush();
			fop.close();
			System.out.println(fileName+ " Created Successfully.");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void createDirectory(String fileName){
		File file;
		file = new File(fileName);
		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.mkdirs();
		}
		System.out.println(fileName+ " Created Successfully.");
	}

	public static List<String> readFromFile(String fileName){
		List<String> list=new ArrayList<String>();
		BufferedReader br = null;
		FileReader fr = null;
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String sCurrentLine;
			br = new BufferedReader(new FileReader(fileName));
			while ((sCurrentLine = br.readLine()) != null) {
				list.add(sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return list;
	}

	public static String getStringFromFile(String fileName){
		StringBuffer buffer=new StringBuffer();
		BufferedReader br = null;
		FileReader fr = null;
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String sCurrentLine;
			br = new BufferedReader(new FileReader(fileName));
			while ((sCurrentLine = br.readLine()) != null) {
				buffer.append(sCurrentLine);
				buffer.append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return buffer.toString();
	}
	
	public static void deleteFile(String filePath) {
		File file = new File(filePath);
        if(file.delete())
        {
            System.out.println(filePath+" deleted successfully");
        }
        else
        {
            System.out.println("Failed to delete the file: "+filePath);
        }
	}
}

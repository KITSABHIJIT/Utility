package com.stockPrice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

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
				getListOfFiles(file.getAbsolutePath(),absolutepath);
			}
		}
		return files;
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
	public static void writeToFile(String content,String fileName){

		BufferedWriter bw = null;
		File file;
		try {
			file = new File(fileName);
			bw = new BufferedWriter(new FileWriter(file, true));
			if (!file.exists()) {
				file.createNewFile();
				System.out.println(fileName+ " Created Successfully.");
			}
			bw.append(content);
			bw.append("\n");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

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

	public static void deleteFile(String filePath) {
		File file = new File(filePath);
		if(file.exists()) {
			try {
				FileUtils.forceDelete(file);
				System.out.println(filePath+" deleted successfully");
			} catch (IOException e) {
				System.out.println("Failed to delete the file: "+filePath+" :"+e.getMessage());
			}
		}
	}

	public static void copyFile(String srcFile,String destFile) {
		File fileSrc = new File(srcFile);
		File fileDest = new File(destFile);
		try {
			FileUtils.copyFile(fileSrc,fileDest);
			System.out.println(srcFile+" copied successfully to "+destFile);
		} catch (IOException e) {
			System.out.println("Failed to copy the file: "+srcFile+" :"+e.getMessage());
		}
	}
}

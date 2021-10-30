package com.myutility.code;



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

	public static List<String> getListOfFiles(String directoryName,boolean absolutepath,List<String> outFiles) {
		File directory = new File(directoryName);
		// get all the files from a directory
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isFile()) {
				outFiles.add((absolutepath)?file.getAbsolutePath():file.getName());
			} else if (file.isDirectory()) {
				getListOfFiles(file.getAbsolutePath(),absolutepath,outFiles);
			}
		}
		Collections.sort(outFiles, new Comparator<String>() {
			public int compare(String s1, String s2) {
				return s1.compareToIgnoreCase(s2);
			}
		});
		return outFiles;
	}

	public static void cutPasteFiles(String destinationDir,List<String> files,String prefix) {

		for(String fle:files) {
			File file = new File(fle);
			if(null==prefix || file.getName().toUpperCase().endsWith(prefix.toUpperCase())) {
				// renaming the file and moving it to a new location
				String newFileName=file.getName().substring(0, file.getName().lastIndexOf("."))+"_"+System.currentTimeMillis()+"."+file.getName().substring(file.getName().lastIndexOf(".")+1);
				if(file.renameTo
						(new File(destinationDir+"\\"+newFileName)))
				{
					// if file copied successfully then delete the original file
					file.delete();
					System.out.println(file.getName()+" File moved successfully");
				}
				else
				{
					System.out.println(file.getName()+"Failed to move the file");
				}
			}
		}
	}

	public static void writeToFile(String content,String fileName){
		FileOutputStream fop = null;
		File file;
		try {

			file = new File(fileName);
			fop = new FileOutputStream(file);
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

	public static void main(String ...strings) {
		ArrayList<String> files=new ArrayList<String>();
		List<String> data=getListOfFiles("F:\\Spoon\\Spoon-002",true,files);
		cutPasteFiles("F:\\Spoon\\new-dxf",data,null);
	}
}

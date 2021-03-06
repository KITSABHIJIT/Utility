package com.test.code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

public class ProcessUtil {
	public static List<String> readFromFile(String fileName){
		List<String> list=new ArrayList<String>();
		BufferedReader br = null;
		InputStream inputStream = null;

		try {

			String sCurrentLine;
			ClassLoader classLoader = ProcessUtil.class.getClassLoader();
			inputStream = classLoader.getResourceAsStream(fileName);
			br = new BufferedReader(new InputStreamReader(inputStream));

			while ((sCurrentLine = br.readLine()) != null) {
				list.add(sCurrentLine);
			}
		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (inputStream != null)
					inputStream.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		return list;
	}

	public static String readFromFileAsString(String fileName){
		StringBuffer buffer=new StringBuffer();
		BufferedReader br = null;
		InputStream inputStream = null;
		try {
			String sCurrentLine;
			ClassLoader classLoader = ProcessUtil.class.getClassLoader();
			inputStream = classLoader.getResourceAsStream(fileName);
			br = new BufferedReader(new InputStreamReader(inputStream));

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

				if (inputStream != null)
					inputStream.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		return buffer.toString();
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

	public static void downloadResource(String fileName) {
		String [] jarFileDetailsArr=fileName.split("[|]");
		downloadFile(jarFileDetailsArr[0]);
		if(jarFileDetailsArr.length>4) {
			downloadFile(jarFileDetailsArr[4]);
		}
	}
	
	public static void downloadFile(String fileName) {
		String [] jarFileDetailsArr=fileName.split("[|]");
		String jarFile=jarFileDetailsArr[0];
		ReadableByteChannel rbc=null;
		FileOutputStream fos=null;
		try {
			URL website = new URL(PropertiesUtil.getProperty("repositoryURL")+jarFile);
			rbc = Channels.newChannel(website.openStream());
			fos = new FileOutputStream(System.getProperty("user.dir")+"/"+jarFile);
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {

				if (fos != null)
					fos.close();

				if (rbc != null)
					rbc.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	}

	public static void runWindowsCommand(String jarFileDetails,String mavenCommand,String mavenDependency) {
		try {
			String [] jarFileDetailsArr=jarFileDetails.split("[|]");
			mavenCommand=mavenCommand.replaceAll("<JAR_FILE_NAME>", jarFileDetailsArr[0]);
			mavenCommand=mavenCommand.replaceAll("<GROUP_ID>", jarFileDetailsArr[1]);
			mavenCommand=mavenCommand.replaceAll("<ARTIFACT_ID>", jarFileDetailsArr[2]);
			mavenCommand=mavenCommand.replaceAll("<VERSION>", jarFileDetailsArr[3]);
			if(jarFileDetailsArr.length>4) {
				String pomInstall=PropertiesUtil.getProperty("pom_install");
				pomInstall=pomInstall.replaceAll("<POM_FILE>", jarFileDetailsArr[4]);
				mavenCommand=mavenCommand+" "+pomInstall;
			}
			ProcessBuilder builder = new ProcessBuilder(
		            "cmd.exe", "/c", "cd "+System.getProperty("user.dir")+" && "+mavenCommand);

			builder.redirectErrorStream(true);
			Process p = builder.start();
			BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while (true) {
				line = r.readLine();
				if (line == null) { break; }
				System.out.println(line);
			}
			System.out.println("Jar File Name: "+jarFileDetailsArr[0]);
			mavenDependency=mavenDependency.replaceAll("<GROUP_ID>", jarFileDetailsArr[1]);
			mavenDependency=mavenDependency.replaceAll("<ARTIFACT_ID>", jarFileDetailsArr[2]);
			mavenDependency=mavenDependency.replaceAll("<VERSION>", jarFileDetailsArr[3]);
			System.out.println("Maven Command: "+mavenCommand);
			System.out.println("Dependency: "+mavenDependency);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getFileSize(String jarFile) {
		File file = new File(jarFile);
		String size=null;
		if(file.exists()){
			double bytes = file.length();
			double kilobytes = (bytes / 1024);
			double megabytes = (kilobytes / 1024);
			double gigabytes = (megabytes / 1024);

			if(gigabytes>1)
				size=  (Math.round(gigabytes * 100.0) / 100.0)+" GB";
			else if(megabytes >1)
				size= (Math.round(megabytes * 100.0) / 100.0)+" MB";
			else if(kilobytes >1)
				size= (Math.round(kilobytes * 100.0) / 100.0)+" KB";
			else
				size= (Math.round(bytes * 100.0) / 100.0)+" B";
		}else{
			size= "File does not exists!";
		}
		return size;
	}

	public static void deleteFile(String fileName) {
		File file = new File(fileName);
		if(file.exists()) {
			if(file.delete()){
				System.out.println(fileName+" removed successfully");
			}else{
				System.out.println("Failed to remove "+fileName);
			}
		}
	}
	
	public static void deleteResource(String fileName) {
		String [] jarFileDetailsArr=fileName.split("[|]");
		deleteFile(jarFileDetailsArr[0]);
		if(jarFileDetailsArr.length>4) {
			deleteFile(jarFileDetailsArr[4]);
		}
	}

	public static String generateHtmlData(String jarFileDetails,String mavenDependencyCode) {
		String [] jarFileDetailsArr=jarFileDetails.split("[|]");
		mavenDependencyCode=mavenDependencyCode.replaceAll("<JAR_FILE_NAME>", jarFileDetailsArr[0]);
		mavenDependencyCode=mavenDependencyCode.replaceAll("<GROUP_ID>", jarFileDetailsArr[1]);
		mavenDependencyCode=mavenDependencyCode.replaceAll("<ARTIFACT_ID>", jarFileDetailsArr[2]);
		mavenDependencyCode=mavenDependencyCode.replaceAll("<VERSION>", jarFileDetailsArr[3]);
		mavenDependencyCode=mavenDependencyCode.replaceAll("<JAR_FILE_SIZE>", getFileSize(jarFileDetailsArr[0]));
		return mavenDependencyCode;
	}
	public static void generateHtmlFile(String fileName,String data) {
		ProcessUtil.deleteResource(fileName);
		String html =readFromFileAsString("template");
		html=html.replaceAll("<TABLE_BODY>", data);
		writeToFile(html, fileName);
	}
}

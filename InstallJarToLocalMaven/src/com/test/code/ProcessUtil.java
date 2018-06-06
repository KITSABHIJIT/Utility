package com.test.code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
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
	
	public static String readFromFileAsString(String fileName){
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

	public static void runMavenCommand(String jarFileDetails,String mavenCommand,String mavenDependency) {
		try {
			String [] jarFileDetailsArr=jarFileDetails.split("[|]");
			mavenCommand=mavenCommand.replaceAll("<JAR_FILE_NAME>", jarFileDetailsArr[0]);
			mavenCommand=mavenCommand.replaceAll("<GROUP_ID>", jarFileDetailsArr[1]);
			mavenCommand=mavenCommand.replaceAll("<ARTIFACT_ID>", jarFileDetailsArr[2]);
			mavenCommand=mavenCommand.replaceAll("<VERSION>", jarFileDetailsArr[3]);
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
			System.out.println("Dependency: "+mavenDependency);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteFile(String fileName) {
		String [] jarFileDetailsArr=fileName.split("[|]");
		String jarFile=jarFileDetailsArr[0];
		File file = new File(jarFile);
		if(file.exists()) {
			if(file.delete()){
				System.out.println(jarFile+" removed successfully");
			}else{
				System.out.println("Failed to remove "+jarFile);
			}
		}
	}
	
	public static String generateHtmlData(String jarFileDetails,String mavenDependencyCode) {
		String [] jarFileDetailsArr=jarFileDetails.split("[|]");
		mavenDependencyCode=mavenDependencyCode.replaceAll("<JAR_FILE_NAME>", jarFileDetailsArr[0]);
		mavenDependencyCode=mavenDependencyCode.replaceAll("<GROUP_ID>", jarFileDetailsArr[1]);
		mavenDependencyCode=mavenDependencyCode.replaceAll("<ARTIFACT_ID>", jarFileDetailsArr[2]);
		mavenDependencyCode=mavenDependencyCode.replaceAll("<VERSION>", jarFileDetailsArr[3]);
		return mavenDependencyCode;
	}
	public static void generateHtmlFile(String fileName,String data) {
		ProcessUtil.deleteFile(fileName);
		String html =readFromFileAsString("html/template");
		html=html.replaceAll("<TABLE_BODY>", data);
		writeToFile(html, fileName);
	}
}
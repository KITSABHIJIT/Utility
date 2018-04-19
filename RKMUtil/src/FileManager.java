

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public class FileManager {

	public static synchronized InputStream tryToLoadFromEverywhere(String fileName){
		InputStream result = null;
		result = System.class.getResourceAsStream(fileName);
		if (result != null) {
			return result;
		}

		result = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
		if (result != null) {
			return result;
		}

		result = Thread.currentThread().getClass().getResourceAsStream(fileName);
		if (result != null) {
			return result;
		}

		result = ClassLoader.getSystemResourceAsStream(fileName);
		if (result != null) {
			return result;
		}
		result = ClassLoader.getSystemClassLoader().getResourceAsStream(fileName);
		if (result != null) {
			return result;
		}
		try {
			result = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}



	public static String getData(String fileName){
		String data="";
		BufferedReader br = null;
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(fileName));
			while ((sCurrentLine = br.readLine()) != null) {
				data+=sCurrentLine;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  finally {
			closeResource(br,fileName);
		}
		return data;
	}
	
	
	public static void closeResource(Object obj, String fileName){
		try {
			if(obj instanceof BufferedWriter) {
				BufferedWriter newObj = (BufferedWriter) obj;
				newObj.close();
			}
			if(obj instanceof FileWriter) {
				FileWriter newObj = (FileWriter) obj;
				newObj.close();
			}
			if(obj instanceof InputStream) {
				InputStream newObj = (InputStream) obj;
				newObj.close();
			}
			if(obj instanceof BufferedReader) {
				BufferedReader newObj = (BufferedReader) obj;
				newObj.close();
			}
			if(obj instanceof RandomAccessFile) {
				RandomAccessFile newObj = (RandomAccessFile) obj;
				newObj.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	
	
	
	public static void main(String args[]){
		System.out.println(19.9/10);
	}
	
}
package com.staples.cards.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.staples.cards.exception.ServiceErrorCodes;
import com.staples.cards.exception.ServiceException;
import com.staples.cards.exception.ServiceExceptionFactory;
import com.staples.cards.logger.CardLogger;

public class FileManager {

	public static synchronized InputStream tryToLoadFromEverywhere(String fileName) throws ServiceException{
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
			throw ServiceExceptionFactory.create(e,
					ServiceErrorCodes.FILE_NOT_FOUND_EXCEPTION_CODE,
					ServiceErrorCodes.FILE_NOT_FOUND_EXCEPTION+fileName);
		}
		return result;
	}


	public static Properties getPropertiesObject(String fileName) throws ServiceException {
		Properties properties = new Properties();
		InputStream fileInputStream = tryToLoadFromEverywhere(fileName);
		try {
			properties.load(fileInputStream);
		} catch (IOException e) {
			throw ServiceExceptionFactory.create(e,
					ServiceErrorCodes.PROPERTIES_LOAD_EXCEPTION_CODE,
					ServiceErrorCodes.PROPERTIES_LOAD_EXCEPTION+fileName);
		} finally {
			closeResource(fileInputStream, fileName);
		}
		return properties;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	public static Map<String, String> readPropertiesFileAsMap(String fileName) throws ServiceException {
		Properties properties = null;
		properties = getPropertiesObject(fileName);
		return new LinkedHashMap<String, String>((Map) properties);
	}

	public static List<String> getData(String fileName) throws ServiceException{
		List<String> data=new ArrayList<String>();
		BufferedReader br = null;
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(fileName));
			while ((sCurrentLine = br.readLine()) != null) {
				data.add(sCurrentLine);
			}
		} catch (FileNotFoundException e) {
			throw ServiceExceptionFactory.create(e,
					ServiceErrorCodes.FILE_NOT_FOUND_EXCEPTION_CODE,
					ServiceErrorCodes.FILE_NOT_FOUND_EXCEPTION+fileName);
		} catch (IOException e) {
			throw ServiceExceptionFactory.create(e,
					ServiceErrorCodes.FILE_READ_EXCEPTION_CODE,
					ServiceErrorCodes.FILE_READ_EXCEPTION+fileName);
		}  finally {
			closeResource(br,fileName);
		}
		return data;
	}
	public static int countLines(String fileName) throws ServiceException{
		InputStream is=null;
		int count = 0;
		boolean empty = true;
		try {
			is = new BufferedInputStream(new FileInputStream(fileName));
			byte[] c = new byte[1024];
			int readChars = 0;
			while ((readChars = is.read(c)) != -1) {
				empty = false;
				for (int i = 0; i < readChars; ++i) {
					if (c[i] == '\n') {
						++count;
					}
				}
			}
		}catch (FileNotFoundException e) {
			throw ServiceExceptionFactory.create(e,
					ServiceErrorCodes.FILE_NOT_FOUND_EXCEPTION_CODE,
					ServiceErrorCodes.FILE_NOT_FOUND_EXCEPTION+fileName);
		} catch (IOException e) {
			throw ServiceExceptionFactory.create(e,
					ServiceErrorCodes.FILE_READ_EXCEPTION_CODE,
					ServiceErrorCodes.FILE_READ_EXCEPTION+fileName);
		} finally {
			closeResource(is,fileName);
		}
		return (count == 0 && !empty) ? 1 : count;
	}
	public static void writeToFile(String content,String dir, String fileName, boolean append) throws ServiceException{
		BufferedWriter bw=null;
		FileWriter fw=null;
		try {
			dir=dir+"/"+DateUtil.getDateString("yyyyMMdd")+"/";
			File fileDir = new File(dir);
			// if fileDir doesnt exists, then create it
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}
			File file = new File(dir+fileName);
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
				CardLogger.debug(dir+fileName+" created successfully");
			}
			fw = new FileWriter(file.getAbsoluteFile(),append);
			bw = new BufferedWriter(fw);
			if(append){
				bw.newLine();
			}
			bw.write(content);
		} catch (IOException e) {
			throw ServiceExceptionFactory.create(e,
					ServiceErrorCodes.FILE_NOT_FOUND_EXCEPTION_CODE,
					ServiceErrorCodes.FILE_NOT_FOUND_EXCEPTION+fileName);
		}finally {
			//closeResource(fw,fileName);
			closeResource(bw,fileName);
		}
	}
	public static void closeResource(Object obj, String fileName) throws ServiceException{
		Object object=null;
		try {
			if(obj instanceof BufferedWriter) {
				BufferedWriter newObj = (BufferedWriter) obj;
				object=newObj;
				newObj.close();
			}
			if(obj instanceof FileWriter) {
				FileWriter newObj = (FileWriter) obj;
				object=newObj;
				newObj.close();
			}
			if(obj instanceof InputStream) {
				InputStream newObj = (InputStream) obj;
				object=newObj;
				newObj.close();
			}
			if(obj instanceof BufferedReader) {
				BufferedReader newObj = (BufferedReader) obj;
				object=newObj;
				newObj.close();
			}
		} catch (IOException e) {
			throw ServiceExceptionFactory.create(e,
					ServiceErrorCodes.RESOURCE_CLOSING_EXCEPTION_CODE,
					ServiceErrorCodes.RESOURCE_CLOSING_EXCEPTION+fileName+" # "+object);
		}
	}

}
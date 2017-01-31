package com.myutility.code;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

	 public static void main(String[] args) throws Exception {
		 String directory="C:\\Users\\royab001\\My Scans";
		 String zipFileName="C:\\Users\\royab001\\Desktop\\MyScan.zip";
		 ZipUtil.zipAllFiles(directory, zipFileName);
	 }
	 public static void zipAllFiles(String sourceDirectory, String zipFile) {

		 byte[] buffer = new byte[1024];

		 try{
			 FileOutputStream fos = new FileOutputStream(zipFile);
			 ZipOutputStream zos = new ZipOutputStream(fos);

			 System.out.println("Output to Zip : " + zipFile);

			 for(Object file : FileUtil.getListOfFiles(sourceDirectory,false)){

				 System.out.println("File Added : " + file);
				 ZipEntry ze= new ZipEntry((String)file);
				 zos.putNextEntry(ze);

				 FileInputStream in =new FileInputStream(sourceDirectory + File.separator + file);

				 int len;
				 while ((len = in.read(buffer)) > 0) {
					 zos.write(buffer, 0, len);
				 }

				 in.close();
			 }
			 zos.closeEntry();
			 zos.close();
			 System.out.println("Done");
		 }catch(IOException ex){
			 ex.printStackTrace();   
		 }
	 }

}

package com.myutility.code;

import java.io.File;

public class MassDeleteOldFiles
{
     public static void main(String[] args) {
         MassDeleteOldFiles massDelOldFiles = new MassDeleteOldFiles();
         massDelOldFiles.deleteFilesOlderThanNdays(30,"C:\\Temp\\");
     }
 
     public void deleteFilesOlderThanNdays(int daysBack, String dirWay)
     {
         File directory = new File(dirWay);
         if(directory.exists())
         {   
             File[] listFiles = directory.listFiles();
             long purgeTime = System.currentTimeMillis() - (daysBack * 24 * 60 * 60 * 1000);
             System.out.println("File will be deleted before: " + DateUtil.milliToDate(purgeTime));
             for(File listFile : listFiles)
             {
                 if(listFile.lastModified() < purgeTime)
                 {
                     System.out.println("File or directory name: " + listFile);
                     if (listFile.isFile())
                     {
                    	 //listFile.delete();
                         System.out.println("This is a file: " + listFile);
                         System.out.println("listFile.lastModified()" + DateUtil.milliToDate(listFile.lastModified()));
                     }
                     else
                     {
                         System.out.println("This is a directory: " + listFile);
                         System.out.println("Date last modified: " + listFile.lastModified());
                         deleteFilesOlderThanNdays(daysBack, listFile.getAbsolutePath());
                     }
                 }
             }
         } 
         else
        	 System.out.println("Files were not deleted, directory " + dirWay + " does'nt exist!");
     }
 }
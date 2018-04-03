package com.test.logReader;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class LogReader {
	public static void main(String ... args) throws IOException {
		File file=new File("C:/DynamicLog/logging.log");
		
        if (!file.exists() || file.isDirectory() || !file.canRead()) {
            throw new IOException("Can't read this file.");
        }
        
        long filePointer = file.length();
        
        System.out.println("Log tailing started on " + file.getName());
    
        try {
            while (true) {
                Thread.sleep(200);
                long len = file.length();
                if (len < filePointer) {
                    // Log must have been rolled or deleted.
                	System.out.println("Log file was reset. Restarting logging from start of file.");
                    filePointer = len;
                }
                else if (len > filePointer) {
                    // File must have had something added to it!
                    RandomAccessFile raf = new RandomAccessFile(file, "r");
                    raf.seek(filePointer);
                    String line = null;
                    while ((line = raf.readLine()) != null) {
                    	System.out.println(line);
                    }
                    filePointer = raf.getFilePointer();
                    raf.close();
                }
            }
        }
        catch (Exception e) {
        	System.out.println("Fatal error reading log file, log tailing has stopped.");
        }
	}
}

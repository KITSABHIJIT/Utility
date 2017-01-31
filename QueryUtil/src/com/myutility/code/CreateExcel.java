package com.myutility.code;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class CreateExcel {
	public static void createExcel(Map < String, Object[] > queryData,String fileName){
		//Create blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook(); 
		//Create a blank sheet
		XSSFSheet spreadsheet = workbook.createSheet("Output Data");
		//Create row object
		XSSFRow row;
		
				//Iterate over data and write to sheet
				Set < String > keyid = queryData.keySet();
				int rowid = 0;
				for (String key : keyid)
				{
					row = spreadsheet.createRow(rowid++);
					Object [] objectArr = queryData.get(key);
					int cellid = 0;
					for (Object obj : objectArr)
					{
						Cell cell = row.createCell(cellid++);
						cell.setCellValue((String)obj);
					}
				}
				//Write the workbook in file system
				FileOutputStream out;
				try {
					out = new FileOutputStream( 
							new File(fileName));
					workbook.write(out);
					out.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println( 
						"Writesheet.xlsx written successfully" );
	}
}

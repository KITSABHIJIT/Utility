package com.util.read.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.util.pojo.BurlapRecord;
import com.util.pojo.ExcelSheet;
import com.util.pojo.SQLObject;

public class ReadExcel {
	public static String FILE_NAME="C:\\Users\\royab001\\Desktop\\work done\\Galaxy Remediation\\SQL Details\\GLRMFLDLST - GALAXY Field List_Modified.xls";

	public static final String DRIVE=System.getProperty("user.home")+System.getProperty("file.separator")+"Google Drive"+System.getProperty("file.separator");
	public static final String DRIVEFILE=DRIVE+System.getProperty("file.separator")+"Work Orders.xls";

	public static void readXLSFile() throws IOException
	{
		InputStream ExcelFileToRead = new FileInputStream(FILE_NAME);
		HSSFWorkbook wb = new HSSFWorkbook(ExcelFileToRead);

		HSSFSheet sheet=wb.getSheetAt(0);
		HSSFRow row; 
		HSSFCell cell;

		Iterator rows = sheet.rowIterator();

		while (rows.hasNext())
		{
			row=(HSSFRow) rows.next();
			Iterator cells = row.cellIterator();
			int columnCounter=0;
			while (cells.hasNext())
			{
				cell=(HSSFCell) cells.next();

				if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
				{
					System.out.print(cell.getStringCellValue()+" ");
				}
				else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
				{
					System.out.print(cell.getNumericCellValue()+" ");
				}
				else
				{
					//U Can Handel Boolean, Formula, Errors
				}
				columnCounter++;
				if(columnCounter==9){
					break;
				}
			}
			System.out.println();
		}

	}

	public static List<SQLObject> readXLSFileSpecificColumns(String fileName) throws IOException
	{
		InputStream ExcelFileToRead = new FileInputStream(fileName);
		HSSFWorkbook wb = new HSSFWorkbook(ExcelFileToRead);
		List<SQLObject> sqlObjectList = new ArrayList<SQLObject>();
		HSSFSheet sheet=wb.getSheetAt(0);
		HSSFRow row; 
		Iterator rows = sheet.rowIterator();
		int rowCount=0;
		while (rows.hasNext())
		{
			row=(HSSFRow) rows.next();
			if(rowCount>0){
				SQLObject sqlObject=new SQLObject();
				HSSFCell cellA = row.getCell((short) 0);
				HSSFCell cellB = row.getCell((short) 1);
				HSSFCell cellC = row.getCell((short) 2);
				HSSFCell cellD = row.getCell((short) 3);
				HSSFCell cellE = row.getCell((short) 4);
				HSSFCell cellF = row.getCell((short) 5);
				HSSFCell cellG = row.getCell((short) 6);
				HSSFCell cellH = row.getCell((short) 7);
				HSSFCell cellI = row.getCell((short) 8);


				if(!StringUtil.isBlankOrEmpty(cellA.getStringCellValue())){
					sqlObject.setTableName(cellA.getStringCellValue());
					sqlObject.setColumnName(cellB.getStringCellValue());
					sqlObject.setColumnLength(cellC.getNumericCellValue());
					sqlObject.setNoOfDigit(cellD.getNumericCellValue());
					sqlObject.setDecimalPosition(cellE.getNumericCellValue());
					sqlObject.setDataType(cellF.getStringCellValue());
					sqlObject.setColumnText(cellG.getStringCellValue());
					sqlObject.setAllowNullValue(cellH.getStringCellValue());
					sqlObject.setDefaultValueSystemName(cellI.getStringCellValue());
					sqlObjectList.add(sqlObject);
				}
			}
			rowCount++;
		}
		return sqlObjectList;
	}
	
	
	
	public static List<ExcelSheet> readXLSFileColumns(String fileName) throws IOException
	{
		InputStream ExcelFileToRead = new FileInputStream(fileName);
		HSSFWorkbook wb = new HSSFWorkbook(ExcelFileToRead);
		List<ExcelSheet> sqlObjectList = new ArrayList<ExcelSheet>();
		HSSFSheet sheet=wb.getSheetAt(0);
		HSSFRow row; 
		Iterator rows = sheet.rowIterator();
		int rowCount=0;
		while (rows.hasNext())
		{
			row=(HSSFRow) rows.next();
			if(rowCount>0){
				ExcelSheet sqlObject=new ExcelSheet();
				HSSFCell cellA = row.getCell((short) 0);
				HSSFCell cellB = row.getCell((short) 1);
				HSSFCell cellC = row.getCell((short) 2);
				HSSFCell cellD = row.getCell((short) 3);
				HSSFCell cellE = row.getCell((short) 4);
				HSSFCell cellF = row.getCell((short) 5);
				HSSFCell cellG = row.getCell((short) 6);
				HSSFCell cellH = row.getCell((short) 7);
				HSSFCell cellI = row.getCell((short) 8);
				HSSFCell cellJ = row.getCell((short) 9);
				HSSFCell cellK = row.getCell((short) 10);
				HSSFCell cellL = row.getCell((short) 11);
				HSSFCell cellM = row.getCell((short) 12);
				HSSFCell cellN = row.getCell((short) 13);


				if(!StringUtil.isBlankOrEmpty(cellA.getStringCellValue())){
					sqlObject.setColA(cellA.getStringCellValue());
					sqlObject.setColB(cellB.getStringCellValue());
					sqlObject.setColC(cellC.getStringCellValue());
					sqlObject.setColD(cellD.getNumericCellValue());
					sqlObject.setColE(cellE.getNumericCellValue());
					sqlObject.setColF(cellF.getStringCellValue());
					sqlObject.setColG(cellG.getStringCellValue());
					sqlObject.setColH(cellH.getStringCellValue());
					sqlObject.setColI(cellI.getStringCellValue());
					sqlObject.setColJ(cellJ.getStringCellValue());
					sqlObject.setColK(cellK.getNumericCellValue());
					sqlObject.setColL(cellL.getStringCellValue());
					sqlObject.setColM(cellM.getStringCellValue());
					sqlObject.setColN(cellN.getStringCellValue());
					sqlObjectList.add(sqlObject);
				}
			}
			rowCount++;
		}
		return sqlObjectList;
	}
	

	public static void readXLSXFile() throws IOException
	{
		InputStream ExcelFileToRead = new FileInputStream(FILE_NAME);
		XSSFWorkbook  wb = new XSSFWorkbook(ExcelFileToRead);

		XSSFWorkbook test = new XSSFWorkbook(); 

		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFRow row; 
		XSSFCell cell;

		Iterator rows = sheet.rowIterator();

		while (rows.hasNext())
		{
			row=(XSSFRow) rows.next();
			Iterator cells = row.cellIterator();
			while (cells.hasNext())
			{
				cell=(XSSFCell) cells.next();

				if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING)
				{
					System.out.print(cell.getStringCellValue()+" ");
				}
				else if(cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
				{
					System.out.print(cell.getNumericCellValue()+" ");
				}
				else
				{
					//U Can Handel Boolean, Formula, Errors
				}
			}
			System.out.println();
		}

	}
	
	public static List<BurlapRecord> readCraftRecordXLSFileColumns(String fileName) throws IOException
	{
		InputStream ExcelFileToRead = new FileInputStream(fileName);
		HSSFWorkbook wb = new HSSFWorkbook(ExcelFileToRead);
		List<BurlapRecord> craftRecordList = new ArrayList<BurlapRecord>();
		HSSFSheet sheet=wb.getSheetAt(0);
		HSSFRow row; 
		Iterator rows = sheet.rowIterator();
		int rowCount=0;
		while (rows.hasNext())
		{
			row=(HSSFRow) rows.next();
			if(rowCount>0){
				BurlapRecord craftRecord=new BurlapRecord();
				HSSFCell cellA = row.getCell((short) 0);
				HSSFCell cellB = row.getCell((short) 1);
				HSSFCell cellC = row.getCell((short) 2);
				HSSFCell cellD = row.getCell((short) 3);
				HSSFCell cellE = row.getCell((short) 4);
				HSSFCell cellF = row.getCell((short) 5);
				HSSFCell cellG = row.getCell((short) 6);
				HSSFCell cellH = row.getCell((short) 7);
				HSSFCell cellI = row.getCell((short) 8);
				HSSFCell cellJ = row.getCell((short) 9);
				HSSFCell cellK = row.getCell((short) 10);
				HSSFCell cellL = row.getCell((short) 11);
				HSSFCell cellM = row.getCell((short) 12);
				HSSFCell cellN = row.getCell((short) 13);
				HSSFCell cellP = row.getCell((short) 14);
				HSSFCell cellQ = row.getCell((short) 15);


				if(!StringUtil.isBlankOrEmpty(cellA.getStringCellValue())){
					craftRecord.setRetailer(cellA.getStringCellValue());
					craftRecord.setOrderDate(cellB.getDateCellValue());
					craftRecord.setItem(cellC.getStringCellValue());
					craftRecord.setOrderNo(cellD.getStringCellValue());
					craftRecord.setQuantity(Math.round(cellE.getNumericCellValue()));
					craftRecord.setTaskCompleted(cellF.getStringCellValue());
					craftRecord.setMakingCharge(cellG.getNumericCellValue());
					craftRecord.setShippingCharge(cellH.getNumericCellValue());
					craftRecord.setPaid(cellJ.getStringCellValue());
					craftRecord.setShipped(!StringUtil.isBlankOrEmpty(cellK)?cellK.getStringCellValue():"");
					craftRecord.setShippingMode(!StringUtil.isBlankOrEmpty(cellL)?cellL.getStringCellValue():"");
					craftRecord.setShippingUser(!StringUtil.isBlankOrEmpty(cellM)?cellM.getStringCellValue():"");
					craftRecord.setItemDesc(!StringUtil.isBlankOrEmpty(cellN)?cellN.getStringCellValue():"");
					craftRecord.setColor(!StringUtil.isBlankOrEmpty(cellP)?cellP.getStringCellValue():"");
					craftRecord.setShippingBatchNo(!StringUtil.isBlankOrEmpty(cellQ)?cellQ.getStringCellValue():"");
					craftRecordList.add(craftRecord);
				}
			}
			rowCount++;
		}
		return craftRecordList;
	}
	public static void main(String[] args) throws IOException {

		//readXLSFileSpecificColumns(FILE_NAME);
		readCraftRecordXLSFileColumns(DRIVEFILE);

		//readXLSXFile();

	}
}

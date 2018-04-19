package com.test.controller;

import java.io.IOException;
import java.util.List;

import com.test.exceptions.ServiceException;
import com.util.pojo.BurlapRecord;
import com.util.read.excel.ReadExcel;


public class StartController {
	public static final String DRIVE=System.getProperty("user.home")+System.getProperty("file.separator")+"Google Drive"+System.getProperty("file.separator");
	public static final String DRIVEFILE1=DRIVE+System.getProperty("file.separator")+"Work Orders.xls";

	
	public static void main(String ... args){
		try {
			List<BurlapRecord> recordList=ReadExcel.readCraftRecordXLSFileColumns(DRIVEFILE1);
			for(BurlapRecord craftRecord:recordList){
			//	Controller.insertData(craftRecord);
			}
			Controller.displayData();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package com.test.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test.exceptions.ServiceException;
import com.util.pojo.BurlapRecord;
import com.util.pojo.ExpenseRecord;
import com.util.pojo.RecordContainer;
import com.util.read.excel.StringUtil;

public class Controller {

	public static final String DIR=System.getProperty("user.home")+System.getProperty("file.separator")+"RecordKeeper";
	public static final String DRIVE=System.getProperty("user.home")+System.getProperty("file.separator")+"Google Drive"+System.getProperty("file.separator")+"RecordKeeper";
	public static String FILE=DIR+System.getProperty("file.separator")+"RecordKeeper.ser";
	public static final String DRIVEFILE=DRIVE+System.getProperty("file.separator")+"RecordKeeper.ser";

	public static boolean insertData(Object obj) throws ServiceException{
		boolean result=false;
		File directory=new File(DIR);
		if(!directory.exists()){
			directory.mkdirs();
		}
		File dataFile=new File(FILE);

		if(obj instanceof BurlapRecord){
			BurlapRecord entry = (BurlapRecord)obj;
			Map<String,BurlapRecord> data=new HashMap<String,BurlapRecord>();
			if(dataFile.exists()){
				RecordContainer container=(RecordContainer)StoreRetreiveData.retrieveData(FILE);
				data=container.getBurlapRecord();
				entry.setRecordId(data.size()+1);
				data.put(entry.getOrderNo(),entry);
				container.setBurlapRecord(data);
				StoreRetreiveData.storeData(container,FILE);
				result=true;
			}else{
				RecordContainer container=new RecordContainer();
				entry.setRecordId(data.size()+1);
				data.put(entry.getOrderNo(),entry);
				container.setBurlapRecord(data);
				StoreRetreiveData.storeData(container,FILE);
				result=true;
			}
		}else if(obj instanceof ExpenseRecord){
			ExpenseRecord entry = (ExpenseRecord)obj;
			List<ExpenseRecord> data=new ArrayList<ExpenseRecord>();
			if(dataFile.exists()){
				RecordContainer container=(RecordContainer)StoreRetreiveData.retrieveData(FILE);
				data=container.getExpenseRecord();
				entry.setExpenseId(data.size()+1);
				data.add(entry);
				container.setExpenseRecord(data);
				StoreRetreiveData.storeData(container,FILE);
				result=true;
			}else{
				RecordContainer container=new RecordContainer();
				entry.setExpenseId(data.size()+1);
				data.add(entry);
				container.setExpenseRecord(data);
				StoreRetreiveData.storeData(container,FILE);
				result=true;
			}
		}
		return result;
	}

	public static boolean deleteData(Object obj) throws ServiceException{
		boolean result=false;
		File directory=new File(DIR);
		if(!directory.exists()){
			directory.mkdirs();
		}
		File dataFile=new File(FILE);
		if(obj instanceof BurlapRecord){
			BurlapRecord entry = (BurlapRecord)obj;
			Map<String,BurlapRecord> data=new HashMap<String,BurlapRecord>();
			if(dataFile.exists()){
				RecordContainer container=(RecordContainer)StoreRetreiveData.retrieveData(FILE);
				data=container.getBurlapRecord();
				for (Map.Entry<String,BurlapRecord> record : (container.getBurlapRecord()).entrySet()) {
					if(entry.getOrderNo().equals(record.getKey())){
						data.remove(record.getKey());
					}
				}
				container.setBurlapRecord(data);
				StoreRetreiveData.storeData(container,FILE);
				result=true;
			}
		}else if(obj instanceof ExpenseRecord){
			ExpenseRecord entry = (ExpenseRecord)obj;
			List<ExpenseRecord> data=new ArrayList<ExpenseRecord>();
			if(dataFile.exists()){
				RecordContainer container=(RecordContainer)StoreRetreiveData.retrieveData(FILE);
				data=container.getExpenseRecord();
				for (int i=0;i<container.getExpenseRecord().size();i++){
					if(container.getExpenseRecord().get(i).getExpenseId()== entry.getExpenseId()){
						data.remove(i);
					}
				}
				container.setExpenseRecord(data);
				StoreRetreiveData.storeData(container,FILE);
				result=true;
			}
		}
		return result;

	}

	public static boolean upadateData(BurlapRecord entry) throws ServiceException{
		boolean result=false;
		deleteData(entry);
		result=insertData(entry);
		return result;
	}

	public static void displayData() throws ServiceException{
		File dataFile=new File(FILE);
		if(dataFile.exists()){
			RecordContainer container=(RecordContainer)StoreRetreiveData.retrieveData(FILE);
			for(Map.Entry<String,BurlapRecord> record : (container.getBurlapRecord()).entrySet()){
				System.out.println(record.getValue().toString());
			}
			if(!StringUtil.isBlankOrEmpty(container.getExpenseRecord())){
				for(ExpenseRecord record:container.getExpenseRecord()){
					record.toString();
				}
			}
		}
	}

	public static boolean isBlankOrEmpty(Object obj){
		if(null==obj){
			return true;
		}else{
			String temp=obj.toString();
			if("".equals(temp.trim())){
				return true;
			}else{
				return false;
			}
		}
	}
}

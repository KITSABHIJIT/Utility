package com.test.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.test.exceptions.ServiceException;
import com.test.model.DecryptedEntry;
import com.test.model.EncryptedContainer;
import com.test.model.EncryptedEntry;

public class Controller {

	public static final String DIR=System.getProperty("user.home")+System.getProperty("file.separator")+"PasswordProtector";
	public static final String DRIVE=System.getProperty("user.home")+System.getProperty("file.separator")+"Google Drive"+System.getProperty("file.separator")+"junk";
	public static String FILE=DIR+System.getProperty("file.separator")+"PasswordProtector.ser";
	public static final String DRIVEFILE=DRIVE+System.getProperty("file.separator")+"PasswordProtector.ser";

	public static boolean insertData(EncryptedEntry entry) throws ServiceException{
		boolean result=false;
		File directory=new File(DIR);
		if(!directory.exists()){
			directory.mkdirs();
		}
		List<EncryptedEntry> data=new ArrayList<EncryptedEntry>();
		File dataFile=new File(FILE);
		if(dataFile.exists()){
			EncryptedContainer container=(EncryptedContainer)StoreRetreiveData.retrieveData(FILE);
			data=container.getContainer();
			data.add(entry);
			container.setContainer(data);
			StoreRetreiveData.storeData(container,FILE);
			result=true;
		}else{
			EncryptedContainer container=new EncryptedContainer();
			data.add(entry);
			container.setContainer(data);
			StoreRetreiveData.storeData(container,FILE);
			result=true;
		}
		return result;
	}

	public static boolean deleteData(EncryptedEntry entry) throws ServiceException{
		boolean result=false;
		File directory=new File(DIR);
		if(!directory.exists()){
			directory.mkdirs();
		}
		List<EncryptedEntry> data=new ArrayList<EncryptedEntry>();
		File dataFile=new File(FILE);
		if(dataFile.exists()){
			EncryptedContainer container=(EncryptedContainer)StoreRetreiveData.retrieveData(FILE);
			for(EncryptedEntry encryptedEntry:container.getContainer()){
				if(!ProtectRevealUtil.reveal(encryptedEntry.getEntity()).equals(ProtectRevealUtil.reveal(entry.getEntity()))){
					data.add(encryptedEntry);
				}
			}
			container.setContainer(data);
			StoreRetreiveData.storeData(container,FILE);
			result=true;
		}
		return result;

	}

	public static boolean upadateData(EncryptedEntry entry) throws ServiceException{
		boolean result=false;
		deleteData(entry);
		result=insertData(entry);
		return result;
	}

	public static void displayData() throws ServiceException{
		File dataFile=new File(FILE);
		if(dataFile.exists()){
			EncryptedContainer container=(EncryptedContainer)StoreRetreiveData.retrieveData(FILE);
			for(EncryptedEntry encryptedEntry:container.getContainer()){
				System.out.println("Entity: "+ProtectRevealUtil.reveal(encryptedEntry.getEntity()));
				System.out.println("User: "+ProtectRevealUtil.reveal(encryptedEntry. getUser()));
				System.out.println("Password: "+ProtectRevealUtil.reveal(encryptedEntry.getPwd()));
				//System.out.println("Password: "+encryptedEntry.getPwd());
				System.out.println();
			}
		}
	}
	
	public static List<DecryptedEntry> revealData() throws ServiceException{
		List<DecryptedEntry> revealedEntryList=new ArrayList<DecryptedEntry>();	 	
		for(EncryptedEntry encryptedEntry:retrieveData()){
			DecryptedEntry decryptedEntry=new DecryptedEntry();
			decryptedEntry.setEntity(ProtectRevealUtil.reveal(encryptedEntry. getEntity()));
			decryptedEntry.setUserId(ProtectRevealUtil.reveal(encryptedEntry. getUser()));
			decryptedEntry.setPassword(ProtectRevealUtil.reveal(encryptedEntry. getPwd()));
			revealedEntryList.add(decryptedEntry);
		}
		Collections.sort(revealedEntryList, new Comparator<DecryptedEntry>() {
			@Override
			public int compare(DecryptedEntry arg0, DecryptedEntry arg1) {
				return arg0.getEntity().compareTo(arg1.getEntity());
			}
	       } );
		return revealedEntryList;
	}
	
	public static DecryptedEntry revealSelectedData(String entity) throws ServiceException{
		DecryptedEntry decryptedEntry=new DecryptedEntry();
		for(DecryptedEntry entry:revealData()){
			if(entry.getEntity().equalsIgnoreCase(entity)){
				decryptedEntry=entry;
			}
		}
		return decryptedEntry;
	}
	
	public static List<EncryptedEntry> retrieveData() throws ServiceException{
		File dataFile=new File(FILE);
		List<EncryptedEntry> data =new ArrayList<EncryptedEntry>();
		if(dataFile.exists()){
			EncryptedContainer container=(EncryptedContainer)StoreRetreiveData.retrieveData(FILE);
			data.addAll(container.getContainer());
		}
		return data;
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

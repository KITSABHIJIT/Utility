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

public class Controller
{
    public static final String DIR;
    public static final String DRIVE;
    public static String FILE;
    public static final String DRIVEFILE;
    public static final String DRIVE_ICON_URL;
    public static final String TITLE_ICON_URL;
    public static final String VISIBLE_ICON_URL;
    
    static {
        DIR = String.valueOf(System.getProperty("user.home")) + System.getProperty("file.separator") + "PasswordProtector";
        DRIVE = String.valueOf(System.getProperty("user.home")) + System.getProperty("file.separator") + "Google Drive" + System.getProperty("file.separator") + "junk";
        Controller.FILE = String.valueOf(Controller.DIR) + System.getProperty("file.separator") + "PasswordProtector.ser";
        DRIVEFILE = String.valueOf(Controller.DRIVE) + System.getProperty("file.separator") + "PasswordProtector.ser";
        DRIVE_ICON_URL = String.valueOf(Controller.DRIVE) + System.getProperty("file.separator") + "googledrive.png";
        TITLE_ICON_URL = String.valueOf(Controller.DRIVE) + System.getProperty("file.separator") + "images.png";
        VISIBLE_ICON_URL = String.valueOf(Controller.DRIVE) + System.getProperty("file.separator") + "visible.png";
    }
    
    public static boolean insertData(final EncryptedEntry entry) throws ServiceException {
        boolean result = false;
        final File directory = new File(Controller.DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        List<EncryptedEntry> data = new ArrayList<EncryptedEntry>();
        final File dataFile = new File(Controller.FILE);
        if (dataFile.exists()) {
            final EncryptedContainer container = (EncryptedContainer)StoreRetreiveData.retrieveData(Controller.FILE);
            data = container.getContainer();
            data.add(entry);
            container.setContainer(data);
            StoreRetreiveData.storeData(container, Controller.FILE);
            result = true;
        }
        else {
            final EncryptedContainer container = new EncryptedContainer();
            data.add(entry);
            container.setContainer(data);
            StoreRetreiveData.storeData(container, Controller.FILE);
            result = true;
        }
        return result;
    }
    
    public static boolean deleteData(final EncryptedEntry entry) throws ServiceException {
        boolean result = false;
        final File directory = new File(Controller.DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        final List<EncryptedEntry> data = new ArrayList<EncryptedEntry>();
        final File dataFile = new File(Controller.FILE);
        if (dataFile.exists()) {
            final EncryptedContainer container = (EncryptedContainer)StoreRetreiveData.retrieveData(Controller.FILE);
            for (final EncryptedEntry encryptedEntry : container.getContainer()) {
                if (!ProtectRevealUtil.reveal(encryptedEntry.getEntity()).equals(ProtectRevealUtil.reveal(entry.getEntity()))) {
                    data.add(encryptedEntry);
                }
            }
            container.setContainer(data);
            StoreRetreiveData.storeData(container, Controller.FILE);
            result = true;
        }
        return result;
    }
    
    public static boolean upadateData(final EncryptedEntry entry) throws ServiceException {
        boolean result = false;
        deleteData(entry);
        result = insertData(entry);
        return result;
    }
    
    public static void displayData() throws ServiceException {
        final File dataFile = new File(Controller.FILE);
        if (dataFile.exists()) {
            final EncryptedContainer container = (EncryptedContainer)StoreRetreiveData.retrieveData(Controller.FILE);
            for (final EncryptedEntry encryptedEntry : container.getContainer()) {
                System.out.println("Entity: " + ProtectRevealUtil.reveal(encryptedEntry.getEntity()));
                System.out.println("User: " + ProtectRevealUtil.reveal(encryptedEntry.getUser()));
                System.out.println("Password: " + ProtectRevealUtil.reveal(encryptedEntry.getPwd()));
                System.out.println();
            }
        }
    }
    
    public static List<DecryptedEntry> revealData() throws ServiceException {
        final List<DecryptedEntry> revealedEntryList = new ArrayList<DecryptedEntry>();
        for (final EncryptedEntry encryptedEntry : retrieveData()) {
            final DecryptedEntry decryptedEntry = new DecryptedEntry();
            decryptedEntry.setEntity(ProtectRevealUtil.reveal(encryptedEntry.getEntity()));
            decryptedEntry.setUserId(ProtectRevealUtil.reveal(encryptedEntry.getUser()));
            decryptedEntry.setPassword(ProtectRevealUtil.reveal(encryptedEntry.getPwd()));
            revealedEntryList.add(decryptedEntry);
        }
        Collections.sort(revealedEntryList, new Comparator<DecryptedEntry>() {
            @Override
            public int compare(final DecryptedEntry arg0, final DecryptedEntry arg1) {
                return arg0.getEntity().compareTo(arg1.getEntity());
            }
        });
        return revealedEntryList;
    }
    
    public static DecryptedEntry revealSelectedData(final String entity) throws ServiceException {
        DecryptedEntry decryptedEntry = new DecryptedEntry();
        for (final DecryptedEntry entry : revealData()) {
            if (entry.getEntity().equalsIgnoreCase(entity)) {
                decryptedEntry = entry;
            }
        }
        return decryptedEntry;
    }
    
    public static List<EncryptedEntry> retrieveData() throws ServiceException {
        final File dataFile = new File(Controller.FILE);
        final List<EncryptedEntry> data = new ArrayList<EncryptedEntry>();
        if (dataFile.exists()) {
            final EncryptedContainer container = (EncryptedContainer)StoreRetreiveData.retrieveData(Controller.FILE);
            data.addAll(container.getContainer());
        }
        return data;
    }
    
    public static boolean isBlankOrEmpty(final Object obj) {
        if (obj == null) {
            return true;
        }
        final String temp = obj.toString();
        return "".equals(temp.trim());
    }
}
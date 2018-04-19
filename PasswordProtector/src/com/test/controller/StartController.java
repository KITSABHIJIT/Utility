package com.test.controller;

import com.test.exceptions.ServiceException;

public class StartController {

	public static void main(String ... args){
		try {
			ProtectRevealUtil.initialize();
			/*Controller.insertData(new EncryptedEntry("Employee1", "Abhijit Roy", "abhijit123"));
			Controller.insertData(new EncryptedEntry("Employee2", "Sourav Singh", "sourav123"));
			Controller.displayData();
			Controller.upadateData(new EncryptedEntry("Employee1", "Tanaya Roy", "tanaya123"));
			Controller.displayData();
			Controller.deleteData(new EncryptedEntry("Employee2", "Tanaya Roy", "tanaya123"));*/
			Controller.displayData();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

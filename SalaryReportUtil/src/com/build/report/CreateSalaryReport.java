package com.build.report;

import com.build.util.SalaryReportGenerator;

public class CreateSalaryReport {

	public static void main(String[] args) {
		String googleDrivePath="/Users/abhijit/Library/CloudStorage/GoogleDrive-cemkabhijit@gmail.com/My Drive/personal details/Citizens-Bank";
		String sourceDir=googleDrivePath+"/PayStubs";
		String destExcel=googleDrivePath+"/PayStubs/Citizens-PayStubs.xls";
		//SalaryReportGenerator.createTCSIndiaSalaryReport(sourceDir, destExcel);
		//SalaryReportGenerator.createTCSUSASalaryReport(sourceDir, destExcel);
		SalaryReportGenerator.createCitizensSalaryReport(sourceDir, destExcel);
	}

}

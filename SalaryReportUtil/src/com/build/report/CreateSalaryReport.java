package com.build.report;

import com.build.util.SalaryReportGenerator;

public class CreateSalaryReport {

	public static void main(String[] args) {
		String sourceDir="/Volumes/GoogleDrive/My Drive/personal details/Citizens-Bank/PayStubs";
		String destExcel="/Volumes/GoogleDrive/My Drive/personal details/Citizens-Bank/PayStubs/Citizens-PayStubs.xls";
		//SalaryReportGenerator.createTCSIndiaSalaryReport(sourceDir, destExcel);
		//SalaryReportGenerator.createTCSUSASalaryReport(sourceDir, destExcel);
		SalaryReportGenerator.createCitizensSalaryReport(sourceDir, destExcel);
	}

}

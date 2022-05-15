package com.build.util;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import com.build.bean.SalaryBean;
import com.build.bean.TableData;
import com.build.report.ReportExcel;

public class PDFToBean {

	public static void main(String args[]) {
		List<SalaryBean> result=new ArrayList<SalaryBean>();
		String sourceDir="/Users/abhijit/Desktop/TCS-PaySlips/";
		String destExcel="/Users/abhijit/Desktop/TCS-PaySlips.xls";
		for(String temp:readPDF(sourceDir)) {
			result.add(extractSalaryData(temp));
		}
		Collections.sort(result, new Comparator<SalaryBean>() {
			public int compare(SalaryBean u1, SalaryBean u2) {
				return u2.getSalaryDate().compareTo(u1.getSalaryDate());
			}
		});


		List<List<Object>> tableData=new ArrayList<List<Object>>();
		List<Object> tableHeader=new ArrayList<Object>();
		tableHeader.add("Salary Date");
		tableHeader.add("Basic");
		tableHeader.add("Bob Kitty Allowance");
		tableHeader.add("House Rent Allowance");
		tableHeader.add("Sundry Medical Allowance");
		tableHeader.add("Leave Travel Allowance");
		tableHeader.add("Personal Allowance");
		tableHeader.add("Variable Allowance");
		tableHeader.add("Conveyance NonTaxable");
		tableHeader.add("Conveyance Taxable");
		tableHeader.add("Overtime");
		tableHeader.add("Miscellaneous");
		tableHeader.add("City Allowance");
		tableHeader.add("Performance Pay");
		tableHeader.add("Provident Fund");
		tableHeader.add("Professional Tax");
		tableHeader.add("Income Tax");
		tableHeader.add("Health Insurance");
		tableHeader.add("Tcs Welfare Trust");
		tableHeader.add("Voluntary Provident Fund");
		tableHeader.add("Total Allowance");
		tableHeader.add("Total Earning");
		tableHeader.add("Total Deduction");
		tableHeader.add("Net Pay");
		tableHeader.add("US Net Pay1");
		tableHeader.add("US Net Pay2");
		

		tableHeader.add("Total ProvidentFund");
		tableHeader.add("Total Gratuity");
		
		tableData.add(tableHeader);
		for(SalaryBean bean:result) {
			List<Object> tableRow=new ArrayList<Object>();
			tableRow.add(bean.getSalaryDate());
			tableRow.add(bean.getBasic());
			tableRow.add(bean.getBobKittyAllowance());
			tableRow.add(bean.getHouseRentAllowance());
			tableRow.add(bean.getSundryMedicalAllowance());
			tableRow.add(bean.getLeaveTravelAllowance());
			tableRow.add(bean.getPersonalAllowance());
			tableRow.add(bean.getVariableAllowance());
			tableRow.add(bean.getConveyanceNonTaxable());
			tableRow.add(bean.getConveyanceTaxable());
			tableRow.add(bean.getOvertime());
			tableRow.add(bean.getMiscellaneous());
			tableRow.add(bean.getCityAllowance());
			tableRow.add(bean.getPerformancePay());
			tableRow.add(bean.getProvidentFund());
			tableRow.add(bean.getProfessionalTax());
			tableRow.add(bean.getIncomeTax());
			tableRow.add(bean.getHealthInsurance());
			tableRow.add(bean.getTcsWelfareTrust());
			tableRow.add(bean.getVoluntaryProvidentFund());
			tableRow.add(bean.getTotalAllowance());
			tableRow.add(bean.getTotalEarning());
			tableRow.add(bean.getTotalDeduction());
			tableRow.add(bean.getNetPay());
			tableRow.add(bean.getUsNetPay1());
			tableRow.add(bean.getUsNetPay2());
			tableRow.add(bean.getTotalProvidentFund());
			tableRow.add(bean.getTotalGratuity());
			tableData.add(tableRow);
		}
		TableData data = new TableData();
		data.setReportData(tableData);
		data.setRowPosition(0);
		data.setColumnPosition(0);
		ReportExcel.writeExcel(data, destExcel);

	} 

	public static List<String> readPDF(String sourceDir){
		List<String> data =new ArrayList<String>();
		try{
			File _folder = new File(sourceDir); 
			File[] filesInFolder; 
			filesInFolder = _folder.listFiles(); 
			for (File string : filesInFolder){ 
				if(!".DS_Store".equals(string.getName())) {
					String st = getTextFromPDF(string);
					data.add(string.getName()+"\n"+st);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	public static String getTextFromPDF(File fileName) {
		PDDocument document = null;
		String result=null;
		try {
			document = PDDocument.load(fileName);
			document.getClass();
			PDFTextStripperByArea stripper = new PDFTextStripperByArea();
			stripper.setSortByPosition( true );
			PDFTextStripper Tstripper = new PDFTextStripper();
			result = Tstripper.getText(document);
		}catch(Exception e){
			System.out.println("Error out file "+fileName);
			e.printStackTrace();
		}finally{
			try {
				if(null!=document) {
					document.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static SalaryBean extractSalaryData(String data) {
		SalaryBean salaryBean =new SalaryBean();
		int profesionalTaxCounter=0;
		int usPayCounter=0;
		List<String> myList = new ArrayList<String>(Arrays.asList(data.split("\n")));
		try {
			for(int i=0;i<myList.size();i++) {
				if(i==0) {
					salaryBean.setSalaryFile(myList.get(i));
					i=i+1;
				}
				if(myList.get(i).contains("Payslip generated on")) {
					salaryBean.setSalaryDate(DateUtil.getSomeDate(StringUtil.trim(myList.get(i).substring(myList.get(i).indexOf(":")+1,myList.get(i).indexOf(","))), "dd MMM yyyy"));
				}
				if(myList.get(i).contains("Basic Salary")) {
					salaryBean.setBasic(appendSpaceDelimitedAmounts(myList.get(i).substring(myList.get(i).indexOf("Basic Salary")+12)));
				}
				if(myList.get(i).contains("BoB Kitty Allowance")) {
					salaryBean.setBobKittyAllowance(appendSpaceDelimitedAmounts(myList.get(i).substring(myList.get(i).indexOf("BoB Kitty Allowance")+19)));
				}
				if(myList.get(i).contains("House Rent Allowance")) {
					salaryBean.setHouseRentAllowance(appendSpaceDelimitedAmounts(myList.get(i).substring(myList.get(i).indexOf("House Rent Allowance")+20)));
				}
				if(myList.get(i).contains("Sundry Medical")) {
					salaryBean.setSundryMedicalAllowance(appendSpaceDelimitedAmounts(myList.get(i).substring(myList.get(i).indexOf("Sundry Medical")+14)));
				}
				if(myList.get(i).contains("Leave Travel Allowance")) {
					salaryBean.setLeaveTravelAllowance(appendSpaceDelimitedAmounts(myList.get(i).substring(myList.get(i).indexOf("Leave Travel Allowance")+22)));
				}
				if(myList.get(i).contains("Personal Allowance")) {
					salaryBean.setPersonalAllowance(appendSpaceDelimitedAmounts(myList.get(i).substring(myList.get(i).indexOf("Personal Allowance")+18)));
				}
				if(myList.get(i).contains("Convy. Allowance Taxable")) {
					salaryBean.setConveyanceTaxable(appendSpaceDelimitedAmounts(myList.get(i).substring(myList.get(i).indexOf("Convy. Allowance Taxable")+24)));
				}
				if(myList.get(i).contains("Conveyance Non Taxable")) {
					salaryBean.setConveyanceNonTaxable(appendSpaceDelimitedAmounts(myList.get(i).substring(myList.get(i).indexOf("Conveyance Non Taxable")+22)));
				}
				if(myList.get(i).contains("Variable Allowance")) {
					salaryBean.setVariableAllowance(appendSpaceDelimitedAmounts(myList.get(i).substring(myList.get(i).indexOf("Variable Allowance")+18)));
				}
				if(myList.get(i).contains("Overtime")) {
					salaryBean.setOvertime(appendSpaceDelimitedAmounts(myList.get(i).substring(myList.get(i).indexOf("Overtime")+8)));
				}
				if(myList.get(i).contains("Miscellaneous")) {
					salaryBean.setMiscellaneous(appendSpaceDelimitedAmounts(myList.get(i).substring(myList.get(i).indexOf("Miscellaneous")+13)));
				}
				if(myList.get(i).contains("City Allowance")) {
					if(myList.get(i).contains("City Allowance Retro"))
						salaryBean.setCityAllowance(appendSpaceDelimitedAmounts(myList.get(i).substring(myList.get(i).indexOf("City Allowance Retro")+20)));
					else
						salaryBean.setCityAllowance(appendSpaceDelimitedAmounts(myList.get(i).substring(myList.get(i).indexOf("City Allowance")+14)));
				}

				salaryBean.setTotalAllowance(salaryBean.getBobKittyAllowance()
						+salaryBean.getHouseRentAllowance()
						+salaryBean.getSundryMedicalAllowance()
						+salaryBean.getLeaveTravelAllowance()
						+salaryBean.getPersonalAllowance()
						+salaryBean.getConveyanceTaxable()
						+salaryBean.getConveyanceNonTaxable()
						+salaryBean.getVariableAllowance());

				if(myList.get(i).contains("Performance Pay")) {
					if(!"Performance Pay".equals(StringUtil.trim(myList.get(i))))
						salaryBean.setPerformancePay(appendSpaceDelimitedAmounts(myList.get(i).substring(myList.get(i).indexOf("Performance Pay")+15)));

				}
				if(myList.get(i).contains("Professional Tax") && profesionalTaxCounter==0) {
					salaryBean.setProfessionalTax(StringUtil.getDoubleFromString(myList.get(i).substring(myList.get(i).indexOf("Professional Tax")+16),true));
					profesionalTaxCounter++;
				}
				if(myList.get(i).contains("Income Tax")) {
					salaryBean.setIncomeTax(StringUtil.getDoubleFromString(myList.get(i).substring(myList.get(i).indexOf("Income Tax")+10),true));
				}
				if(myList.get(i).contains("Health Insurance Scheme Premium")) {
					salaryBean.setHealthInsurance(StringUtil.getDoubleFromString(myList.get(i).substring(myList.get(i).indexOf("Health Insurance Scheme Premium")+31),true));
				}
				if(myList.get(i).contains("TCS Welfare Trust")) {
					salaryBean.setTcsWelfareTrust(StringUtil.getDoubleFromString(myList.get(i).substring(myList.get(i).indexOf("TCS Welfare Trust")+17),true));
				}
				if(myList.get(i).contains("US Net Pay 1 (1st Semi-Monthly)") && usPayCounter==0) {
					salaryBean.setUsNetPay1(StringUtil.getDoubleFromString(myList.get(i).substring(myList.get(i).indexOf("US Net Pay 1 (1st Semi-Monthly)")+31),true));
					usPayCounter++;
				}
				if(myList.get(i).contains("US Net Pay 2 (2nd Semi-Monthly)")) {
					salaryBean.setUsNetPay2(StringUtil.getDoubleFromString(myList.get(i).substring(myList.get(i).indexOf("US Net Pay 2 (2nd Semi-Monthly)")+31),true));
				}
				if(myList.get(i).contains("US Net Pay") && usPayCounter==0) {
					salaryBean.setUsNetPay2(StringUtil.getDoubleFromString(myList.get(i).substring(myList.get(i).indexOf("US Net Pay")+10),true));
					usPayCounter++;
				}
				if(myList.get(i).contains("Provident Fund") && !myList.get(i).contains("*")) {
					if(myList.get(i).startsWith("Arrears")) {
						salaryBean.setProvidentFund(salaryBean.getProvidentFund()+StringUtil.getDoubleFromString(myList.get(i).substring(myList.get(i).indexOf("Arrears Provident Fund")+22),true));
					}else if(myList.get(i).startsWith("Voluntary")){
						salaryBean.setVoluntaryProvidentFund(StringUtil.getDoubleFromString(myList.get(i).substring(myList.get(i).indexOf("Voluntary Provident Fund")+24),true));
					}else {
						salaryBean.setProvidentFund(StringUtil.getDoubleFromString(myList.get(i).substring(myList.get(i).indexOf("Provident Fund")+14),true));
					}
				}
				if(myList.get(i).contains("Provident Fund") && myList.get(i).contains("*")) {
					if("Provident Fund*".equals(myList.get(i))){
						i=i+1;
						salaryBean.setTotalProvidentFund(StringUtil.getDoubleFromString(myList.get(i),true));
					}else {
						salaryBean.setTotalProvidentFund(StringUtil.getDoubleFromString(myList.get(i).substring(myList.get(i).indexOf("Provident Fund*")+15),true));
					}

				}
				if(myList.get(i).contains("Gratuity")) {

					if("Gratuity".equals(myList.get(i))){
						i=i+1;
						salaryBean.setTotalGratuity(StringUtil.getDoubleFromString(myList.get(i),true));
					}else {
						salaryBean.setTotalGratuity(StringUtil.getDoubleFromString(myList.get(i).substring(myList.get(i).indexOf("Gratuity")+8),true));
					}
				}

				if(myList.get(i).contains("Total Earnings") && myList.get(i).contains("Total Deductions")) {
					salaryBean.setTotalEarning(StringUtil.getDoubleFromString(myList.get(i).substring(myList.get(i).indexOf("Total Earnings  (Current + Arrears)")+36,myList.get(i).indexOf("Total Deductions")),true));
					salaryBean.setTotalDeduction(StringUtil.getDoubleFromString(myList.get(i).substring(myList.get(i).indexOf("Total Deductions")+16),true));
				}

				if(myList.get(i).contains("Net Pay (INR)")) {
					double netPay=StringUtil.getDoubleFromString(myList.get(i).substring(myList.get(i).indexOf("Net Pay (INR)")+13),true);
					if(netPay==0) {
						salaryBean.setNetPay(salaryBean.getUsNetPay1()+salaryBean.getUsNetPay2());
					}else {
						salaryBean.setNetPay(netPay);
					}
				}
				//System.out.println(myList.get(i));
			}
		}catch(Exception e){
			System.out.println("Error out Data "+data);
			e.printStackTrace();
		}
		return salaryBean;
	}

	public static double appendSpaceDelimitedAmounts(String data) throws ParseException {
		double output =0D;
		if(!StringUtil.isBlankOrEmpty(data)) {
			for(String temp: data.split("[ ]")) {
				output+=StringUtil.getDoubleFromString(temp, true);
			}
		}
		return output;
	}
}

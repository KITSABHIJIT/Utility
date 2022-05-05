package com.build.pdf.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import com.build.beanToPdf.SalaryBean;

public class PDFToBean {

	public static void main(String args[]) {
		String sourceDir="/Users/abhijit/Desktop/TCS-PaySlips/";
		for(String temp:readPDF(sourceDir)) {
			extractSalaryData(temp);
		}
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
		double allownce=0D;
		List<String> myList = new ArrayList<String>(Arrays.asList(data.split("\n")));
		try {
			for(int i=0;i<myList.size();i++) {
				if(i==0) {
					salaryBean.setSalaryFile(myList.get(i));
					i=i+1;
				}
				if(myList.get(i).contains("Payslip generated on")) {
					salaryBean.setSalaryDate(DateUtil.getSomeDate(myList.get(i).substring(myList.get(i).indexOf(":")+1,myList.get(i).indexOf(",")), "dd MMM yyyy"));
				}
				if(myList.get(i).contains("Basic Salary")) {
					salaryBean.setBasic(StringUtil.getDoubleFromString(myList.get(i).substring(myList.get(i).indexOf("Basic Salary")+12),true));
				}
				
				
				if(myList.get(i).contains("BoB Kitty Allowance")) {
					allownce=StringUtil.getDoubleFromString(myList.get(i).substring(myList.get(i).indexOf("BoB Kitty Allowance")+19),true);
				}
				if(myList.get(i).contains("House Rent Allowance")) {
					allownce+=StringUtil.getDoubleFromString(myList.get(i).substring(myList.get(i).indexOf("House Rent Allowance")+20),true);
				}
				if(myList.get(i).contains("Sundry Medical")) {
					allownce+=StringUtil.getDoubleFromString(myList.get(i).substring(myList.get(i).indexOf("Sundry Medical")+14),true);
				}
				if(myList.get(i).contains("Leave Travel Allowance")) {
					allownce+=StringUtil.getDoubleFromString(myList.get(i).substring(myList.get(i).indexOf("Leave Travel Allowance")+22),true);
				}
				if(myList.get(i).contains("Personal Allowance")) {
					allownce+=StringUtil.getDoubleFromString(myList.get(i).substring(myList.get(i).indexOf("Personal Allowance")+18),true);
				}
				if(myList.get(i).contains("Convy. Allowance Taxable")) {
					allownce+=StringUtil.getDoubleFromString(myList.get(i).substring(myList.get(i).indexOf("Convy. Allowance Taxable")+24),true);
				}
				if(myList.get(i).contains("Conveyance Non Taxable")) {
					allownce+=StringUtil.getDoubleFromString(myList.get(i).substring(myList.get(i).indexOf("Conveyance Non Taxable")+22),true);
				}
				if(myList.get(i).contains("Variable Allowance")) {
					allownce+=StringUtil.getDoubleFromString(myList.get(i).substring(myList.get(i).indexOf("Variable Allowance")+18),true);
				}
				
				salaryBean.setAllowance(allownce);
				if(myList.get(i).contains("Performance Pay")) {
					salaryBean.setPerformancePay(StringUtil.getDoubleFromString(myList.get(i).substring(myList.get(i).indexOf("Performance Pay")+15),true));
				}
				if(myList.get(i).contains("Professional Tax")) {
					salaryBean.setProfessionalTax(StringUtil.getDoubleFromString(myList.get(i).substring(myList.get(i).indexOf("Professional Tax")+16),true));
				}
				if(myList.get(i).contains("Income Tax")) {
					salaryBean.setIncomeTax(StringUtil.getDoubleFromString(myList.get(i).substring(myList.get(i).indexOf("Income Tax")+10),true));
				}
				if(myList.get(i).contains("Health Insurance Scheme Premium")) {
					salaryBean.setHealthInsurance(StringUtil.getDoubleFromString(myList.get(i).substring(myList.get(i).indexOf("Health Insurance Scheme Premium")+31),true));
				}
				if(myList.get(i).contains("Provident Fund") && !myList.get(i).contains("*")) {
					salaryBean.setProvidentFund(StringUtil.getDoubleFromString(myList.get(i).substring(myList.get(i).indexOf("Provident Fund")+14),true));
				}

				if(myList.get(i).contains("Voluntary Provident Fund")) {
					salaryBean.setVoluntaryProvidentFund(StringUtil.getDoubleFromString(myList.get(i).substring(myList.get(i).indexOf("Voluntary Provident Fund")+24),true));
				}

				if(myList.get(i).contains("Provident Fund") && myList.get(i).contains("*")) {
					i=i+1;
					salaryBean.setTotalProvidentFund(StringUtil.getDoubleFromString(myList.get(i),true));
				}
				if(myList.get(i).contains("Gratuity")) {
					i=i+1;
					salaryBean.setTotalGratuity(StringUtil.getDoubleFromString(myList.get(i),true));
				}
				if(myList.get(i).contains("Net Pay (INR)")) {
					salaryBean.setNetPay(StringUtil.getDoubleFromString(myList.get(i).substring(myList.get(i).indexOf("Net Pay (INR)")+13),true));
				}
				
				if(myList.get(i).contains("Total Earnings") && myList.get(i).contains("Total Deductions")) {
					salaryBean.setTotalEarning(StringUtil.getDoubleFromString(myList.get(i).substring(myList.get(i).indexOf("Total Earnings  (Current + Arrears)")+36,myList.get(i).indexOf("Total Deductions")),true));
					salaryBean.setTotalDeduction(StringUtil.getDoubleFromString(myList.get(i).substring(myList.get(i).indexOf("Total Deductions")+16),true));
				}
				
				if(myList.get(i).contains("Net Pay (INR)")) {
					salaryBean.setNetPay(StringUtil.getDoubleFromString(myList.get(i).substring(myList.get(i).indexOf("Net Pay (INR)")+13),true));
				}

			}
		}catch(Exception e){
			System.out.println("Error out Data "+data);
			e.printStackTrace();
		}
		System.out.println(salaryBean.toString());
		return salaryBean;
	}
}

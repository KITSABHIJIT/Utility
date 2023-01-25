package com.build.util;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import com.build.bean.SalaryBean;
import com.build.bean.TableData;
import com.build.report.ReportExcel;

public class SalaryReportGenerator {

	public static void createTCSIndiaSalaryReport(String sourceDir,String destExcel) {
		List<SalaryBean> result=new ArrayList<SalaryBean>();
		for(String temp:readPDF(sourceDir)) {
			result.add(extractTCSIndiaSalaryData(temp));
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
	public static void createTCSUSASalaryReport(String sourceDir,String destExcel) {
		List<SalaryBean> result=extractTCSUSASalaryData(readPDF(sourceDir));
		if(result.size()>0) {
			Collections.sort(result, new Comparator<SalaryBean>() {
				public int compare(SalaryBean u1, SalaryBean u2) {
					return u2.getSalaryDate().compareTo(u1.getSalaryDate());
				}
			});


			List<List<Object>> tableData=new ArrayList<List<Object>>();
			List<Object> tableHeader=new ArrayList<Object>();
			tableHeader.add("Salary Date");
			tableHeader.add("Worked Hours");
			tableHeader.add("Indian Salary");
			tableHeader.add("Living Allowance");
			tableHeader.add("Federal Income Tax");
			tableHeader.add("Social Security Tax");
			tableHeader.add("Medicare Tax");
			tableHeader.add("MA State Income Tax");
			tableHeader.add("Dental Insurance");
			tableHeader.add("Is Recovery");
			tableHeader.add("Ma Fli Ma");
			tableHeader.add("Ma Mli Ee");
			tableHeader.add("Medical Insurance");
			tableHeader.add("Vision Insurance");
			tableHeader.add("401k Plan");
			tableHeader.add("Gross Pay");
			tableHeader.add("Net Pay");

			tableData.add(tableHeader);
			for(SalaryBean bean:result) {
				List<Object> tableRow=new ArrayList<Object>();
				tableRow.add(bean.getSalaryDate());
				tableRow.add(bean.getWorkHours());
				tableRow.add(bean.getIndianSalary());
				tableRow.add(bean.getLivingAllowance());
				tableRow.add(bean.getFederalIncomeTax());
				tableRow.add(bean.getSocialSecurityTax());
				tableRow.add(bean.getMedicareTax());
				tableRow.add(bean.getMaStateIncomeTax());
				tableRow.add(bean.getDentalInsurance());
				tableRow.add(bean.getIsRecovery());
				tableRow.add(bean.getMaFliMa());
				tableRow.add(bean.getMaMliEe());
				tableRow.add(bean.getMedicalInsurance());
				tableRow.add(bean.getVisionInsurance());
				tableRow.add(bean.getPlan401K());
				tableRow.add(bean.getGrossPay());
				tableRow.add(bean.getNetPay());
				tableData.add(tableRow);
				System.out.println(bean.toString());
			}
			TableData data = new TableData();
			data.setReportData(tableData);
			data.setRowPosition(0);
			data.setColumnPosition(0);
			ReportExcel.writeExcel(data, destExcel);
		}
	}
	
	
	
	public static List<String> readPDF(String sourceDir){
		List<String> data =new ArrayList<String>();
		try{
			File _folder = new File(sourceDir); 
			File[] filesInFolder; 
			filesInFolder = _folder.listFiles(); 
			for (File string : filesInFolder){ 
				if(!".DS_Store".equals(string.getName()) && !"xls".equals(FilenameUtils.getExtension(string.getName()))) {
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

	public static SalaryBean extractTCSIndiaSalaryData(String data) {
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

	public static List<SalaryBean>  extractTCSUSASalaryData(List<String> dataList) {
		List<SalaryBean> result=new ArrayList<SalaryBean>();
		boolean maFliEe,maMliEe,fringeBenefits,fringeBenefitsSingle,maPaidFamilyLeaveIns,dentalInsurance,visionInsurance,plan401K,intAdvance=false;
		for(String data:dataList) {
			//System.out.println(data);
			maFliEe=data.contains("Ma Fli Ee") || data.contains("Ma Fli Ma");
			maMliEe=data.contains("Ma Mli Ee");
			fringeBenefits=data.indexOf("Fringe Benefits")!=data.lastIndexOf("Fringe Benefits");
			fringeBenefitsSingle=data.indexOf("Fringe Benefits")==data.lastIndexOf("Fringe Benefits");
			maPaidFamilyLeaveIns=data.contains("MA Paid Family Leave Ins");
			dentalInsurance=data.contains("Dental");
			visionInsurance=data.contains("Vision");
			plan401K=data.contains("401K Plan");
			intAdvance=data.contains("Int Advance");
			SalaryBean salaryBean =new SalaryBean();
			List<String> myList = new ArrayList<String>(Arrays.asList(data.split("\n")));
			try {
				for(int i=0;i<myList.size();i++) {
					if(i==0) {
						salaryBean.setSalaryFile(myList.get(i));
						if(salaryBean.getSalaryFile().equals("20180207.pdf"))
								dentalInsurance=false;;
						i=i+1;
					}
					if(myList.get(i).startsWith("397912")) {
						i=i+1;
						salaryBean.setSalaryDate(DateUtil.getSomeDate(StringUtil.trim(myList.get(i).substring(myList.get(i).indexOf(":")+1)), "MM/dd/yyyy"));
					}
					if(myList.get(i).contains("Regular")) {
						String temp=myList.get(i).substring(myList.get(i).indexOf("Regular")+8);
						String [] tempArr=temp.split("[ ]");
						salaryBean.setWorkHours(StringUtil.getDoubleFromString(tempArr[2]+"."+tempArr[3],false));
					}
					if(myList.get(i).contains("Indian Salary")) {
						String temp=myList.get(i).substring(myList.get(i).indexOf("Indian Salary")+14);
						String [] tempArr=temp.split("[ ]");
						double tempDouble=StringUtil.getDoubleFromString((tempArr.length>2)?tempArr[0]+tempArr[1]+"."+tempArr[2]:tempArr[0]+"."+tempArr[1],false);
						if(tempDouble>1500)
							tempDouble=StringUtil.getDoubleFromString(tempArr[0]+"."+tempArr[1],false);
						salaryBean.setIndianSalary(tempDouble);
					}
					if(myList.get(i).contains("Living Allowanc")) {
						if("Living Allowanc".equals(StringUtil.trim(myList.get(i)))){
							i=i+1;
							if(myList.get(i).contains("Gross Pay")) {
								i=i+1;
								String [] tempArr=myList.get(i).replaceAll("[^a-zA-Z0-9\\s]", "").split("[ ]");
								if(dentalInsurance && (fringeBenefits || fringeBenefitsSingle))
									salaryBean.setDentalInsurance(-1*StringUtil.getDoubleFromString(tempArr[0]+"."+tempArr[1],false));
								else
									salaryBean.setSocialSecurityTax(-1*StringUtil.getDoubleFromString(tempArr[0]+"."+tempArr[1],false));
							}
							i=(myList.get(i).startsWith("FIND DISCREPANCY") || myList.get(i).startsWith("PLS CONNECT WITH"))?i:i+1;
							String [] tempArr=myList.get(i).replaceAll("[^a-zA-Z0-9\\s]", "").split("[ ]");
							if(tempArr.length>1 && StringUtil.isNumeric(tempArr[0])) {
								//if (dentalInsurance)
									salaryBean.setLivingAllowance(StringUtil.getDoubleFromString((tempArr.length>2)?tempArr[0]+tempArr[1]+"."+tempArr[2]:tempArr[0]+"."+tempArr[1],false));
							}
							if(myList.get(i).startsWith("xxxx") || myList.get(i).contains("/")|| !StringUtil.isNumeric(myList.get(i).replaceAll("[^a-zA-Z0-9\\s]", ""))) {
								i=i+1;
								tempArr=myList.get(i).replaceAll("[^a-zA-Z0-9\\s]", "").split("[ ]");
								if(tempArr.length>1 && StringUtil.isNumeric(tempArr[0]))
									salaryBean.setLivingAllowance(StringUtil.getDoubleFromString((tempArr.length>2)?tempArr[0]+tempArr[1]+"."+tempArr[2]:tempArr[0]+"."+tempArr[1],false));
							}
							i=myList.get(i).contains("Statutory")?i:i+1;
							tempArr=myList.get(i).replaceAll("[^a-zA-Z0-9\\s]", "").split("[ ]");
							if(tempArr.length>1 && StringUtil.isNumeric(tempArr[0]))
								salaryBean.setLivingAllowance(StringUtil.getDoubleFromString((tempArr.length>2)?tempArr[0]+tempArr[1]+"."+tempArr[2]:tempArr[0]+"."+tempArr[1],false));
						}else {
							String temp=myList.get(i).substring(myList.get(i).indexOf("Living Allowanc")+16);
							String [] tempArr=temp.replaceAll("[^a-zA-Z0-9\\s]", "").split("[ ]");
							salaryBean.setLivingAllowance(StringUtil.getDoubleFromString((tempArr.length>2)?tempArr[0]+tempArr[1]+"."+tempArr[2]:tempArr[0]+"."+tempArr[1],false));

						}
					}
					if(myList.get(i).contains("Taxable Marital Status:") 
							|| (myList.get(i).contains("Exemptions/Allowances:") && salaryBean.getFederalIncomeTax()==0)) {
						i=i+1;
						String [] tempArr=myList.get(i).replaceAll("[^a-zA-Z0-9\\s]", "").split("[ ]");
						if(tempArr.length>0 && StringUtil.isNumeric(tempArr[0])) {
							double tempDouble=StringUtil.getDoubleFromString((tempArr.length>2)?tempArr[0]+tempArr[1]+"."+tempArr[2]:tempArr[0]+"."+tempArr[1],false);
							if(tempDouble>1500)
								tempDouble=StringUtil.getDoubleFromString(tempArr[0]+"."+tempArr[1],false);
							salaryBean.setFederalIncomeTax(-1*tempDouble);
							i=i+1;
						}
					}

					if(myList.get(i).contains("Exemptions/Allowances:") 
							|| (myList.get(i).contains("Gross Pay") && salaryBean.getSocialSecurityTax()==0)) {
						i=i+1;
						String [] tempArr=myList.get(i).replaceAll("[^a-zA-Z0-9\\s]", "").split("[ ]");
						salaryBean.setSocialSecurityTax(-1*StringUtil.getDoubleFromString(tempArr[0]+"."+tempArr[1],false));
						i=i+1;
					}
					if(myList.get(i).contains("Federal:") 
							|| (myList.get(i).contains("Statutory") && salaryBean.getMedicareTax()==0)) {
						i=i+1;
						String [] tempArr=myList.get(i).replaceAll("[^a-zA-Z0-9\\s]", "").split("[ ]");
						salaryBean.setMedicareTax(-1*StringUtil.getDoubleFromString(tempArr[0]+"."+tempArr[1],false));
						i=i+1;
					}
					if(myList.get(i).contains("MA: 4") 
							|| myList.get(i).contains("MA: 1") 
							|| (myList.get(i).contains("Federal Income Tax") && salaryBean.getMaStateIncomeTax()==0)) {
						i=i+1;
						String [] tempArr=myList.get(i).replaceAll("[^a-zA-Z0-9\\s]", "").split("[ ]");
						salaryBean.setMaStateIncomeTax(-1*StringUtil.getDoubleFromString(tempArr[0]+"."+tempArr[1],false));
					}
					if((myList.get(i).contains("Gross Pay")  && salaryBean.getDentalInsurance()==0) 
							|| (maPaidFamilyLeaveIns && myList.get(i).contains("Federal Income Tax")) 
							|| (dentalInsurance && myList.get(i).contains("Social Security Tax") && salaryBean.getDentalInsurance()==0)) {
						i=i+1;
						String [] tempArr=myList.get(i).replaceAll("[^a-zA-Z0-9\\s]", "").split("[ ]");
						if(dentalInsurance)
							salaryBean.setDentalInsurance(-1*StringUtil.getDoubleFromString(tempArr[0]+"."+tempArr[1],false));
					}

					if((!fringeBenefits && maPaidFamilyLeaveIns && myList.get(i).contains("Social Security Tax")) 
							|| (fringeBenefits && myList.get(i).contains("Federal Income Tax"))  
							|| (dentalInsurance && !maPaidFamilyLeaveIns && !fringeBenefits && myList.get(i).contains("Statutory")) 
							|| ((!fringeBenefits || salaryBean.getSalaryFile().equals("20200522.pdf") 
							|| salaryBean.getSalaryFile().equals("20191007.pdf")) && myList.get(i).contains("Medicare Tax") && salaryBean.getIsRecovery()==0)) {
						i=i+1;
						String temp=(myList.get(i).contains("*"))?myList.get(i).substring(0, myList.get(i).indexOf("*")):myList.get(i);
						String [] tempArr=temp.replaceAll("[^a-zA-Z0-9\\s]", "").split("[ ]");
						double amount=-1*StringUtil.getDoubleFromString((tempArr.length>2)?tempArr[0]+tempArr[1]+"."+tempArr[2]:tempArr[0]+"."+tempArr[1],false);
						if(amount > -151.00  && amount <-149.00)
							salaryBean.setMedicalInsurance(amount);
					}
					if(maFliEe && ((fringeBenefits && myList.get(i).contains("Social Security Tax")) 
							|| (!fringeBenefits && (myList.get(i).contains("Federal Income Tax")) 
							||  (myList.get(i).contains("MA State Income Tax") && salaryBean.getMaFliMa()==0)))) {
						i=i+1;
						String [] tempArr=myList.get(i).replaceAll("[^a-zA-Z0-9\\s]", "").split("[ ]");
						salaryBean.setMaFliMa(tempArr.length>0 && StringUtil.isNumeric(tempArr[0])?-1*StringUtil.getDoubleFromString(tempArr[0]+"."+tempArr[1],false):0);
					}
					if(maMliEe && ((fringeBenefits && myList.get(i).contains("Medicare Tax")) 
							|| (!fringeBenefits && (myList.get(i).contains("Social Security Tax") 
							|| (myList.get(i).contains("Dental") && salaryBean.getMaMliEe()==0))))) {
						i=i+1;
						String [] tempArr=myList.get(i).replaceAll("[^a-zA-Z0-9\\s]", "").split("[ ]");
						if(tempArr.length>0 && StringUtil.isNumeric(tempArr[0]))
							salaryBean.setMaMliEe(-1*StringUtil.getDoubleFromString(tempArr[0]+"."+tempArr[1],false));
					}
					if((intAdvance && (salaryBean.getSalaryFile().equals("20150801.pdf") || salaryBean.getSalaryFile().equals("20160201.pdf"))&& myList.get(i).contains("Social Security Tax"))
							//|| (intAdvance && myList.get(i).contains("Statutory"))
							|| (intAdvance && myList.get(i).contains("Federal Income Tax"))
							|| (!intAdvance && !dentalInsurance && myList.get(i).contains("Statutory"))
							|| (maPaidFamilyLeaveIns && myList.get(i).contains("Medicare Tax")) 
							|| (fringeBenefits && myList.get(i).contains("MA State Income Tax")) 
							|| (dentalInsurance && !fringeBenefits && !maFliEe && myList.get(i).contains("Federal Income Tax")) 
							|| (!fringeBenefits && myList.get(i).contains("Medicare Tax") && salaryBean.getMedicalInsurance()==0) 
							|| (myList.get(i).contains("Is Recovery") && salaryBean.getMedicalInsurance()==0)
							) {
						i=i+1;
						String [] tempArr=myList.get(i).replaceAll("[^a-zA-Z0-9\\s]", "").split("[ ]");
						salaryBean.setMedicalInsurance(-1*StringUtil.getDoubleFromString(tempArr[0]+"."+tempArr[1],false));
					}

					if(visionInsurance && 
							((maPaidFamilyLeaveIns && myList.get(i).contains("MA State Income Tax")) 
									|| ((salaryBean.getSalaryFile().equals("20200522.pdf") 
											|| salaryBean.getSalaryFile().equals("20191007.pdf"))&& myList.get(i).contains("Dental Hmo"))
									|| (fringeBenefits && myList.get(i).contains("Dental Ppo")) 
									|| (!fringeBenefits && !maMliEe && myList.get(i).contains("Social Security Tax")) 
									|| (!fringeBenefits && myList.get(i).contains("MA State Income Tax") 
											|| (myList.get(i).contains("Ma Fli Ma") && salaryBean.getVisionInsurance()==0)))) {
						i=i+1;
						String [] tempArr=myList.get(i).replaceAll("[^a-zA-Z0-9\\s]", "").split("[ ]");
						salaryBean.setVisionInsurance(-1*StringUtil.getDoubleFromString(tempArr[0]+"."+tempArr[1],false));
					}
					if(plan401K && ((salaryBean.getSalaryFile().equals("20180122.pdf") && myList.get(i).contains("Social Security Tax"))
							||(!dentalInsurance && myList.get(i).contains("Social Security Tax"))
							|| (!dentalInsurance && myList.get(i).contains("Federal Income Tax") && salaryBean.getPlan401K()==0)
							|| (maPaidFamilyLeaveIns && myList.get(i).contains("Dental Hmo")) 
							|| (dentalInsurance && fringeBenefits && myList.get(i).contains("Fringe Benefits")) 
							|| (dentalInsurance && !fringeBenefits && !maMliEe && myList.get(i).contains("Medicare Tax")) 
							|| !fringeBenefits && myList.get(i).contains("Dental Ppo") 
							|| (!fringeBenefits && myList.get(i).contains("Ma Mli Ee") && salaryBean.getPlan401K()==0)
							)) {
						i=i+1;
						String [] tempArr=myList.get(i).replaceAll("[^a-zA-Z0-9\\s]", "").split("[ ]");
						salaryBean.setPlan401K(tempArr.length>0 && StringUtil.isNumeric(tempArr[0])?-1*StringUtil.getDoubleFromString(tempArr[0]+"."+tempArr[1],false):0);
					}
					if(myList.get(i).contains("500 STAPLES DR") || (myList.get(i).contains("Med Ppo 2000") && salaryBean.getNetPay()==0)  || (myList.get(i).contains("Is Net Pay") && salaryBean.getNetPay()==0) || (myList.get(i).contains("EAST MAIN ST")  && salaryBean.getNetPay()==0)) {
						i=i+1;
						String [] tempArr=myList.get(i).replaceAll("[^a-zA-Z0-9\\s.]", "").split("[ ]");
						if(tempArr.length>0 && StringUtil.isNumeric(tempArr[0]) && StringUtil.getDouble(tempArr[0])>0)
							salaryBean.setNetPay(StringUtil.getDoubleFromString((tempArr.length>2)?tempArr[0]+tempArr[1]+"."+tempArr[2]:(tempArr.length>1)?tempArr[0]+"."+tempArr[1]:tempArr[0],false));
					}
				}
			}catch(Exception e){
				System.out.println(salaryBean.getSalaryFile()+"  Error out Data "+data);
				e.printStackTrace();
				break;
			}
			salaryBean.setGrossPay(salaryBean.getIndianSalary()+salaryBean.getLivingAllowance());
			salaryBean.setIsRecovery(-1*salaryBean.getIndianSalary());
			result.add(salaryBean);
		}
		return result;
	}
	public static void createCitizensSalaryReport(String sourceDir,String destExcel) {
		List<SalaryBean> result=extractCitizensSalaryData(readPDF(sourceDir));
		if(result.size()>0) {
			Collections.sort(result, new Comparator<SalaryBean>() {
				public int compare(SalaryBean u1, SalaryBean u2) {
					return u2.getSalaryDate().compareTo(u1.getSalaryDate());
				}
			});


			List<List<Object>> tableData=new ArrayList<List<Object>>();
			List<Object> tableHeader=new ArrayList<Object>();
			tableHeader.add("Salary Date");
			tableHeader.add("Worked Hours");
			tableHeader.add("Pay Rate");
			tableHeader.add("Federal Income Tax");
			tableHeader.add("Social Security Tax");
			tableHeader.add("Medicare Tax");
			tableHeader.add("MA State Income Tax");
			tableHeader.add("Ma Fli Ma");
			tableHeader.add("Ma Mli Ee");
			tableHeader.add("Medical Insurance");
			tableHeader.add("Dental Insurance");
			tableHeader.add("Vision Insurance");
			tableHeader.add("401k Plan");
			tableHeader.add("Employee StockPurchase Plan");
			tableHeader.add("Gross Pay");
			tableHeader.add("PTO Days");
			tableHeader.add("Total Tax");
			tableHeader.add("Tax Break Up");
			tableHeader.add("Net Pay");

			tableData.add(tableHeader);
			for(SalaryBean bean:result) {
				List<Object> tableRow=new ArrayList<Object>();
				tableRow.add(bean.getSalaryDate());
				tableRow.add(bean.getWorkHours());
				tableRow.add(bean.getPayRate());
				tableRow.add(bean.getFederalIncomeTax());
				tableRow.add(bean.getSocialSecurityTax());
				tableRow.add(bean.getMedicareTax());
				tableRow.add(bean.getMaStateIncomeTax());
				tableRow.add(bean.getMaFliMa());
				tableRow.add(bean.getMaMliEe());
				tableRow.add(bean.getMedicalInsurance());
				tableRow.add(bean.getDentalInsurance());
				tableRow.add(bean.getVisionInsurance());
				tableRow.add(bean.getPlan401K());
				tableRow.add(bean.getEmployeeStockPurchase());
				tableRow.add(bean.getBaseSalary());
				tableRow.add(bean.getPaidOffTime()/8);
				tableRow.add(StringUtil.getTwoDecimal(-1*(bean.getFederalIncomeTax()
						+bean.getSocialSecurityTax()
						+bean.getMedicareTax()
						+bean.getMaStateIncomeTax()
						+bean.getMaFliMa()
						+bean.getMaMliEe()))+" ("+StringUtil.getTwoDecimal(-1*((bean.getFederalIncomeTax()*100)/bean.getBaseSalary()
								+(bean.getSocialSecurityTax()*100)/bean.getBaseSalary()
								+(bean.getMedicareTax()*100)/bean.getBaseSalary()
								+(bean.getMaStateIncomeTax()*100)/bean.getBaseSalary()
								+(bean.getMaFliMa()*100)/bean.getBaseSalary()
								+(bean.getMaMliEe()*100)/bean.getBaseSalary()))+"%)");
				tableRow.add("FD: "+StringUtil.getTwoDecimal(-1*(bean.getFederalIncomeTax()*100)/bean.getBaseSalary())+"%"
						+" SS: "+StringUtil.getTwoDecimal(-1*(bean.getSocialSecurityTax()*100)/bean.getBaseSalary())+"%"
						+" M: "+StringUtil.getTwoDecimal(-1*(bean.getMedicareTax()*100)/bean.getBaseSalary())+"%"
						+" ST: "+StringUtil.getTwoDecimal(-1*(bean.getMaStateIncomeTax()*100)/bean.getBaseSalary())+"%"
						+" MaFli: "+StringUtil.getTwoDecimal(-1*(bean.getMaFliMa()*100)/bean.getBaseSalary())+"%"
						+" MaMli: "+StringUtil.getTwoDecimal(-1*(bean.getMaMliEe()*100)/bean.getBaseSalary())+"%");
				tableRow.add(bean.getNetPay());
				tableData.add(tableRow);
				System.out.println(bean.toString());
			}
			TableData data = new TableData();
			data.setReportData(tableData);
			data.setRowPosition(0);
			data.setColumnPosition(0);
			ReportExcel.writeExcel(data, destExcel);
		}
	}
	
	public static List<SalaryBean>  extractCitizensSalaryData(List<String> dataList) {
		List<SalaryBean> result=new ArrayList<SalaryBean>();
		for(String data:dataList) {
			//System.out.println(data);
			SalaryBean salaryBean =new SalaryBean();
			List<String> myList = new ArrayList<String>(Arrays.asList(data.split("\n")));
			try {
				for(int i=0;i<myList.size();i++) {
					if(i==0) {
						salaryBean.setSalaryFile(myList.get(i));
						i=i+1;
					}
					if(myList.get(i).startsWith("Pay Date")) {
						salaryBean.setSalaryDate(DateUtil.getSomeDate(StringUtil.trim(myList.get(i).substring(myList.get(i).indexOf("Pay Date")+9)), "MM/dd/yyyy"));
					}
					if(myList.get(i).startsWith("Net Pay $")) {
						salaryBean.setNetPay(StringUtil.getDoubleFromString(StringUtil.trim(myList.get(i).substring(myList.get(i).indexOf("Net Pay $")+9)),true));
					}
					if(myList.get(i).startsWith("Pay Rate $")) {
						salaryBean.setPayRate(StringUtil.getDoubleFromString(StringUtil.trim(myList.get(i).substring(myList.get(i).indexOf("Pay Rate $")+10)),false));
					}
					if(myList.get(i).startsWith("Regular")) {
						salaryBean.setWorkHours(StringUtil.getDoubleFromString(StringUtil.trim(myList.get(i).substring(myList.get(i).indexOf("Regular")+8,myList.get(i).indexOf("$"))),false));
					}
					if(myList.get(i).startsWith("401(k) Yes $")) {
						salaryBean.setPlan401K(-1*StringUtil.getDoubleFromString(StringUtil.trim(myList.get(i).substring(myList.get(i).indexOf("401(k) Yes $")+12,myList.get(i).indexOf("$",myList.get(i).indexOf("401(k) Yes $")+12))),true));
					}
					if(myList.get(i).startsWith("Emp St Pur Plan No $")) {
						salaryBean.setEmployeeStockPurchase(-1*StringUtil.getDoubleFromString(StringUtil.trim(myList.get(i).substring(myList.get(i).indexOf("Emp St Pur Plan No $")+20,myList.get(i).indexOf("$",myList.get(i).indexOf("Emp St Pur Plan No $")+20))),true));
					}
					if(myList.get(i).startsWith("Dental Plan Yes $")) {
						salaryBean.setDentalInsurance(-1*StringUtil.getDoubleFromString(StringUtil.trim(myList.get(i).substring(myList.get(i).indexOf("Dental Plan Yes $")+17,myList.get(i).indexOf("$",myList.get(i).indexOf("Dental Plan Yes $")+17))),true));
					}
					if(myList.get(i).startsWith("Medical Plan Yes $")) {
						salaryBean.setMedicalInsurance(-1*StringUtil.getDoubleFromString(StringUtil.trim(myList.get(i).substring(myList.get(i).indexOf("Medical Plan Yes $")+18,myList.get(i).indexOf("$",myList.get(i).indexOf("Medical Plan Yes $")+18))),true));
					}
					if(myList.get(i).startsWith("Vision Plan Yes $")) {
						salaryBean.setVisionInsurance(-1*StringUtil.getDoubleFromString(StringUtil.trim(myList.get(i).substring(myList.get(i).indexOf("Vision Plan Yes $")+17,myList.get(i).indexOf("$",myList.get(i).indexOf("Vision Plan Yes $")+17))),true));
					}
					if(myList.get(i).startsWith("Federal Income Tax $")) {
						salaryBean.setFederalIncomeTax(-1*StringUtil.getDoubleFromString(StringUtil.trim(myList.get(i).substring(myList.get(i).indexOf("Federal Income Tax $")+20,myList.get(i).indexOf("$",myList.get(i).indexOf("Federal Income Tax $")+20))),true));
					}
					if(myList.get(i).startsWith("Employee Medicare $")) {
						salaryBean.setMedicareTax(-1*StringUtil.getDoubleFromString(StringUtil.trim(myList.get(i).substring(myList.get(i).indexOf("Employee Medicare $")+19,myList.get(i).indexOf("$",myList.get(i).indexOf("Employee Medicare $")+19))),true));
					}
					if(myList.get(i).startsWith("Social Security Employee Tax $")) {
						salaryBean.setSocialSecurityTax(-1*StringUtil.getDoubleFromString(StringUtil.trim(myList.get(i).substring(myList.get(i).indexOf("Social Security Employee Tax $")+30,myList.get(i).indexOf("$",myList.get(i).indexOf("Social Security Employee Tax $")+30))),true));
					}
					if(myList.get(i).startsWith("MA State Income Tax $")) {
						salaryBean.setMaStateIncomeTax(-1*StringUtil.getDoubleFromString(StringUtil.trim(myList.get(i).substring(myList.get(i).indexOf("MA State Income Tax $")+21,myList.get(i).indexOf("$",myList.get(i).indexOf("MA State Income Tax $")+21))),true));
					}
					if(myList.get(i).startsWith("MA Priv Pd Fam Leave- EE $")) {
						salaryBean.setMaFliMa(-1*StringUtil.getDoubleFromString(StringUtil.trim(myList.get(i).substring(myList.get(i).indexOf("MA Priv Pd Fam Leave- EE $")+26,myList.get(i).indexOf("$",myList.get(i).indexOf("MA Priv Pd Fam Leave- EE $")+26))),true));
					}
					if(myList.get(i).startsWith("MA Priv Pd Med Leave- EE $")) {
						salaryBean.setMaMliEe(-1*StringUtil.getDoubleFromString(StringUtil.trim(myList.get(i).substring(myList.get(i).indexOf("MA Priv Pd Med Leave- EE $")+26,myList.get(i).indexOf("$",myList.get(i).indexOf("MA Priv Pd Med Leave- EE $")+26))),true));
					}
					if(myList.get(i).startsWith("Paid Time Off") && !"Paid Time Off".equals(StringUtil.trim(myList.get(i)))) {
						salaryBean.setPaidOffTime(StringUtil.getDoubleFromString(StringUtil.trim(myList.get(i).substring(myList.get(i).indexOf(" ",myList.get(i).indexOf("Paid Time Off")+14))),false));
					}
					if(myList.get(i).startsWith("Current $")) {
						salaryBean.setBaseSalary(StringUtil.getDoubleFromString(StringUtil.trim(myList.get(i).substring(myList.get(i).indexOf("Current $")+9,myList.get(i).indexOf("$",myList.get(i).indexOf("Current $")+9))),true));
					}
				}
			}catch(Exception e){
				System.out.println(salaryBean.getSalaryFile()+"  Error out Data "+data);
				e.printStackTrace();
				break;
			}
			result.add(salaryBean);
		}
		return result;
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

package com.build.bean;

import java.util.Date;

public class SalaryBean {

	public String salaryFile;
	public Date salaryDate;
	public double basic;
	public double bobKittyAllowance;
	public double houseRentAllowance;
	public double sundryMedicalAllowance;
	public double leaveTravelAllowance;
	public double personalAllowance;
	public double variableAllowance;
	public double conveyanceNonTaxable;
	public double conveyanceTaxable;
	public double performancePay;
	public double overtime;
	public double miscellaneous;
	public double cityAllowance;

	public double providentFund;
	public double professionalTax;
	public double incomeTax;
	public double healthInsurance;
	public double tcsWelfareTrust;
	public double voluntaryProvidentFund;

	public double totalAllowance;
	public double totalEarning;
	public double totalDeduction;
	public double totalProvidentFund;
	public double totalGratuity;
	public double netPay;
	public double usNetPay1;
	public double usNetPay2;

	public double workHours;
	public double indianSalary;
	public double livingAllowance;
	public double grossPay;
	public double federalIncomeTax;
	public double socialSecurityTax;
	public double medicareTax;
	public double maStateIncomeTax;
	public double isRecovery;
	public double dentalInsurance;
	public double maFliMa;
	public double maMliEe;
	public double medicalInsurance;
	public double visionInsurance;
	public double plan401K;
	
	public double payRate;
	public double baseSalary;
	public double paidOffTime;

	public double getPayRate() {
		return payRate;
	}
	public void setPayRate(double payRate) {
		this.payRate = payRate;
	}
	public double getBaseSalary() {
		return baseSalary;
	}
	public void setBaseSalary(double baseSalary) {
		this.baseSalary = baseSalary;
	}
	public double getPaidOffTime() {
		return paidOffTime;
	}
	public void setPaidOffTime(double paidOffTime) {
		this.paidOffTime = paidOffTime;
	}
	public double getIsRecovery() {
		return isRecovery;
	}
	public void setIsRecovery(double isRecovery) {
		if((this.getIsRecovery()==0 || this.getIsRecovery()>isRecovery) && isRecovery>-1100)
			this.isRecovery = isRecovery;
	}
	public double getWorkHours() {
		return workHours;
	}
	public void setWorkHours(double workHours) {
		this.workHours = workHours;
	}
	public double getIndianSalary() {
		return indianSalary;
	}
	public void setIndianSalary(double indianSalary) {
		if(this.getIndianSalary()==0)
			this.indianSalary = indianSalary;
	}
	public double getLivingAllowance() {
		return livingAllowance;
	}
	public void setLivingAllowance(double livingAllowance) {
		if(this.getLivingAllowance()==0 || this.getLivingAllowance()<livingAllowance)
			this.livingAllowance = livingAllowance;
	}
	public double getGrossPay() {
		return grossPay;
	}
	public void setGrossPay(double grossPay) {
		if(this.getGrossPay()==0)
			this.grossPay = grossPay;
	}
	public double getFederalIncomeTax() {
		return federalIncomeTax;
	}
	public void setFederalIncomeTax(double federalIncomeTax) {
		if(this.getFederalIncomeTax()==0)
			this.federalIncomeTax = federalIncomeTax;
	}
	public double getSocialSecurityTax() {
		return socialSecurityTax;
	}
	public void setSocialSecurityTax(double socialSecurityTax) {
		if(this.getSocialSecurityTax()==0)
			this.socialSecurityTax = socialSecurityTax;
	}
	public double getMedicareTax() {
		return medicareTax;
	}
	public void setMedicareTax(double medicareTax) {
		if(this.getMedicareTax()==0)
			this.medicareTax = medicareTax;
	}
	public double getMaStateIncomeTax() {
		return maStateIncomeTax;
	}
	public void setMaStateIncomeTax(double maStateIncomeTax) {
		if(this.getMaStateIncomeTax()==0)
			this.maStateIncomeTax = maStateIncomeTax;
	}
	public double getDentalInsurance() {
		return dentalInsurance;
	}
	public void setDentalInsurance(double dentalInsurance) {
		if(this.getDentalInsurance()==0 || this.getDentalInsurance()>dentalInsurance)
			this.dentalInsurance = dentalInsurance;
	}
	public double getMaFliMa() {
		return maFliMa;
	}
	public void setMaFliMa(double maFliMa) {
		if(this.getMaFliMa()==0)
			this.maFliMa = maFliMa;
	}
	public double getMaMliEe() {
		return maMliEe;
	}
	public void setMaMliEe(double maMliEe) {
		if(this.getMaMliEe()==0)
			this.maMliEe = maMliEe;
	}
	public double getMedicalInsurance() {
		return medicalInsurance;
	}
	public void setMedicalInsurance(double medicalInsurance) {
		if(this.getMedicalInsurance()==0)
			this.medicalInsurance = medicalInsurance;
	}
	public double getVisionInsurance() {
		return visionInsurance;
	}
	public void setVisionInsurance(double visionInsurance) {
		if(this.getVisionInsurance()==0)
			this.visionInsurance = visionInsurance;
	}
	public double getPlan401K() {
		return plan401K;
	}
	public void setPlan401K(double plan401k) {
		if((this.getPlan401K()==0  || this.getPlan401K()>plan401k) && plan401k>-300)
			plan401K = plan401k;
	}
	public String getSalaryFile() {
		return salaryFile;
	}
	public void setSalaryFile(String salaryFile) {
		this.salaryFile = salaryFile;
	}
	public Date getSalaryDate() {
		return salaryDate;
	}
	public void setSalaryDate(Date salaryDate) {
		this.salaryDate = salaryDate;
	}
	public double getBasic() {
		return basic;
	}
	public void setBasic(double basic) {
		this.basic = basic;
	}
	public double getBobKittyAllowance() {
		return bobKittyAllowance;
	}
	public void setBobKittyAllowance(double bobKittyAllowance) {
		this.bobKittyAllowance = bobKittyAllowance;
	}
	public double getHouseRentAllowance() {
		return houseRentAllowance;
	}
	public void setHouseRentAllowance(double houseRentAllowance) {
		this.houseRentAllowance = houseRentAllowance;
	}
	public double getSundryMedicalAllowance() {
		return sundryMedicalAllowance;
	}
	public void setSundryMedicalAllowance(double sundryMedicalAllowance) {
		this.sundryMedicalAllowance = sundryMedicalAllowance;
	}
	public double getLeaveTravelAllowance() {
		return leaveTravelAllowance;
	}
	public void setLeaveTravelAllowance(double leaveTravelAllowance) {
		this.leaveTravelAllowance = leaveTravelAllowance;
	}
	public double getPersonalAllowance() {
		return personalAllowance;
	}
	public void setPersonalAllowance(double personalAllowance) {
		this.personalAllowance = personalAllowance;
	}
	public double getVariableAllowance() {
		return variableAllowance;
	}
	public void setVariableAllowance(double variableAllowance) {
		this.variableAllowance = variableAllowance;
	}
	public double getConveyanceNonTaxable() {
		return conveyanceNonTaxable;
	}
	public void setConveyanceNonTaxable(double conveyanceNonTaxable) {
		this.conveyanceNonTaxable = conveyanceNonTaxable;
	}
	public double getConveyanceTaxable() {
		return conveyanceTaxable;
	}
	public void setConveyanceTaxable(double conveyanceTaxable) {
		this.conveyanceTaxable = conveyanceTaxable;
	}
	public double getPerformancePay() {
		return performancePay;
	}
	public void setPerformancePay(double performancePay) {
		this.performancePay = performancePay;
	}
	public double getOvertime() {
		return overtime;
	}
	public void setOvertime(double overtime) {
		this.overtime = overtime;
	}
	public double getMiscellaneous() {
		return miscellaneous;
	}
	public void setMiscellaneous(double miscellaneous) {
		this.miscellaneous = miscellaneous;
	}
	public double getCityAllowance() {
		return cityAllowance;
	}
	public void setCityAllowance(double cityAllowance) {
		this.cityAllowance = cityAllowance;
	}
	public double getProvidentFund() {
		return providentFund;
	}
	public void setProvidentFund(double providentFund) {
		this.providentFund = providentFund;
	}
	public double getProfessionalTax() {
		return professionalTax;
	}
	public void setProfessionalTax(double professionalTax) {
		this.professionalTax = professionalTax;
	}
	public double getIncomeTax() {
		return incomeTax;
	}
	public void setIncomeTax(double incomeTax) {
		this.incomeTax = incomeTax;
	}
	public double getHealthInsurance() {
		return healthInsurance;
	}
	public void setHealthInsurance(double healthInsurance) {
		this.healthInsurance = healthInsurance;
	}
	public double getTcsWelfareTrust() {
		return tcsWelfareTrust;
	}
	public void setTcsWelfareTrust(double tcsWelfareTrust) {
		this.tcsWelfareTrust = tcsWelfareTrust;
	}
	public double getVoluntaryProvidentFund() {
		return voluntaryProvidentFund;
	}
	public void setVoluntaryProvidentFund(double voluntaryProvidentFund) {
		this.voluntaryProvidentFund = voluntaryProvidentFund;
	}
	public double getTotalAllowance() {
		return totalAllowance;
	}
	public void setTotalAllowance(double totalAllowance) {
		this.totalAllowance = totalAllowance;
	}
	public double getTotalEarning() {
		return totalEarning;
	}
	public void setTotalEarning(double totalEarning) {
		this.totalEarning = totalEarning;
	}
	public double getTotalDeduction() {
		return totalDeduction;
	}
	public void setTotalDeduction(double totalDeduction) {
		this.totalDeduction = totalDeduction;
	}
	public double getTotalProvidentFund() {
		return totalProvidentFund;
	}
	public void setTotalProvidentFund(double totalProvidentFund) {
		this.totalProvidentFund = totalProvidentFund;
	}
	public double getTotalGratuity() {
		return totalGratuity;
	}
	public void setTotalGratuity(double totalGratuity) {
		this.totalGratuity = totalGratuity;
	}
	public double getNetPay() {
		return netPay;
	}
	public void setNetPay(double netPay) {
		this.netPay = netPay;
	}
	public double getUsNetPay1() {
		return usNetPay1;
	}
	public void setUsNetPay1(double usNetPay1) {
		this.usNetPay1 = usNetPay1;
	}
	public double getUsNetPay2() {
		return usNetPay2;
	}
	public void setUsNetPay2(double usNetPay2) {
		this.usNetPay2 = usNetPay2;
	}
	@Override
	public String toString() {
		return "SalaryBean [salaryFile=" + salaryFile 
				+ ", salaryDate=" + salaryDate 
				+ ", workHours=" + workHours 
				+ ", payRate="+ payRate 
				+ ", baseSalary=" + baseSalary 
				+ ", federalIncomeTax=" + federalIncomeTax 
				+ ", socialSecurityTax=" + socialSecurityTax
				+ ", medicareTax=" + medicareTax 
				+ ", maStateIncomeTax=" + maStateIncomeTax 
				+ ", dentalInsurance="+ dentalInsurance 
				+ ", maFliMa=" + maFliMa 
				+ ", maMliEe=" + maMliEe 
				+ ", medicalInsurance="+ medicalInsurance 
				+ ", visionInsurance=" + visionInsurance 
				+ ", plan401K=" + plan401K 
				+ ", paidOffTime=" + paidOffTime 
				+ ", grossPay=" + grossPay
				+ ", netPay=" + netPay+ "]";
				
	}

}

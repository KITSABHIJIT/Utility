package com.build.beanToPdf;

import java.util.Date;

public class SalaryBean {
	
	public String salaryFile;
	public Date salaryDate;
	public double basic;
	public double allowance;
	public double performancePay;
	public double providentFund;
	public double professionalTax;
	public double incomeTax;
	public double healthInsurance;
	public double voluntaryProvidentFund;
	
	public double totalEarning;
	public double totalDeduction;
	public double totalProvidentFund;
	public double totalGratuity;
	public double netPay;
	
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
	public double getAllowance() {
		return allowance;
	}
	public void setAllowance(double allowance) {
		this.allowance = allowance;
	}
	public double getPerformancePay() {
		return performancePay;
	}
	public void setPerformancePay(double performancePay) {
		this.performancePay = performancePay;
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
	public double getVoluntaryProvidentFund() {
		return voluntaryProvidentFund;
	}
	public void setVoluntaryProvidentFund(double voluntaryProvidentFund) {
		this.voluntaryProvidentFund = voluntaryProvidentFund;
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
	@Override
	public String toString() {
		return "SalaryBean [salaryFile=" + salaryFile + ", salaryDate=" + salaryDate + ", basic=" + basic
				+ ", allowance=" + allowance + ", performancePay=" + performancePay + ", providentFund=" + providentFund
				+ ", professionalTax=" + professionalTax + ", incomeTax=" + incomeTax + ", healthInsurance="
				+ healthInsurance + ", voluntaryProvidentFund=" + voluntaryProvidentFund + ", totalEarning="
				+ totalEarning + ", totalDeduction=" + totalDeduction + ", totalProvidentFund=" + totalProvidentFund
				+ ", totalGratuity=" + totalGratuity + ", netPay=" + netPay + "]";
	}
	
	
	
}

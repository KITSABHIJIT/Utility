package com.test.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="paymode_month_expense")
public class PaymodeMonthExpense implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name="EXPENSE_MONTH")
	@Id String expenseMonth;
	
	@Column(name = "BANK_OF_AMERICA_DEBIT_CARD")
	private double bankOfAmericaDebitCard;
	
	@Column(name = "BANK_OF_AMERICA_CREDIT_CARD")
	private double bankOfAmericaCreditCard;
	
	@Column(name = "DISCOVER_CARD")
	private double discoverCard;
	
	@Column(name = "AMAZON_CHASE_CARD")
	private double amazonChaseCard;
	
	@Column(name = "AMAZON_STORE_CARD")
	private double amazonStoreCard;
	
	@Column(name = "MACYS_CREDIT_CARD")
	private double macysCreditCard;
	
	@Column(name = "ZALES_CREDIT_CARD")
	private double zalesCreditCard;
	
	@Column(name = "BJS_CREDIT_CARD")
	private double bjsCreditCard;
	
	@Column(name = "KOHLS_CREDIT_CARD")
	private double kohlsCreditCard;
	
	@Column(name = "JC_PENNEY_CARD")
	private double jcPenneyCard;
	
	@Column(name = "SEARS_CREDIT_CARD")
	private double searsCreditCard;
	
	@Column(name = "TJ_MAX_CREDIT_CARD")
	private double tjMaxCreditCard;
	
	@Column(name = "BESTBUY_CARD")
	private double bestBuyCard;
	
	@Column(name = "WELLS_FARGO_CREDIT_CARD")
	private double wellsFargoCreditCard;
	
	@Column(name = "AMEX_CARD")
	private double amexCard;
	
	@Column(name = "DOLLAR")
	private double dollar;

	public String getExpenseMonth() {
		return expenseMonth;
	}

	public void setExpenseMonth(String expenseMonth) {
		this.expenseMonth = expenseMonth;
	}

	public double getBankOfAmericaDebitCard() {
		return bankOfAmericaDebitCard;
	}

	public void setBankOfAmericaDebitCard(double bankOfAmericaDebitCard) {
		this.bankOfAmericaDebitCard = bankOfAmericaDebitCard;
	}

	public double getBankOfAmericaCreditCard() {
		return bankOfAmericaCreditCard;
	}

	public void setBankOfAmericaCreditCard(double bankOfAmericaCreditCard) {
		this.bankOfAmericaCreditCard = bankOfAmericaCreditCard;
	}

	public double getDiscoverCard() {
		return discoverCard;
	}

	public void setDiscoverCard(double discoverCard) {
		this.discoverCard = discoverCard;
	}

	public double getAmazonChaseCard() {
		return amazonChaseCard;
	}

	public void setAmazonChaseCard(double amazonChaseCard) {
		this.amazonChaseCard = amazonChaseCard;
	}

	public double getAmazonStoreCard() {
		return amazonStoreCard;
	}

	public void setAmazonStoreCard(double amazonStoreCard) {
		this.amazonStoreCard = amazonStoreCard;
	}

	public double getMacysCreditCard() {
		return macysCreditCard;
	}

	public void setMacysCreditCard(double macysCreditCard) {
		this.macysCreditCard = macysCreditCard;
	}

	public double getZalesCreditCard() {
		return zalesCreditCard;
	}

	public void setZalesCreditCard(double zalesCreditCard) {
		this.zalesCreditCard = zalesCreditCard;
	}

	public double getBjsCreditCard() {
		return bjsCreditCard;
	}

	public void setBjsCreditCard(double bjsCreditCard) {
		this.bjsCreditCard = bjsCreditCard;
	}

	public double getKohlsCreditCard() {
		return kohlsCreditCard;
	}

	public void setKohlsCreditCard(double kohlsCreditCard) {
		this.kohlsCreditCard = kohlsCreditCard;
	}

	public double getJcPenneyCard() {
		return jcPenneyCard;
	}

	public void setJcPenneyCard(double jcPenneyCard) {
		this.jcPenneyCard = jcPenneyCard;
	}

	public double getSearsCreditCard() {
		return searsCreditCard;
	}

	public void setSearsCreditCard(double searsCreditCard) {
		this.searsCreditCard = searsCreditCard;
	}

	public double getTjMaxCreditCard() {
		return tjMaxCreditCard;
	}

	public void setTjMaxCreditCard(double tjMaxCreditCard) {
		this.tjMaxCreditCard = tjMaxCreditCard;
	}

	public double getBestBuyCard() {
		return bestBuyCard;
	}

	public void setBestBuyCard(double bestBuyCard) {
		this.bestBuyCard = bestBuyCard;
	}

	public double getWellsFargoCreditCard() {
		return wellsFargoCreditCard;
	}

	public void setWellsFargoCreditCard(double wellsFargoCreditCard) {
		this.wellsFargoCreditCard = wellsFargoCreditCard;
	}

	public double getAmexCard() {
		return amexCard;
	}

	public void setAmexCard(double amexCard) {
		this.amexCard = amexCard;
	}

	public double getDollar() {
		return dollar;
	}

	public void setDollar(double dollar) {
		this.dollar = dollar;
	}
	

}

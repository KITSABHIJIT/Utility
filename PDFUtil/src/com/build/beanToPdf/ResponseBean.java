package com.build.beanToPdf;

import java.util.List;

public class ResponseBean {
	
	////EDI810_HDR
	String INVOICE_NUMBER,
    INVOICE_DATE,
    AUTOMATCH_PRO_GRP,
    VENDOR_NUMBER,
    VENDOR_NAME,
    ALT_VENDOR_NUMBER,
    BILL_OF_LADING_NUMBER,
    ALT_VENDOR_NAME,
    SHIP_TO_WAREHOUSE_STORE,
    SHIP_DATE ,
    PURCHASE_ORDER_NUMBER,
    PO_ENTRY_DATE,
    EDI_DATE,
    EDI_TIME,
    STANDARD_CARRIER_ALPHA_CODE,
    TOTAL_UNITS,
    APPLICATION_ID ,
    TERMS_CODE,
    NET_INVOICE_DUE_DATE,
    DISCOUNT_PERCENTAGE,
    DISCOUNT_DATE,
    LOCATION_QUALIFIER,
    DISCOUNTED_AMOUNT_DUE,
    AMOUNT_SUBJECT_TO_DISCOUNT,
    TERMS_DISCOUNT_AMOUNT,
    MERCHANDISE_AMT,
    TOTAL_ALLOWANCES ,
    TOTAL_CHARGES,
    TOTAL_TAX,
    TOTAL_INVOICE_AMOUNT,
    TRANSACTION_GUID,
    TOTAL_FREIGHT;
	
	List<DetailBean> detailList;
	List<ErrorBean> errorList;
	List<PackageTrackingBean> packageTrackingBean;
	
	
	public String getBILL_OF_LADING_NUMBER() {
		return BILL_OF_LADING_NUMBER;
	}
	public void setBILL_OF_LADING_NUMBER(String bILL_OF_LADING_NUMBER) {
		BILL_OF_LADING_NUMBER = bILL_OF_LADING_NUMBER;
	}
	public String getINVOICE_NUMBER() {
		return INVOICE_NUMBER;
	}
	public void setINVOICE_NUMBER(String iNVOICE_NUMBER) {
		INVOICE_NUMBER = iNVOICE_NUMBER;
	}
	public String getINVOICE_DATE() {
		return INVOICE_DATE;
	}
	public void setINVOICE_DATE(String iNVOICE_DATE) {
		INVOICE_DATE = iNVOICE_DATE;
	}
	public String getAUTOMATCH_PRO_GRP() {
		return AUTOMATCH_PRO_GRP;
	}
	public void setAUTOMATCH_PRO_GRP(String aUTOMATCH_PRO_GRP) {
		AUTOMATCH_PRO_GRP = aUTOMATCH_PRO_GRP;
	}
	public String getVENDOR_NUMBER() {
		return VENDOR_NUMBER;
	}
	public void setVENDOR_NUMBER(String vENDOR_NUMBER) {
		VENDOR_NUMBER = vENDOR_NUMBER;
	}
	public String getVENDOR_NAME() {
		return VENDOR_NAME;
	}
	public void setVENDOR_NAME(String vENDOR_NAME) {
		VENDOR_NAME = vENDOR_NAME;
	}
	public String getALT_VENDOR_NUMBER() {
		return ALT_VENDOR_NUMBER;
	}
	public void setALT_VENDOR_NUMBER(String aLT_VENDOR_NUMBER) {
		ALT_VENDOR_NUMBER = aLT_VENDOR_NUMBER;
	}
	public String getALT_VENDOR_NAME() {
		return ALT_VENDOR_NAME;
	}
	public void setALT_VENDOR_NAME(String aLT_VENDOR_NAME) {
		ALT_VENDOR_NAME = aLT_VENDOR_NAME;
	}
	public String getSHIP_TO_WAREHOUSE_STORE() {
		return SHIP_TO_WAREHOUSE_STORE;
	}
	public void setSHIP_TO_WAREHOUSE_STORE(String sHIP_TO_WAREHOUSE_STORE) {
		SHIP_TO_WAREHOUSE_STORE = sHIP_TO_WAREHOUSE_STORE;
	}
	public String getSHIP_DATE() {
		return SHIP_DATE;
	}
	public void setSHIP_DATE(String sHIP_DATE) {
		SHIP_DATE = sHIP_DATE;
	}
	public String getPURCHASE_ORDER_NUMBER() {
		return PURCHASE_ORDER_NUMBER;
	}
	public void setPURCHASE_ORDER_NUMBER(String pURCHASE_ORDER_NUMBER) {
		PURCHASE_ORDER_NUMBER = pURCHASE_ORDER_NUMBER;
	}
	public String getPO_ENTRY_DATE() {
		return PO_ENTRY_DATE;
	}
	public void setPO_ENTRY_DATE(String pO_ENTRY_DATE) {
		PO_ENTRY_DATE = pO_ENTRY_DATE;
	}
	public String getEDI_DATE() {
		return EDI_DATE;
	}
	public void setEDI_DATE(String eDI_DATE) {
		EDI_DATE = eDI_DATE;
	}
	public String getEDI_TIME() {
		return EDI_TIME;
	}
	public void setEDI_TIME(String eDI_TIME) {
		EDI_TIME = eDI_TIME;
	}
	public String getSTANDARD_CARRIER_ALPHA_CODE() {
		return STANDARD_CARRIER_ALPHA_CODE;
	}
	public void setSTANDARD_CARRIER_ALPHA_CODE(String sTANDARD_CARRIER_ALPHA_CODE) {
		STANDARD_CARRIER_ALPHA_CODE = sTANDARD_CARRIER_ALPHA_CODE;
	}
	public String getTOTAL_UNITS() {
		return TOTAL_UNITS;
	}
	public void setTOTAL_UNITS(String tOTAL_UNITS) {
		TOTAL_UNITS = tOTAL_UNITS;
	}
	public String getAPPLICATION_ID() {
		return APPLICATION_ID;
	}
	public void setAPPLICATION_ID(String aPPLICATION_ID) {
		APPLICATION_ID = aPPLICATION_ID;
	}
	public String getTERMS_CODE() {
		return TERMS_CODE;
	}
	public void setTERMS_CODE(String tERMS_CODE) {
		TERMS_CODE = tERMS_CODE;
	}
	public String getNET_INVOICE_DUE_DATE() {
		return NET_INVOICE_DUE_DATE;
	}
	public void setNET_INVOICE_DUE_DATE(String nET_INVOICE_DUE_DATE) {
		NET_INVOICE_DUE_DATE = nET_INVOICE_DUE_DATE;
	}
	public String getDISCOUNTED_AMOUNT_DUE() {
		return DISCOUNTED_AMOUNT_DUE;
	}
	public void setDISCOUNTED_AMOUNT_DUE(String dISCOUNTED_AMOUNT_DUE) {
		DISCOUNTED_AMOUNT_DUE = dISCOUNTED_AMOUNT_DUE;
	}
	public String getTERMS_DISCOUNT_AMOUNT() {
		return TERMS_DISCOUNT_AMOUNT;
	}
	public void setTERMS_DISCOUNT_AMOUNT(String tERMS_DISCOUNT_AMOUNT) {
		TERMS_DISCOUNT_AMOUNT = tERMS_DISCOUNT_AMOUNT;
	}
	public String getMERCHANDISE_AMT() {
		return MERCHANDISE_AMT;
	}
	public void setMERCHANDISE_AMT(String mERCHANDISE_AMT) {
		MERCHANDISE_AMT = mERCHANDISE_AMT;
	}
	public String getTOTAL_ALLOWANCES() {
		return TOTAL_ALLOWANCES;
	}
	public void setTOTAL_ALLOWANCES(String tOTAL_ALLOWANCES) {
		TOTAL_ALLOWANCES = tOTAL_ALLOWANCES;
	}
	public String getTOTAL_CHARGES() {
		return TOTAL_CHARGES;
	}
	public void setTOTAL_CHARGES(String tOTAL_CHARGES) {
		TOTAL_CHARGES = tOTAL_CHARGES;
	}
	public String getTOTAL_TAX() {
		return TOTAL_TAX;
	}
	public void setTOTAL_TAX(String tOTAL_TAX) {
		TOTAL_TAX = tOTAL_TAX;
	}
	public String getTRANSACTION_GUID() {
		return TRANSACTION_GUID;
	}
	public void setTRANSACTION_GUID(String tRANSACTION_GUID) {
		TRANSACTION_GUID = tRANSACTION_GUID;
	}
	public String getTOTAL_FREIGHT() {
		return TOTAL_FREIGHT;
	}
	public void setTOTAL_FREIGHT(String tOTAL_FREIGHT) {
		TOTAL_FREIGHT = tOTAL_FREIGHT;
	}
	public List<ErrorBean> getErrorList() {
		return errorList;
	}
	public void setErrorList(List<ErrorBean> errorList) {
		this.errorList = errorList;
	}
	public List<PackageTrackingBean> getPackageTrackingBean() {
		return packageTrackingBean;
	}
	public void setPackageTrackingBean(List<PackageTrackingBean> packageTrackingBean) {
		this.packageTrackingBean = packageTrackingBean;
	}
	public String getDISCOUNT_PERCENTAGE() {
		return DISCOUNT_PERCENTAGE;
	}
	public void setDISCOUNT_PERCENTAGE(String dISCOUNT_PERCENTAGE) {
		DISCOUNT_PERCENTAGE = dISCOUNT_PERCENTAGE;
	}
	public String getDISCOUNT_DATE() {
		return DISCOUNT_DATE;
	}
	public void setDISCOUNT_DATE(String dISCOUNT_DATE) {
		DISCOUNT_DATE = dISCOUNT_DATE;
	}
	public String getAMOUNT_SUBJECT_TO_DISCOUNT() {
		return AMOUNT_SUBJECT_TO_DISCOUNT;
	}
	public void setAMOUNT_SUBJECT_TO_DISCOUNT(String aMOUNT_SUBJECT_TO_DISCOUNT) {
		AMOUNT_SUBJECT_TO_DISCOUNT = aMOUNT_SUBJECT_TO_DISCOUNT;
	}
	public String getTOTAL_INVOICE_AMOUNT() {
		return TOTAL_INVOICE_AMOUNT;
	}
	public void setTOTAL_INVOICE_AMOUNT(String tOTAL_INVOICE_AMOUNT) {
		TOTAL_INVOICE_AMOUNT = tOTAL_INVOICE_AMOUNT;
	}
	public List<DetailBean> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<DetailBean> detailList) {
		this.detailList = detailList;
	}
	public String getLOCATION_QUALIFIER() {
		return LOCATION_QUALIFIER;
	}
	public void setLOCATION_QUALIFIER(String lOCATION_QUALIFIER) {
		LOCATION_QUALIFIER = lOCATION_QUALIFIER;
	}
}

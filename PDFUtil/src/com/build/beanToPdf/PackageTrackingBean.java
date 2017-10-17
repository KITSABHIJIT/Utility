package com.build.beanToPdf;

public class PackageTrackingBean {
	
	////EDI810_DPT
	String SKU_NUMBER,
	PACKAGE_TRACKING_NUMBER;

	public String getSKU_NUMBER() {
		return SKU_NUMBER;
	}

	public void setSKU_NUMBER(String sKU_NUMBER) {
		SKU_NUMBER = sKU_NUMBER;
	}

	public String getPACKAGE_TRACKING_NUMBER() {
		return PACKAGE_TRACKING_NUMBER;
	}

	public void setPACKAGE_TRACKING_NUMBER(String pACKAGE_TRACKING_NUMBER) {
		PACKAGE_TRACKING_NUMBER = pACKAGE_TRACKING_NUMBER;
	}
}

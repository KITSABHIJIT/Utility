package com.build.beanToPdf;

public class ErrorBean {
	
	////EDI810_ERR
	String ERROR_CODE,
	ERROR,
	ERROR_DESCRIPTION;

	public String getERROR_CODE() {
		return ERROR_CODE;
	}

	public void setERROR_CODE(String eRROR_CODE) {
		ERROR_CODE = eRROR_CODE;
	}

	public String getERROR() {
		return ERROR;
	}

	public void setERROR(String eRROR) {
		ERROR = eRROR;
	}

	public String getERROR_DESCRIPTION() {
		return ERROR_DESCRIPTION;
	}

	public void setERROR_DESCRIPTION(String eRROR_DESCRIPTION) {
		ERROR_DESCRIPTION = eRROR_DESCRIPTION;
	}
}

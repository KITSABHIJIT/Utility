package com.build.beanToPdf;

public class DetailBean {
	
	//EDI810_DTL
	String QTY_INVOICED
	,UOM_CODE
	,PRICE_PER_UOM
	,VEND_UPC_NUM
	,SKU_NUMBER
	,OVERRIDE_SKU_NUMBER
	,LIST_PRICE
	,ITEM_DESC
	,VEND_MODEL_NUM
	,EXTENDED_COST
	,CASE_PACK;

	public String getQTY_INVOICED() {
		return QTY_INVOICED;
	}

	public void setQTY_INVOICED(String qTY_INVOICED) {
		QTY_INVOICED = qTY_INVOICED;
	}

	public String getUOM_CODE() {
		return UOM_CODE;
	}

	public void setUOM_CODE(String uOM_CODE) {
		UOM_CODE = uOM_CODE;
	}

	public String getPRICE_PER_UOM() {
		return PRICE_PER_UOM;
	}

	public void setPRICE_PER_UOM(String pRICE_PER_UOM) {
		PRICE_PER_UOM = pRICE_PER_UOM;
	}

	public String getVEND_UPC_NUM() {
		return VEND_UPC_NUM;
	}

	public void setVEND_UPC_NUM(String vEND_UPC_NUM) {
		VEND_UPC_NUM = vEND_UPC_NUM;
	}

	public String getSKU_NUMBER() {
		return SKU_NUMBER;
	}

	public void setSKU_NUMBER(String sKU_NUMBER) {
		SKU_NUMBER = sKU_NUMBER;
	}

	public String getOVERRIDE_SKU_NUMBER() {
		return OVERRIDE_SKU_NUMBER;
	}

	public void setOVERRIDE_SKU_NUMBER(String oVERRIDE_SKU_NUMBER) {
		OVERRIDE_SKU_NUMBER = oVERRIDE_SKU_NUMBER;
	}

	public String getLIST_PRICE() {
		return LIST_PRICE;
	}

	public void setLIST_PRICE(String lIST_PRICE) {
		LIST_PRICE = lIST_PRICE;
	}

	public String getITEM_DESC() {
		return ITEM_DESC;
	}

	public void setITEM_DESC(String iTEM_DESC) {
		ITEM_DESC = iTEM_DESC;
	}

	public String getVEND_MODEL_NUM() {
		return VEND_MODEL_NUM;
	}

	public void setVEND_MODEL_NUM(String vEND_MODEL_NUM) {
		VEND_MODEL_NUM = vEND_MODEL_NUM;
	}

	public String getEXTENDED_COST() {
		return EXTENDED_COST;
	}

	public void setEXTENDED_COST(String eXTENDED_COST) {
		EXTENDED_COST = eXTENDED_COST;
	}

	public String getCASE_PACK() {
		return CASE_PACK;
	}

	public void setCASE_PACK(String cASE_PACK) {
		CASE_PACK = cASE_PACK;
	}
}

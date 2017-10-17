package com.build.beanToPdf;

import java.util.ArrayList;
import java.util.List;

public class DataBuilder {
public static ResponseBean getResponseBean(){
	ResponseBean bean=new ResponseBean();
	bean.setINVOICE_NUMBER("2525024001-8145998010");
	bean.setINVOICE_DATE("20170927");
	bean.setAUTOMATCH_PRO_GRP("HAM");
	bean.setVENDOR_NUMBER("142191");
	bean.setVENDOR_NAME("WayFair LLC");
	bean.setSHIP_TO_WAREHOUSE_STORE("682");
	bean.setALT_VENDOR_NUMBER("142191");
	bean.setALT_VENDOR_NAME("WayFair LLC");
	bean.setSHIP_DATE("20170927");
	bean.setPURCHASE_ORDER_NUMBER("DMKOU7");
	bean.setPO_ENTRY_DATE("20170927");
	bean.setEDI_DATE("20170927");
	bean.setEDI_TIME("194708");
	bean.setBILL_OF_LADING_NUMBER("061319675256485");
	bean.setSTANDARD_CARRIER_ALPHA_CODE("FEDX");
	bean.setTOTAL_UNITS("2");
	bean.setAPPLICATION_ID("HAM");
	bean.setTERMS_CODE("01");
	bean.setNET_INVOICE_DUE_DATE("20171112");
	bean.setDISCOUNT_PERCENTAGE("2.3%");
	bean.setDISCOUNT_DATE("20171112");
	bean.setLOCATION_QUALIFIER("OR");
	bean.setAMOUNT_SUBJECT_TO_DISCOUNT("2.56");
	bean.setDISCOUNTED_AMOUNT_DUE("21.36");
	bean.setTERMS_DISCOUNT_AMOUNT("25.23");
	bean.setMERCHANDISE_AMT("15.85");
	bean.setTOTAL_ALLOWANCES("21.56");
	bean.setTOTAL_CHARGES("32.45");
	bean.setTOTAL_FREIGHT("17.51");
	bean.setTOTAL_INVOICE_AMOUNT("145.56");
	List<DetailBean> detailList= new ArrayList<DetailBean>();
	for(int i=1;i<10;i++){
		DetailBean detailBean = new DetailBean();
		detailBean.setEXTENDED_COST(""+(i*(i+.5)));
		detailBean.setITEM_DESC("SKU Details "+i);
		detailBean.setLIST_PRICE(""+(i+.5));
		detailBean.setPRICE_PER_UOM(""+(i+.5));
		detailBean.setQTY_INVOICED(""+i);
		detailBean.setSKU_NUMBER(""+Math.round(Math.random()+i));
		detailBean.setUOM_CODE("EA");
		detailBean.setVEND_MODEL_NUM(""+Math.round(Math.random()+i));
		detailBean.setVEND_UPC_NUM(""+Math.round(Math.random()+i));
		detailBean.setCASE_PACK("0");
		detailList.add(detailBean);
	}
	bean.setDetailList(detailList);
	
	List<PackageTrackingBean> pkgList= new ArrayList<PackageTrackingBean>();
	for(int i=1;i<5;i++){
		PackageTrackingBean pkgBean = new PackageTrackingBean();
		pkgBean.setPACKAGE_TRACKING_NUMBER(""+Math.round(Math.random()+i));
		pkgBean.setSKU_NUMBER(""+Math.round(Math.random()+i));
		pkgList.add(pkgBean);
	}
	bean.setPackageTrackingBean(pkgList);
	
	List<ErrorBean> errList= new ArrayList<ErrorBean>();
	for(int i=1;i<3;i++){
		ErrorBean errBean = new ErrorBean();
		errBean.setERROR("Error "+i);
		errBean.setERROR_CODE("Error Code "+i);
		errBean.setERROR_DESCRIPTION("Error Description "+i);
		errList.add(errBean);
	}
	bean.setErrorList(errList);
	
	return bean;
}
}

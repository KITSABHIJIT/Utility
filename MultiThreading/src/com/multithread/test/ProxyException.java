package com.multithread.test;



/**
 * FileName: ProxyException.java
 * @author gupma001
 * Date: Feb 26, 2009
 * Description: 
 *
 */
public class ProxyException extends Exception {
	
	private int
		faultCode;
	private String 
		faultData;
	private int 
		level= LEVEL.ERROR;//default
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getFaultCode() {
		return (faultCode);
	}

	public void setFaultCode(int faultCode) {
		this.faultCode = faultCode;
	}

	public ProxyException(Exception ex){
		super(ex);
	}

	public ProxyException(Exception ex, String msg){
		super(msg, ex);
	}
	
	public ProxyException(Exception ex, String msg, int faultCode){
		super(msg, ex);
		this.faultCode = faultCode;
	}
	
	public String toString(){
		String sFaultMsg=faultCode==0?"Message- " + getMessage():"Fault code: " + faultCode + ", FaultMsg: " + getMessage();
		String sLevel=getLevel()==LEVEL.WARNING?"WARNING: ":"ERROR: ";
		return (sLevel.concat(sFaultMsg));
	}
	
	public void setFaultData(String faultData) {
		this.faultData = faultData;
	}


	public String getFaultData() {
		return faultData;
	}

	public static interface LEVEL{
		public static int 
			WARNING=1,
			ERROR=2;					
	}
}



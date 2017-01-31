package com.staples.cards.exception;
/**
 * 
 * @author TCS
 *
 */
public class ServiceExceptionFactory {
	
	 
	public static ServiceException create(Exception ex, String faultCode, String msg){
		return (new ServiceException(ex,getLogInfo(faultCode, msg)));
	}
	
	public static ServiceException create(Exception ex, int faultCode, String flatFileName, String msg){
		return (new ServiceException(ex,getLogInfo(flatFileName, msg), faultCode));
	}
	
	public static ServiceException create(Exception ex){
		return (new ServiceException(ex));
	}
	
	public static ServiceException create(Exception ex, String msg){
		return (new ServiceException(ex, getLogInfo(msg)));
	}
	
	public static ServiceException create(Exception ex, int faultCode, String msg){
		return (new ServiceException(ex, getLogInfo(msg), faultCode));
	}
	private static String getLogInfo(String flatFileName, String msg){
		StringBuilder builder = new StringBuilder();
		builder.append(msg= msg==null?"":(flatFileName+"\n" + msg));
		return (builder.toString());		
	}
	
	private static String getLogInfo(String msg){
		StringBuilder builder = new StringBuilder();
		builder.append(msg= msg==null?"":("\n" + msg));
		return (builder.toString());		
	}
}

package com.staples.cards.exception;
/**
 * 
 * @author TCS
 *
 */
public class ServiceErrorCodes {
	/*
	 * Amex Level3 Error Codes.
	 * errors starts from 900 - 999.
	 */
	
	public static final int FILE_NOT_FOUND_EXCEPTION_CODE=900;
	public static final String FILE_NOT_FOUND_EXCEPTION="Missing configuration File: ";

	public static final int PROPERTIES_LOAD_EXCEPTION_CODE=901;
	public static final String PROPERTIES_LOAD_EXCEPTION="Unable to load property File: ";

	public static final int RESOURCE_CLOSING_EXCEPTION_CODE=903;
	public static final String RESOURCE_CLOSING_EXCEPTION="Exception while Closing the used resource: ";

	public static final int FILE_READ_EXCEPTION_CODE=904;
	public static final String FILE_READ_EXCEPTION="Exception while reading File: ";

	public static final int FTP_CONNECT_EXCEPTION_CODE=905;
	public static final String FTP_CONNECT_EXCEPTION="Unable to connect using FTP to: ";

	public static final int FTP_TIMEOUT_EXCEPTION_CODE=906;
	public static final String FTP_TIMEOUT_EXCEPTION="Timeout occuring while Connecting FTP to: ";

	public static final int FTP_AUTHENTICATION_EXCEPTION_CODE=907;
	public static final String FTP_AUTHENTICATION_EXCEPTION="Authentication failing while Connecting FTP to: ";

	public static final int FTP_TRANSFER_EXCEPTION_CODE=908;
	public static final String FTP_TRANSFER_EXCEPTION="Failing while storing data to: ";

	public static final int XML_DOCUMENT_CREATION_EXCEPTION_CODE=909;
	public static final String XML_DOCUMENT_CREATION_EXCEPTION="Failed to create XML Document: ";

	public static final int XML_DOCUMENT_PARSING_EXCEPTION_CODE=910;
	public static final String XML_DOCUMENT_PARSING_EXCEPTION="Failed to parse XML Document: ";
	public static final String XML_OUTPUT_DOCUMENT_PARSING_EXCEPTION="Failed to parse Output XML Document";

	public static final int XPATH_OUTPUT_XML_EXCEPTION_CODE=911;
	public static final String XPATH_OUTPUT_XML_EXCEPTION="Output XPATH Configuration is wrong";
	
	public static final int XML_TRANSFORMER_EXCEPTION_CODE=912;
	public static final String XML_TRANSFORMER_EXCEPTION="Failed to transform the output xml";

	public static final int ADD_TO_CONCURRENT_CONTAINER_EXCEPTION_CODE=913;
	public static final String ADD_TO_CONCURRENT_CONTAINER_EXCEPTION="Failed to add data to concurrent Container";

	public static final int REMOVE_FROM_CONCURRENT_CONTAINER_EXCEPTION_CODE=914;
	public static final String REMOVE_FROM_CONCURRENT_CONTAINER_EXCEPTION="Failed to remove data from concurrent Container";

	public static final int RKM_INITIALIZATION_EXCEPTION_CODE=915;
	public static final String RKM_INITIALIZATION_EXCEPTION="Failed to initialize RKM";

	public static final int RKM_ENCRYPTION_EXCEPTION_CODE=916;
	public static final String RKM_ENCRYPTION_EXCEPTION="Failed to ecrypt data using RKM: ";

}

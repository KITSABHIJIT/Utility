package com.staples.cards.begin;

import java.util.List;

import com.staples.cards.constants.CardProperties;
import com.staples.cards.exception.ServiceException;
import com.staples.cards.logger.CardLogger;
import com.staples.cards.util.FileManager;

public class ProcessFlow {

	public static void getTargetXML(List<String> sourceDoc,String outputFile) throws ServiceException{

		FileManager.writeToFile(CardProperties.XML_CARD_INFO_START_TAG,CardProperties.OUTPUT_FILE_DEST_DIR,outputFile, false);
		FileManager.writeToFile(CardProperties.XML_CARD_START_TAG,CardProperties.OUTPUT_FILE_DEST_DIR,outputFile, true);
		for(String data:sourceDoc){
			FileManager.writeToFile(data,CardProperties.OUTPUT_FILE_DEST_DIR,outputFile, true);
		}
		FileManager.writeToFile(CardProperties.XML_CARD_END_TAG,CardProperties.OUTPUT_FILE_DEST_DIR,outputFile, true);
		FileManager.writeToFile(CardProperties.XML_CARD_INFO_END_TAG,CardProperties.OUTPUT_FILE_DEST_DIR,outputFile, true);
		CardLogger.debug(CardProperties.OUTPUT_FILE_DEST_DIR+outputFile+" written successfully");
	}
	/*public static void main(String args[]){
		try {
			EDIProperties.initProperties();
			List<Map<String,List<String>>> ediData=BuildEDIData.splitHeaderDetailData(FileManager.getData(EDIProperties.INPUT_FILE));
			for(Map<String,List<String>> ediInvoice:ediData){
				ProcessFlow.getTargetXML(ediInvoice);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
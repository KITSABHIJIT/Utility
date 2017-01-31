package com.staples.cards.begin;

import com.staples.cards.constants.CardProperties;
import com.staples.cards.databaseconnector.BuiltCardInfoData;
import com.staples.cards.exception.ServiceException;
import com.staples.cards.logger.CardLogger;
import com.staples.cards.util.DateUtil;
import com.staples.cards.util.StringUtil;

public class GenerateCardXML {

	public static boolean createBinXMLFile(String cardPropertiesFile) {
		if(!StringUtil.isBlankOrEmpty(cardPropertiesFile)){
			try {
				final long startTime = System.currentTimeMillis();
				CardProperties.initProperties(cardPropertiesFile);
				//RKMEngineSvc27.initializeRKM(CardProperties.RKM_CONFIG_PATH);
				ProcessFlow.getTargetXML(BuiltCardInfoData.getCardInfoData(BuiltCardInfoData.getCardInfoObj(true)),CardProperties.OUTPUT_FILE_PREPAID);
				long exitTime = System.currentTimeMillis();
				String timeTaken="Prepaid CardInfo generation ended with processing time :"+DateUtil.getHrMinSec(exitTime-startTime);
				CardLogger.debugAndInfo(timeTaken);
				ProcessFlow.getTargetXML(BuiltCardInfoData.getCardInfoData(BuiltCardInfoData.getCardInfoObj(false)),CardProperties.OUTPUT_FILE_CREDIT);
				exitTime = System.currentTimeMillis();
				timeTaken="Credit CardInfo generation ended with processing time :"+DateUtil.getHrMinSec(exitTime-startTime);
				CardLogger.debugAndInfo(timeTaken);
				return true;
			} catch (ServiceException ex) {
				CardLogger.error(ex.toString(), ex);
				return false;
			}
		}else{
			CardLogger.error("No Card config file passed as a parameter. So the Card bin XML creation process didn't started.");
			return false;
		}
	}
	
	public static void main(String[] args){
		if(args.length>0){
			createBinXMLFile(args[0]);
		}else{
			CardLogger.error("No Card config file passed as a parameter. So the Card bin XML creation process didn't started.");
		}
	}
}

package com.staples.cards.databaseconnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.staples.cards.constants.CardProperties;
import com.staples.cards.exception.ServiceException;
import com.staples.cards.logger.CardLogger;
import com.staples.cards.pojo.CardInfo;
import com.staples.cards.util.StringUtil;


/**
 * Contains methods for accessing the AS400 database
 * Each of the methods queries the database and returns some results to the calling methods
 * @author TCS
 * @version 1.0
 */
public class BuiltCardInfoData {

	public static List<CardInfo> getCardInfoObj(boolean prepaidType) {
		PreparedStatement pStmt=null;
		ResultSet rs=null;
		Connection con=null;
		List<CardInfo> cardNodes=new ArrayList<CardInfo>();
		String query =(prepaidType)? CardProperties.GET_CARDINFO_PREPAID_QUERY:CardProperties.GET_CARDINFO_CREDIT_QUERY;
		CardLogger.debug("CARDINFO_QUERY  : " + query);
		try {
			con=DatabaseConnectionClass.connectToDatabase(CardProperties.DATABASE_LIBRARY);
			pStmt = con.prepareStatement(query);
			rs = pStmt.executeQuery();
			while(rs.next()) {
				String lowerPrefix=validatePrefix(rs.getString(1),false);
				String upperPrefix=	validatePrefix(rs.getString(2),true);
				String cardLength=validateCardNode(rs.getString(3));
				CardInfo info=new CardInfo(lowerPrefix,upperPrefix,cardLength
						, validateCardNode(rs.getString(4))
						, validateCardNode(rs.getString(5))
						, validateCardNode(rs.getString(6))
						, validateCardNode(rs.getString(7)));
				
				if(cardNodes.size()>0){
					CardInfo previousInfo=cardNodes.get(cardNodes.size()-1);
					int previousUpperPrefix=Integer.parseInt(previousInfo.getPrefixUpper());
					int currentLowerPrefix=Integer.parseInt(info.getPrefixLower());
					if((previousUpperPrefix+1)==currentLowerPrefix){
						info.setPrefixLower(previousInfo.getPrefixLower());
						cardNodes.set(cardNodes.size()-1, info);
					}else{
						cardNodes.add(info);
					}
				}else{
					cardNodes.add(info);
				}
			}
		}catch (SQLException e) {
			CardLogger.error(BuiltCardInfoData.class+"::getCardInfoObj() ",e);
			e.printStackTrace();
		}
		finally {
			DatabaseConnectionClass.closeConnection(con, pStmt, rs);
		}
		CardLogger.debug("Static BIN Processing started");
		if(prepaidType){
			CardLogger.debug("Processing AMEX_PREPAID_BIN");
			cardNodes.addAll(getStaticCardInfoObj(CardProperties.AMEX_PREPAID_BIN.split(CardProperties.XML_CARD_VALUE_SEPERATOR),
					CardProperties.AMEX_CARD_CLASS_NAME,CardProperties.STATIC_AM_CC_CARD_LENGTH,prepaidType));
		}else{
			CardLogger.debug("Processing AMEX_CREDIT_BIN");
			cardNodes.addAll(getStaticCardInfoObj(CardProperties.AMEX_CREDIT_BIN.split(CardProperties.XML_CARD_VALUE_SEPERATOR),
					CardProperties.AMEX_CARD_CLASS_NAME,CardProperties.STATIC_AM_CC_CARD_LENGTH,prepaidType));
			CardLogger.debug("Processing STAPLES_CREDIT_CARD_BIN");
			cardNodes.addAll(getStaticCardInfoObj(CardProperties.STAPLES_CREDIT_CARD_BIN.split(CardProperties.XML_CARD_VALUE_SEPERATOR),
					CardProperties.STAPLES_CREDIT_CARD_CLASS_NAME,CardProperties.STATIC_STAPLES_CARD_LENGTH,prepaidType));
			CardLogger.debug("Processing STAPLES_OPEN_CREDIT_CARD_BIN");
			cardNodes.addAll(getStaticCardInfoObj(CardProperties.STAPLES_OPEN_CREDIT_CARD_BIN.split(CardProperties.XML_CARD_VALUE_SEPERATOR),
					CardProperties.STAPLES_OPEN_CREDIT_CARD_CLASS_NAME,CardProperties.STATIC_STAPLES_CARD_LENGTH,prepaidType));
			CardLogger.debug("Processing CONVENIENCE_CREDIT_CARD_BIN");
			cardNodes.addAll(getStaticCardInfoObj(CardProperties.CONVENIENCE_CREDIT_CARD_BIN.split(CardProperties.XML_CARD_VALUE_SEPERATOR),
					CardProperties.CONVENIENCE_CREDIT_CARD_CLASS_NAME,CardProperties.STATIC_AM_CC_CARD_LENGTH,prepaidType));
		}
		CardLogger.debug("Static BIN Processing Ended");
		return cardNodes;
	}
	public static List<CardInfo> getStaticCardInfoObj(String[] strArray,String cardClass,String cardLength,boolean prepaidType) {
		List<CardInfo> cardNodes=new ArrayList<CardInfo>();
		List<String> staticBinList=new ArrayList<String>();
		staticBinList.addAll(StringUtil.convertArrayToList(strArray));
		List<String> cardLengthList=new ArrayList<String>();
		cardLengthList.addAll(StringUtil.convertArrayToList(cardLength.split(CardProperties.XML_CARD_VALUE_SEPERATOR)));
		for(String staticBin: staticBinList) {
			String lowerPrefix=validatePrefix(staticBin,false);
			String upperPrefix=	validatePrefix(staticBin,true);
			for(String unitCardLength: cardLengthList){
				CardInfo info=new CardInfo(lowerPrefix,upperPrefix,unitCardLength,
						(prepaidType)?CardProperties.STATIC_CARD_SUB_CLASS_PREPAID:CardProperties.STATIC_CARD_SUB_CLASS_CREDIT
								, cardClass
								, CardProperties.STATIC_PROVIDER
								, CardProperties.STATIC_COUNTRY);
				cardNodes.add(info);
			}
		}
		return cardNodes;
	}
	public static List<String> getCardInfoData(List<CardInfo> cardData) throws ServiceException {
		List<String> cardNodes=new ArrayList<String>();
		int recordCounter=0;
		StringBuilder builder=new StringBuilder();
		for (CardInfo entry : cardData){
			builder.append(entry.getPrefixLower())
			.append(CardProperties.XML_CARD_RECORD_SEPERATOR)
			.append(entry.getPrefixUpper())
			.append(CardProperties.XML_CARD_RECORD_SEPERATOR)
			.append(entry.getCardLength())
			.append(CardProperties.XML_CARD_RECORD_SEPERATOR)
			.append(entry.getCardSubClass())
			.append(CardProperties.XML_CARD_RECORD_SEPERATOR)
			.append(entry.getCardClassName())
			.append(CardProperties.XML_CARD_RECORD_SEPERATOR)
			.append(entry.getProvider())
			.append(CardProperties.XML_CARD_RECORD_SEPERATOR)
			.append(entry.getCountry());
			recordCounter++;
			if(recordCounter==CardProperties.RECORD_PER_NODE){
				cardNodes.add(builder.toString().trim());
				recordCounter=0;
				builder =new StringBuilder();
			}else{
				builder.append(System.getProperty("line.separator"));
			}
			
		}
		if(!StringUtil.isBlankOrEmpty(builder.toString())){
			cardNodes.add(builder.toString().trim());
		}
		return cardNodes;
	}

	public static String validateCardNode(String nodeValue){
		String result="-";
		if(!StringUtil.isBlankOrEmpty(nodeValue)){
			result=nodeValue.trim();
		}
		return result;
	}
	public static String validatePrefix(String nodeValue,boolean upper){
		String result="-";
		if(!StringUtil.isBlankOrEmpty(nodeValue)){
			result=(upper)?StringUtil.padString(nodeValue, 6, false, CardProperties.XML_CARD_UPPER_PREFIX_APPENDER)
					:StringUtil.padString(nodeValue, 6, false, CardProperties.XML_CARD_LOWER_PREFIX_APPENDER);
		}
		return result;
	}


}






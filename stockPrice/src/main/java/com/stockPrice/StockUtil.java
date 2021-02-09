package com.stockPrice;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockDividend;
import yahoofinance.quotes.stock.StockQuote;
import yahoofinance.quotes.stock.StockStats;

public class StockUtil implements Runnable {

	public static final String NYSE_LISTED_STOCKS_URL="https://datahub.io/core/nyse-other-listings/r/nyse-listed.json";
	public static final String OTHER_LISTED_STOCKS_URL="https://datahub.io/core/nyse-other-listings/r/other-listed.json";
	private String outFile;
	private String method;
	private List<String> symbols;
	double marketCap;
	double change;
	double sharePrice;

	public StockUtil(List<String> symbols,Double marketCap,Double change,Double sharePrice,String outFile, String method) {
		this.outFile = outFile;
		this.symbols = symbols;
		this.method = method;
		this.marketCap = marketCap;
		this.change = change;
		this.sharePrice = sharePrice;
	}

	public void run() {
		if(null!=method) {
			if("StockDetails".equals(method)) {
				getStockDetails(symbols,outFile,marketCap,change,sharePrice);
			}else if("StocksOverThreshold".equals(method)) {
				getStocksOverThreshold(symbols,outFile,sharePrice);
			}else if("PennyStocks".equals(method)) {
				getPennyStocks(symbols,sharePrice,outFile);
			}else {
				System.out.println("Please select a valid method");
			}
		}
	}

	public static void getStockDetails(List<String> symbols,String outFile,Double marketCap,Double change, Double sharePrice) {
		Stock stock;
		FileUtil.deleteFile(outFile);
		for(String symbol:symbols) {
			try {
				stock = YahooFinance.get(symbol);
				if(null!=stock) {
					StockStats stats=stock.getStats();
					StockQuote quote=stock.getQuote();
					if(null!=stats && null!=quote  && null!=stats.getMarketCap() 
							&& stats.getMarketCap().doubleValue()>=marketCap
							&& quote.getPrice().doubleValue()<=sharePrice
							&& Math.abs((quote.getChange()).doubleValue())>=change
							) {
						FileUtil.writeToFile(stock.getSymbol(), outFile);
					}
				}else {
					System.out.println(symbol + ": Data not available");
				}

			} catch (IOException e) {
				System.out.println(symbol + ": Data not available: "+e.getMessage());
			}
		}
	}

	public static void getStockDetailsHTML(List<String> symbols,String outFile) {
		Stock stock;
		List<JSONArray> list = new ArrayList<JSONArray>();
		FileUtil.deleteFile(outFile);
		for(String symbol:symbols) {
			try {
				stock = YahooFinance.get(symbol);
				if(null!=stock) {
					StockStats stats=stock.getStats();
					StockQuote quote=stock.getQuote();
					StockDividend dividend=stock.getDividend();
					if(null!=stats && null!=quote  && null!=stats.getMarketCap()) {
							StockObject stockObject=new StockObject();
							stockObject.setName(stock.getName());
							stockObject.setSymbol(stock.getSymbol());
							stockObject.setExchange(stock.getStockExchange());
							stockObject.setOpen(quote.getOpen());
							stockObject.setHigh(quote.getDayHigh());
							stockObject.setLow(quote.getDayLow());
							stockObject.setMarketCap(stats.getMarketCap());
							stockObject.setAvgVolume(quote.getAvgVolume());
							stockObject.setVolume(quote.getVolume());
							stockObject.setChange(quote.getChange());
							stockObject.setDailyChange(quote.getPreviousClose().subtract(quote.getOpen()));
							stockObject.setNearToYearLow(quote.getPrice().subtract(quote.getYearLow()));
							stockObject.setPeg(stats.getPeg());
							stockObject.setDividend(dividend.getAnnualYield());
							stockObject.setPrice(quote.getPrice());
							stockObject.setYearLow(quote.getYearLow());
							stockObject.setYearHigh(quote.getYearHigh());
							System.out.println(stockObject.toString());
							LinkedList<Object> dataSet = new LinkedList<Object>();
							dataSet.add(stockObject.getName()+" ("+stockObject.getSymbol()+")");
							dataSet.add(stockObject.getExchange());
							dataSet.add(stockObject.getPrice());
							dataSet.add(stockObject.getOpen());
							dataSet.add(stockObject.getHigh());
							dataSet.add(stockObject.getLow());
							dataSet.add(stockObject.getChange());
							dataSet.add(stockObject.getDailyChange());
							dataSet.add(stockObject.getNearToYearLow());
							dataSet.add(currencyFormat(stockObject.getMarketCap()));
							dataSet.add(stockObject.getAvgVolume());
							dataSet.add(stockObject.getVolume());
							dataSet.add(stockObject.getYearHigh());
							dataSet.add(stockObject.getYearLow());
							dataSet.add(stockObject.getPeg());
							dataSet.add(stockObject.getDividend());
							list.add(new JSONArray(dataSet));
					}
				}else {
					System.out.println(symbol + ": Data not available");
				}

				//stock.print();

				/*Calendar from = Calendar.getInstance();
			Calendar to = Calendar.getInstance();
			from.add(Calendar.YEAR, -1); // from 1 year ago

			Stock google = YahooFinance.get("ACB");
			StockStats stats=google.getStats();
			StockQuote quote=google.getQuote();*/


			} catch (IOException e) {
				System.out.println(symbol + ": Data not available: "+e.getMessage());
			}
		}
			String content=FileUtil.getStringFromFile("config/stocksToBuyTemplate");
			content=content.replace("<TABULAR_DATA>",(list.toString()));
			FileUtil.writeToFile(content, outFile);
	}
	public static void getStocksOverThreshold(List<String> symbols,String outFile,Double value) {
		Stock stock;
		for(String symbol:symbols) {
			try {
				stock = YahooFinance.get(symbol);
				if(null!=stock) {
					StockStats stats=stock.getStats();
					StockQuote quote=stock.getQuote();
					BigDecimal threshold = new BigDecimal(value);
					BigDecimal range = new BigDecimal(10);
					if(null!=stats.getMarketCap() && stats.getMarketCap().compareTo(threshold)>=0
							&& null!=quote && (quote.getYearHigh().subtract(quote.getYearLow())).compareTo(range)>=0) {
						FileUtil.writeToFile(stock.getSymbol(), outFile);
					}
				}else {
					System.out.println(symbol + ": Data not available");
				}

				//stock.print();

				/*Calendar from = Calendar.getInstance();
			Calendar to = Calendar.getInstance();
			from.add(Calendar.YEAR, -1); // from 1 year ago

			Stock google = YahooFinance.get("ACB");
			StockStats stats=google.getStats();
			StockQuote quote=google.getQuote();*/


			} catch (IOException e) {
				System.out.println(symbol + ": Data not available: "+e.getMessage());
			}
		}
	}

	public static void getPennyStocks(List<String> symbols,Double thresholdValue,String outFile) {
		FileUtil.deleteFile(outFile);
		Stock stock;
		for(String symbol:symbols) {
			try {
				stock = YahooFinance.get(symbol);
				if(null!=stock) {
					StockQuote quote=stock.getQuote();
					if(null!=quote.getPrice() && quote.getPrice().doubleValue()<=thresholdValue) {
						FileUtil.writeToFile(stock.getSymbol(), outFile);
					}
				}else {
					System.out.println(symbol + ": Data not available");
				}

				//stock.print();

				/*Calendar from = Calendar.getInstance();
			Calendar to = Calendar.getInstance();
			from.add(Calendar.YEAR, -1); // from 1 year ago

			Stock google = YahooFinance.get("ACB");
			StockStats stats=google.getStats();
			StockQuote quote=google.getQuote();*/


			} catch (IOException e) {
				System.out.println(symbol + ": Data not available: "+e.getMessage());
			}
		}
	}

	public static String currencyFormat(BigDecimal n) {
		NumberFormat.getInstance(Locale.US);
		return NumberFormat.getCurrencyInstance().format(n);
	}

	public static void getValidStocks(List<String> symbols, String outFile) {
		Stock stock;
		FileUtil.deleteFile(outFile);
		for(String symbol:symbols) {
			try {
				stock = YahooFinance.get(symbol);
				if(null!=stock && null!=stock.getQuote()) {
					FileUtil.writeToFile(stock.getSymbol(), outFile);
				}else {
					System.out.println(symbol + ": Data not available");
				}
			} catch (IOException e) {
				System.out.println(symbol + ": Data not available: "+e.getMessage());
			}
		}
	}

	public static List<List<String>> getSymbolList(JSONArray finalList) {
		List<List<String>> finalSymbolList=new ArrayList<List<String>>();
		List<String> temp=new ArrayList<String>();
		if(finalList!=null && finalList.length()>0){
			for (int i = 0; i < finalList.length(); i++) {
				JSONObject json=(JSONObject) finalList.get(i);
				try {
					temp.add(URLDecoder.decode((String) json.get("ACT Symbol"), "UTF-8"));
				} catch (UnsupportedEncodingException | JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(temp.size()>=500) {
					finalSymbolList.add(temp);
					temp=new ArrayList<String>();
				}
			}
			finalSymbolList.add(temp);
		}
		return finalSymbolList;

	}
	public static String getHrMinSec(long timeInMilliSeconds){
		if(timeInMilliSeconds<0){
			return timeInMilliSeconds+"";
		}
		long seconds = timeInMilliSeconds / 1000;
		long minutes = seconds / 60;
		long hours = minutes / 60;
		long days = hours / 24;
		String time = days + " days " + hours % 24 + " hr " + minutes % 60 + " min " + seconds % 60+ " sec "+ timeInMilliSeconds % 1000+ " millisec";
		return time;
	}
	
	public static String toString(Stock stock) {
		
		return stock.getSymbol()+"|"
		+stock.getName()+"|"
		+stock.getStats().getMarketCap().toString()+"|"
		+stock.getQuote().getAvgVolume().toString()+"|"
		+stock.getQuote().getVolume().toString()+"|"
		+stock.getQuote().getYearLow().toString()+"|"
		+stock.getQuote().getYearHigh().toString();
		
	}
}

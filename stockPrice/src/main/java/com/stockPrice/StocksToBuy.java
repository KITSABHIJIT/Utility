package com.stockPrice;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;

public class StocksToBuy {


	public static Double marketCap = new Double(1000000000);
	public static Double change = new Double(0);
	public static Double yearLowMargin = new Double(0);
	public static Double thresholdValue = new Double(0);
	public static Double sharePrice = new Double(3);
	public static void main(String[] args) {

		/*try {
			Stock stock = YahooFinance.get("CCO");
			System.out.println(stock.getStats().getMarketCap().doubleValue());
			System.out.println(stock.getStats().getMarketCap().doubleValue()>marketCap);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		buyStocks();
		
	}

	public static void buyStocks() {
		long processStarted=System.currentTimeMillis();
		List<List<String>> symbolList=new ArrayList<List<String>>();
		try {
			JSONArray nyJsonArr = new JSONArray(IOUtils.toString(new URL(StockUtil.NYSE_LISTED_STOCKS_URL), Charset.forName("UTF-8")));
			JSONArray otherJsonArr = new JSONArray(IOUtils.toString(new URL(StockUtil.OTHER_LISTED_STOCKS_URL), Charset.forName("UTF-8")));
			List<Object> list = nyJsonArr.toList();
			list.addAll(otherJsonArr.toList());
			JSONArray finalList= new JSONArray(list);
			symbolList=StockUtil.getSymbolList(finalList);

		} catch (JSONException | IOException e1) {
			e1.printStackTrace();
		}

		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(symbolList.size());

		for (int i = 1; i <= symbolList.size(); i++) 
		{
			StockUtil task = new StockUtil(symbolList.get(i-1), marketCap,change,sharePrice,"temp/stockToBuyList"+i+".txt","StockDetails");
			executor.execute(task);
		}
		executor.shutdown();
		while (!executor.isTerminated()) {
		}
		System.out.println("Finished all threads..Total time taken"+StockUtil.getHrMinSec(System.currentTimeMillis()-processStarted));
		getStocksDetails("temp");
	}

	public static void getStocksDetails(String directory) {
		List<String> symbols =new ArrayList<String>();
		for(String file: FileUtil.getListOfFiles(directory, true)) {
			symbols.addAll(FileUtil.readFromFile(file));
		}
		StockUtil.getStockDetailsHTML(symbols, "config/stockToBuyList.html");

	}
}

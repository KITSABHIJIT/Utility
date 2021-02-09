package com.stockPrice;

import java.io.IOException;
import java.util.List;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class GetValidStocks implements Runnable {
	private String outFile;
	private List<String> symbols;

	public GetValidStocks(List<String> symbols, String outFile) {
		this.outFile = outFile;
		this.symbols = symbols;
	}

	public String getName() {
		return outFile;
	}

	public void run() {
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
			} catch (IOException  e) {
				System.out.println(symbol + ": Data not available: "+e.getMessage());
			}
		}
	} 
}

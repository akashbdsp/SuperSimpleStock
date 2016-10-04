package com.jpm.dao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jpm.entities.Stock;


public class StockDao {

	static private HashMap<String,Stock> stocks=null;
	public void init(){
		if(stocks==null)
			stocks=new HashMap<String,Stock>();
		
	}
	
	public void addStock(Stock s){
		stocks.put(s.getSymbol(), s);
	}
	
	public Stock getStockBySymbol(String s){
		return stocks.get(s);
	}
	
	public void deleteStock(Stock s){
		stocks.remove(s.getSymbol());
	}
	
	public List<Stock> getAllStocks(){
		List<Stock> l=new ArrayList<Stock>();
		l.addAll(stocks.values());
		return l;
		
	}
	
	public void free(){
		stocks=null;
	}
	
	
}

package com.jpm.dao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jpm.entities.Stock;
import com.jpm.entities.Trade;

public class TradeDao {
	
	
	static private List<Trade> trades=null;
	public void init(){
		if(trades==null)
			trades=new ArrayList<Trade>();
	}
	public void addTrade(Trade t){
		trades.add(t);
	}
	
	public List<Trade> getAllTrades(){
		return trades;
	}
	
	public List<Trade> getTradesByStock(Stock s){
		List<Trade> aux=new ArrayList<Trade>();
		for(Trade t:trades){
			if(t.getStock().equals(s))
				aux.add(t);
		}
			
		return aux;
	}
	
	public Trade getTradeByStockDate(Stock s,Date d){
		for(Trade tr:trades){
			if(tr.getStock().equals(s)&&tr.getTimestamp().equals(d)) return tr;
		}
		return null;
		
	}
	
	public void deleteTrade(Trade t){
		int index=0;
		
		for(Trade tr:trades){
			if(tr.equals(t)) trades.remove(index);
			index++;
		}
	}
	
	public void free(){
		trades=null;
	}
	

}

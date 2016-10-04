package com.jpm.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.jpm.dao.StockDao;
import com.jpm.dao.TradeDao;
import com.jpm.entities.Stock;
import com.jpm.entities.Trade;

public class StockTradeService {

	static public int SELL=0;
	static public int BUY=0;
	static public int ERR=-1;
	static public int OK=0;
	
	
	static private StockDao sdao;
	static private TradeDao tdao;
	
	public StockTradeService(){
		sdao=new StockDao();
		sdao.init();
		tdao=new TradeDao();
		tdao.init();
	}
	
	
	public int addStock(Stock s){
		if(s==null) return ERR;
		sdao.addStock(s);
				
		return OK;
		
	}
	
	public double dividendYield(String stock,double price){		
		
		Stock s= sdao.getStockBySymbol(stock);
		return s.dividendYeldFunction(price);
	}
	
	public double pEratio(String stock,double price){
		
		Stock s= sdao.getStockBySymbol(stock);
		if(s.getLastDividend()==0) return ERR;
		
		return price/s.getLastDividend();
	}
	
	public int recordTrade(String stock, int qtty,int indicator,double price){
		if(stock==null) return ERR;
		Stock s= sdao.getStockBySymbol(stock);
		Trade t=new Trade(new Date(),qtty,indicator,price,s);
		tdao.addTrade(t);
		return OK;
		
	}
	
	public double volumeWeightedStock(String stock){
		Stock s= sdao.getStockBySymbol(stock);
		List<Trade> l=tdao.getTradesByStock(s);
		double qxpSum=0,qSum=0;
		for(Trade t:l){
			qxpSum+=(t.getPrice()*t.getQuantity());
			qSum+=t.getQuantity();
		}
		
		if(qSum==0) return 1;//Return 1 if no trades for given stock to avoid GBCE become 0
		
		return qxpSum/qSum;		
		
	}
	
	public double volumeWeightedStock5min(String stock){
		Stock s= sdao.getStockBySymbol(stock);
		List<Trade> l=tdao.getTradesByStock(s);
		Date dt=new Date();
		Calendar calPrev = GregorianCalendar.getInstance();
		calPrev.setTime(dt);
		calPrev.add(Calendar.MINUTE, -5);
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(dt);
		
		Calendar calAux = GregorianCalendar.getInstance();
		double qxpSum=0,qSum=0;
		for(Trade t:l){
			calAux.setTime(t.getTimestamp());
			if((calAux.after(calPrev)&&calAux.before(cal))||calAux.equals(calPrev)||calAux.equals(cal)){
				qxpSum+=(t.getPrice()*t.getQuantity());
				qSum+=t.getQuantity();
			}
		}
		
		if(qSum==0) return 1;//Return 1 if no trades for given stock to avoid GBCE become 0
		
		return qxpSum/qSum;		
		
	}
	
	public double allShareIndex(){
		
		List<Stock> ls=sdao.getAllStocks();
		
		double chainProd=1;
		
		for(Stock s:ls){
			double vws=volumeWeightedStock(s.getSymbol());
			if(vws!=ERR)
				chainProd*=vws;				
			
		}
		
		if(chainProd==-1) return -1;		
		
		return Math.pow(chainProd, 1.0/ls.size());
		
	}
	
	public void deleteTrades(){
		
		tdao.free();
		tdao.init();
	}
	
}

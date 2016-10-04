package com.jpm.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jpm.dao.StockDao;
import com.jpm.dao.TradeDao;
import com.jpm.entities.CommonStock;
import com.jpm.entities.PreferredStock;
import com.jpm.entities.Stock;
import com.jpm.entities.Trade;
import com.jpm.service.StockTradeService;

public class TestStockService {

	static StockTradeService stockService;
	private Stock[] stocks;
	
	
	@Before
	public void setUp() throws Exception {
		stockService=new StockTradeService();
		stocks=new Stock[5];
		stocks[0]=new CommonStock("TEA", 0, 100) ;
		stocks[1]=new CommonStock("POP", 8, 100) ;
		stocks[2]=new CommonStock("ALE", 23, 60) ;
		stocks[3]=new PreferredStock("GIN", 8, 100,0.02) ;
		stocks[4]=new CommonStock("JOE", 13, 250) ;
		for(int i=0;i<5;i++)
			stockService.addStock(stocks[i]);
	}
	
	

	@Test
	public void testDividendYeldCommon() {
		
		Double dv=stockService.dividendYield("POP", 4);
		
		Assert.assertEquals(new Double(2),dv);
		
	}
	
	@Test
	public void testDividendYeldCommonErr() {
		
		Double dv=stockService.dividendYield("POP", 0);
		
		Assert.assertEquals(new Double(StockTradeService.ERR),dv);
		
	}
	
	
	@Test
	public void testDividendYeldPreferred() {
		
		Double dv=stockService.dividendYield("GIN", 2);
		
		Assert.assertEquals(new Double(1),dv);
		
	}
	

	@Test
	public void testDividendYeldPreferredErr() {
		
		Double dv=stockService.dividendYield("GIN", 0);
		
		Assert.assertEquals(new Double(StockTradeService.ERR),dv);
		
	}
	
	
	@Test
	public void testPERCommonErr() {
		
		Double dv=stockService.pEratio("TEA", 2);
		
		Assert.assertEquals(new Double(StockTradeService.ERR),dv);
		
	}
	
	
	@Test
	public void testPERCommon() {
		
		Double dv=stockService.pEratio("POP", 2);
		
		Assert.assertEquals(new Double(1/4.0),dv);
		
	}
	
	@Test
	public void testPERPreferred() {
		
		Double dv=stockService.pEratio("GIN", 2);
		
		Assert.assertEquals(new Double(1/4.0),dv);
		
	}
	
	@Test
	public void testVWSP() {
		//Insert 2 trades 1 stock TEA
		stockService.deleteTrades();
		stockService.recordTrade("TEA",20, StockTradeService.BUY, 10);
		stockService.recordTrade("TEA",20, StockTradeService.BUY, 10);
		Double dv=stockService.volumeWeightedStock("TEA");
		//(200+200)/40=10
		Assert.assertEquals(new Double(10),dv);
		stockService.deleteTrades();
		
	}
	
	@Test
	public void testVWSPNoTrades() {
		
		//Insert 2 trades 1 stock TEA
		stockService.deleteTrades();
		Double dv=stockService.volumeWeightedStock("POP");
		//1
		Assert.assertEquals(new Double(1),dv);
		
	}
	
	@Test
	public void testVWSP5Min() {
			
		
		//Insert 2 trades 1 stock TEA
		stockService.deleteTrades();
		stockService.recordTrade("ALE",20, StockTradeService.BUY, 10);
		stockService.recordTrade("ALE",20, StockTradeService.BUY, 10);
		Double dv=stockService.volumeWeightedStock5min("ALE");
		//(200+200)/40=10
		Assert.assertEquals(new Double(10),dv);
		stockService.deleteTrades();
		
	}
	
	
	
	@Test
	public void testAllShareIndex() {
		
		
		
		
		
		//Creating a new set of trades to calculate all Share Index
		stockService.deleteTrades();		
		stockService.recordTrade("ALE",20, StockTradeService.BUY, 10);
		stockService.recordTrade("ALE",20, StockTradeService.BUY, 10);
		stockService.recordTrade("TEA",20, StockTradeService.BUY, 10);
		stockService.recordTrade("TEA",20, StockTradeService.BUY, 10);
		stockService.recordTrade("GIN",20, StockTradeService.BUY, 10);
		stockService.recordTrade("GIN",20, StockTradeService.BUY, 10);
		stockService.recordTrade("POP",20, StockTradeService.BUY, 10);
		stockService.recordTrade("POP",20, StockTradeService.BUY, 10);
		stockService.recordTrade("JOE",20, StockTradeService.BUY, 10);
		stockService.recordTrade("JOE",20, StockTradeService.BUY, 10);
		
		Double dv=stockService.allShareIndex();
		//1000000^5=10
		Assert.assertEquals(new Double(Math.pow(100000, 1.0/5)),dv);
		stockService.deleteTrades();
		
	}
	
	
}

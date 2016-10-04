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

public class TestDaoTrade {

static TradeDao tdao;
static StockDao sdao;
	
	@Before
	public void setUp() throws Exception {
		tdao=new TradeDao();		
		sdao=new StockDao();
		
		
	}
	
	
	@Test
	public void testInsertTradeCommon() {
		sdao.init();
		tdao.init();
		Stock s=new CommonStock("TEA", 0, 100) ;
		sdao.addStock(s);
		Date dt=new Date();
		//Inserts a Preferred Stock trade
		Trade t=new Trade(dt, 20, Trade.BUY, 10,s);		
		tdao.addTrade(t);
		Assert.assertEquals(t,tdao.getTradeByStockDate(s, dt));
		sdao.free();
		tdao.free();
		
	}
	
	@Test
	public void testInsertTradePreferred() {
		sdao.init();
		tdao.init();
		Stock s=new PreferredStock("GIN", 8, 100,2.0) ;
		sdao.addStock(s);
		Date dt=new Date();
		//Inserts a Preferred Stock trade
		Trade t=new Trade(dt, 20, Trade.BUY, 10,s);		
		
		tdao.addTrade(t);
		Assert.assertEquals(t,tdao.getTradeByStockDate(s, dt));
		sdao.free();
		tdao.free();
		
	}
	
	
	@Test
	public void testAllTrades() {
		sdao.init();
		tdao.init();
		Stock s1=new PreferredStock("GIN", 8, 100,2.0) ;
		Stock s2=new CommonStock("TEA", 0, 100) ;
		Stock s3=new CommonStock("POP", 8, 100) ;
		
		//Insert 3 Stocks common and preferred
		sdao.addStock(s1);
		sdao.addStock(s2);
		sdao.addStock(s3);
		
		Date dt=new Date();
		
		
		//Insert 3 trades 1 per Stock
		Trade t1=new Trade(dt, 20, Trade.BUY, 10,s1);	
		Trade t2=new Trade(dt, 20, Trade.BUY, 10,s2);
		Trade t3=new Trade(dt, 20, Trade.BUY, 10,s3);
		tdao.addTrade(t1);
		tdao.addTrade(t2);
		tdao.addTrade(t3);
		
		Assert.assertEquals(3,tdao.getAllTrades().size());
		sdao.free();
		tdao.free();
		
	}
	
	
	@Test
	public void testDeleteTrades() {
		sdao.init();
		tdao.init();
		Stock s1=new PreferredStock("GIN", 8, 100,2.0) ;
		Stock s2=new CommonStock("TEA", 0, 100) ;
		Stock s3=new CommonStock("POP", 8, 100) ;
		
		//Insert 3 Stocks common and preferred
		sdao.addStock(s1);
		sdao.addStock(s2);
		sdao.addStock(s3);
		
		Date dt=new Date();
		
		
		//Insert 3 trades 1 per Stock
		Trade t1=new Trade(dt, 20, Trade.BUY, 10,s1);	
		Trade t2=new Trade(dt, 20, Trade.BUY, 10,s2);
		Trade t3=new Trade(dt, 20, Trade.BUY, 10,s3);
		tdao.addTrade(t1);
		tdao.addTrade(t2);
		tdao.addTrade(t3);
		
		
		//Deletes 1 Stock
		tdao.deleteTrade(t2);
		
		Assert.assertEquals(2,tdao.getAllTrades().size());
		sdao.free();
		tdao.free();
		
	}
	
	@Test
	public void testGetTradesByStock() {
		sdao.init();
		tdao.init();
		Stock s1=new PreferredStock("GIN", 8, 100,2.0) ;
		Stock s2=new CommonStock("TEA", 0, 100) ;
		Stock s3=new CommonStock("POP", 8, 100) ;
		
		//Insert 3 Stocks common and preferred
		sdao.addStock(s1);
		sdao.addStock(s2);
		sdao.addStock(s3);
		
		Date dt=new Date();
		
		
		//Insert 4 trades 2 trades for s3->POP
		Trade t1=new Trade(dt, 20, Trade.BUY, 10,s1);	
		Trade t2=new Trade(dt, 20, Trade.BUY, 10,s2);
		Trade t3=new Trade(dt, 20, Trade.BUY, 10,s3);
		Trade t4=new Trade(dt, 20, Trade.BUY, 10,s3);
		tdao.addTrade(t1);
		tdao.addTrade(t2);
		tdao.addTrade(t3);
		tdao.addTrade(t4);
		
		Assert.assertEquals(2,tdao.getTradesByStock(s3).size());
		sdao.free();
		tdao.free();
		
	}

}

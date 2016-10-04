package com.jpm.test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jpm.dao.StockDao;
import com.jpm.entities.CommonStock;
import com.jpm.entities.PreferredStock;
import com.jpm.entities.Stock;

public class TestDaoStock {

	static StockDao sdao;
	
	@Before
	public void setUp() throws Exception {
		sdao=new StockDao();
		
		
	}
	
	
	@Test
	public void testInsertCommon() {
		sdao.init();
		Stock s=new CommonStock("TEA", 0, 100) ;
		
		sdao.addStock(s);
		//Inserts a Preferred stock symbol=TEA Type=Common Last Dividend=0 
		Assert.assertEquals(s,sdao.getStockBySymbol(s.getSymbol()));
		sdao.free();
		
	}
	
	@Test
	public void testInsertPreferred() {
		sdao.init();
		Stock s=new PreferredStock("GIN", 8, 100,2.0) ;
		//Inserts a Preferred stock symbol=GIN Type=Common Last Dividend=8 Fixed dividend=2 
		sdao.addStock(s);
		
		Assert.assertEquals(s,sdao.getStockBySymbol(s.getSymbol()));
		sdao.free();
		
	}
	
	
	@Test
	public void testAllStocks() {
		sdao.init();
		Stock s1=new PreferredStock("GIN", 8, 100,2.0) ;
		Stock s2=new CommonStock("TEA", 0, 100) ;
		Stock s3=new CommonStock("POP", 8, 100) ;
		
		//Insert 3 Stocks common and preferred
		sdao.addStock(s1);
		sdao.addStock(s2);
		sdao.addStock(s3);
		
		Assert.assertEquals(3,sdao.getAllStocks().size());
		sdao.free();
	}
	
	
	@Test
	public void testDeleteStocks() {
		sdao.init();
		Stock s1=new PreferredStock("GIN", 8, 100,2.0) ;
		Stock s2=new CommonStock("TEA", 0, 100) ;
		Stock s3=new CommonStock("POP", 8, 100) ;
		
		//Insert 3 Stocks common and preferred
		sdao.addStock(s1);
		sdao.addStock(s2);
		sdao.addStock(s3);
		
		//Deletes 1 Stock
		sdao.deleteStock(s1);
		
		Assert.assertEquals(2,sdao.getAllStocks().size());
		sdao.free();
		
	}

}

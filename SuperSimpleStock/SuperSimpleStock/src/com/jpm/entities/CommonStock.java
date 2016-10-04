package com.jpm.entities;

public class CommonStock extends Stock{
	
		
	public CommonStock(String symbol, double lastDividend, double parValue) {
		super(symbol,lastDividend,parValue,null,"Common");
		
	}
	
	
	public double dividendYeldFunction(double price){
		if(price!=0){		
			return (this.lastDividend/price);
		}
		return -1;
	}
}

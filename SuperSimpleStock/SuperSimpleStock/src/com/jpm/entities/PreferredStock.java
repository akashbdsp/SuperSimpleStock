package com.jpm.entities;

public class PreferredStock extends Stock{
		
	
	public PreferredStock(String symbol, double lastDividend, double parValue,
			Double fixedDividend) {
		super(symbol,lastDividend,parValue,fixedDividend,"Preferred");
		
	}
	
	
	public double dividendYeldFunction(double price){
		if(price!=0){
			
			return ((this.fixedDividend*this.parValue)/price);
		}
		return -1;
	}
	
}

package com.jpm.entities;

public abstract class Stock {
	
	protected String Symbol;
	protected double lastDividend;
	protected double parValue;
	protected Double fixedDividend;
	protected String type;
	
		
	public Stock(String symbol, double lastDividend, double parValue,
			Double fixedDividend, String type) {
		super();
		Symbol = symbol;
		this.lastDividend = lastDividend;
		this.parValue = parValue;
		this.fixedDividend = fixedDividend;
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSymbol() {
		return Symbol;
	}
	public void setSymbol(String symbol) {
		Symbol = symbol;
	}
	public double getLastDividend() {
		return lastDividend;
	}
	public void setLastDividend(double lastDividend) {
		this.lastDividend = lastDividend;
	}
	public double getParValue() {
		return parValue;
	}
	public void setParValue(double parValue) {
		this.parValue = parValue;
	}
	
	public Double getFixedDividend() {
		return null;
	}
	
	public void setFixedDividend(Double fixedDividend) {
		//Does nothing
	}
	
	public boolean equals(Stock s){
		return (this.Symbol.equals(s.getSymbol()));
	}
	abstract public double dividendYeldFunction(double price);
	

}

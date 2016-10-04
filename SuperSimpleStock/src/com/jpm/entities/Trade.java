package com.jpm.entities;

import java.util.Date;



public class Trade {
	
	static public int SELL=0;
	static public int BUY=0;
	
	public Trade(Date timestamp, int quantity, int indicator, double price,
			Stock stock) {
		super();
		this.timestamp = timestamp;
		this.quantity = quantity;
		this.indicator = indicator;
		this.price = price;
		this.stock = stock;
	}

	
	
	
	private Date timestamp;
	private int quantity;
	private int indicator;
	private double price;
	private Stock stock;

	
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getIndicator() {
		return indicator;
	}
	public void setIndicator(int indicator) {
		this.indicator = indicator;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public boolean equals(Trade t){
		return (this.stock.equals(t.getStock()) && this.timestamp.equals(t.getTimestamp()));
	}
}

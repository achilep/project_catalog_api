package com.catalog.com.imagemanipulation.service;

public class ProductDTOPostRequest {

	private String name;
	private long quantity;
	private long price;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	
	public ProductDTOPostRequest(String name, long quantity, long price) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}
}

package com.catalog.com.dto;


public class ProductDTO {

	
	private int id;
	private String name;
	private long quantity;
	private long price;
	
	public ProductDTO(int id, String name, long quantity, long price) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}
	
	public ProductDTO() {
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
}

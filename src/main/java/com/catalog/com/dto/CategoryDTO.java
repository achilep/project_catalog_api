package com.catalog.com.dto;

import java.util.List;

import com.catalog.com.models.Product;

public class CategoryDTO {
	private int id;
	private String name;
	List<Product> products;
    public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public CategoryDTO() {
		}
	public CategoryDTO(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		
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
	
	
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}
	


}

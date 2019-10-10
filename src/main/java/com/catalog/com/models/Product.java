package com.catalog.com.models;
import javax.persistence.*;
import java.sql.Blob;

@Entity
public class Product {
	@Id
	@GeneratedValue
	private int id ; @ManyToOne(fetch=FetchType.LAZY)
	private Category category; 

	public Product() {
		
	}
	
}

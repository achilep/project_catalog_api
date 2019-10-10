package com.catalog.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catalog.com.dto.ProductDTO;
import com.catalog.com.models.Product;
import com.catalog.com.services.ProductServiceImpl;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductController {
	
	private ProductServiceImpl service;
	
	@Autowired
	public ProductController(ProductServiceImpl service) {
		this.service=service;
	}

	@GetMapping("/here")
	public String test() {
		return "working";
	}
	//save a new product
	@PostMapping("/category/{category}")
	public ResponseEntity<ProductDTO> saveProduct(@RequestBody Product product) {
		
		ResponseEntity<ProductDTO> response = new ResponseEntity<ProductDTO>(service.addProduct(product), 
				HttpStatus.CREATED);
		return response;
	}
	
	//modify an existing product
	@PutMapping("/{productid}/category/{categoryid}")
	public ResponseEntity<ProductDTO> editProduct(@RequestBody Product product) {
		ResponseEntity<ProductDTO> response = new ResponseEntity<ProductDTO>(service.editProduct(product),
				HttpStatus.NO_CONTENT);
		
		return response;
	}
}

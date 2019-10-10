package com.catalog.com.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalog.com.dto.ProductDTO;
import com.catalog.com.models.Product;
import com.catalog.com.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	private ProductRepository repository;
	
	@Autowired
	public ProductServiceImpl(ProductRepository repository) {
		this.repository= repository;
	}

	@Override
	public ProductDTO addProduct(Product product) {
		return repository.save(product).toDTO();
	}

	@Override
	public ProductDTO editProduct(Product product) {
		return repository.save(product).toDTO();
	}

	@Override
	public void deleteProduct(int productid) {
		repository.deleteById(productid);
	}

	@Override
	public List<ProductDTO> retrieveAllProducts() {
		List<ProductDTO> products = new ArrayList<ProductDTO>();
		
		repository.findAll().forEach(product -> products.add(product.toDTO()));
		return products;
	}

}

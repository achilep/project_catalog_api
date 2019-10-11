package com.catalog.com.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalog.com.dto.ProductDTO;
import com.catalog.com.models.Product;
import com.catalog.com.repositories.CategoryRepository;
import com.catalog.com.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	private ProductRepository repository;
	private CategoryRepository catRepo;
	
	@Autowired
	public ProductServiceImpl(ProductRepository repository, CategoryRepository catRepo) {
		this.repository= repository;
		this.catRepo = catRepo;
	}

	@Override
	public ProductDTO addProduct(Product product, int categoryid) {
		
		product.setCategory(catRepo.findById(categoryid).get());
		return repository.save(product).toDTO();
	}

	@Override
	public ProductDTO editProduct(Product product, int productid, int categoryid) {
		
		product.setCategory(catRepo.findById(categoryid).get());
		product.setId(productid);
		
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

	@Override
	public ProductDTO retrieveProduct(int productid) {
		return repository.findById(productid).get().toDTO();
	}

}

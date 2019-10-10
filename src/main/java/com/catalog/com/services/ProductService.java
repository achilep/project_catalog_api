package com.catalog.com.services;

import java.util.List;

import com.catalog.com.dto.ProductDTO;
import com.catalog.com.models.Product;

public interface ProductService {

	ProductDTO addProduct(Product product);

	ProductDTO editProduct(Product product);

	void deleteProduct(int productid);

	List<ProductDTO> retrieveAllProducts();

	ProductDTO retrieveProduct(int productid);

}

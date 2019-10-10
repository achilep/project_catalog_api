package com.catalog.com.services;

import com.catalog.com.dto.ProductDTO;
import com.catalog.com.models.Product;

public interface ProductService {

	ProductDTO addProduct(Product product);

	ProductDTO editProduct(Product product);

}

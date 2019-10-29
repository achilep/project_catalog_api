package com.catalog.com.services;

import java.util.List;

import com.catalog.com.dto.StandardProductDTO;
import com.catalog.com.models.Product;

public interface ProductService {

	void deleteProduct(int productid);

	List<StandardProductDTO> retrieveAllProducts();

	StandardProductDTO retrieveProduct(int productid);
	

	StandardProductDTO addProduct(Product product, int categoryid);

	StandardProductDTO editProduct(Product product, int productid, int categoryid);

	StandardProductDTO editProduct(int productid, String imageLink);

}

package com.catalog.services;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.category.product.dto.ProductDTO;
import com.category.product.models.Product;
import com.category.product.repositories.ProductRepository;

@Service
public class ProductService {
	@Autowired
    private ProductRepository productrepos;
    
    
   // @Override
    public List<ProductDTO> generate() {
        Iterable<Product> products = productrepos.findAll();
        List<ProductDTO> productdtos = new ArrayList<>();
        for(Product category : products) {
            ProductDTO categorytdo = new ProductDTO();
            categorytdo.setDescription(category.getDescription());
            categorytdo.setName(category.getName());
            categorytdo.setImage(category.getImage());
            productdtos.add(categorytdo);
        }
        return productdtos;
    }
    public ResponseEntity<URI> saveproduct(ProductDTO productdto) {
    	productrepos.save(productdto);
    	URI location = ServletUriComponentsBuilder
    			.fromCurrentRequest()
    			.path("/{id}")
    			.buildAndExpand(productdto.getId()).toUri();
    		
    		return ResponseEntity.created(location).build();
    }

}

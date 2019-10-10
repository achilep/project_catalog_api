package com.catalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.category.product.dto.ProductDTO;
import com.category.product.services.ProductService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AllProduct {
	@Autowired
    private ProductService productservice;

   /* @Autowired
    public ContactsController(ContactService contactService) {
        this.contactService = contactService;
    }*/
    @GetMapping("/product")
    public ResponseEntity<List<ProductDTO>> generateCategory(){
        return ResponseEntity.ok(productservice.generate());
    }
    @PostMapping("/product/save/{productdto}")
    public ResponseEntity<URI> saveproduct( @RequestBody ProductDTO productdto){
        return productservice.saveproduct(productdto);
        		
    }
	

}

package com.catalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.category.product.dto.CategoryDTO;
import com.category.product.services.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AllCategory {
	@Autowired
    private CategoryService categoryservice;

   /* @Autowired
    public ContactsController(ContactService contactService) {
        this.contactService = contactService;
    }*/

    @GetMapping("/category")
    public ResponseEntity<List<CategoryDTO>> generateCategory(){
        return ResponseEntity.ok(categoryservice.generate());
    }
	

}

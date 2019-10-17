package com.catalog.com.services;

import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.catalog.com.dto.CategoryDTO;
import com.catalog.com.models.Category;

public interface CategoryInterface {
	  
	List<CategoryDTO>getallcategroy();
	CategoryDTO getparticularcategory(int id);
	void deletecategory(int categoryId);
	ResponseEntity<Object> creatcategory(CategoryDTO categorydto);
	void updatecategory( int categoryId, CategoryDTO categorydto);
	//CategoryDTO getparticularcategor(int categoryId);

}

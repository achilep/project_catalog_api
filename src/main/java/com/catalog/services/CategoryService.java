package com.catalog.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.category.product.models.Category;
import com.category.product.repositories.CategoryRepository;
import com.category.product.dto.CategoryDTO;
@Service
public class CategoryService {
	@Autowired
    private CategoryRepository categoryrepos;
    
    
   // @Override
    public List<CategoryDTO> generate() {
        Iterable<Category> categorys = categoryrepos.findAll();
        List<CategoryDTO> categorydtos = new ArrayList<>();
        for(Category category : categorys) {
            CategoryDTO categorytdo = new CategoryDTO();
            categorytdo.setDescription(category.getDescription());
            categorytdo.setName(category.getName());
            
            categorydtos.add(categorytdo);
        }
        return categorydtos;
    }

}

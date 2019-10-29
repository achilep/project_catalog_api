package com.catalog.com.services;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.catalog.com.dto.CategoryDTO;
import com.catalog.com.exceptions.category.CategoryNotFound;
import com.catalog.com.exceptions.category.UncaughtCategory;
import com.catalog.com.exceptions.category.CategoryNotDelete;
import com.catalog.com.models.Category;
import com.catalog.com.models.Product;
import com.catalog.com.repositories.CategoryRepository;
import com.catalog.com.repositories.ProductRepository;
@Service
public class CategoryServiceImple implements CategoryInterface {
	
	
    private CategoryRepository categoryrepos;
    private ProductRepository productrepository;
    
    @Autowired
    public CategoryServiceImple(CategoryRepository categoryrepos,ProductRepository productrepository) {
		super();
		this.categoryrepos = categoryrepos;
		this.productrepository= productrepository;
	}


	@Override
    public List<CategoryDTO>getallcategroy() {
        List<CategoryDTO> categorydtos;
		try {
			Iterable<Category> categorys = categoryrepos.findAll();
			categorydtos = new ArrayList<>();
			for(Category category : categorys) {
			    CategoryDTO categorytdo = new CategoryDTO();
			    categorytdo.setId(category.getId());
			    List <Product> products= productrepository.findByCategoryId(category.getId());
			    categorytdo.setName(category.getName());
			    
			    categorytdo.setProducts(products);
			    categorydtos.add(categorytdo);
			}
		 } catch (Exception e) {
			// TODO Auto-generated catch block
			throw new UncaughtCategory("no category found because of -" + e);
			
		}
		
        
        return categorydtos;
    }
	@Override
	public CategoryDTO getparticularcategory(int categoryId) {
		Optional<Category> category= categoryrepos.findById(categoryId);
		if(!category.isPresent()) {
			throw new CategoryNotFound("cannot find the category of id-" + categoryId);
		}
		CategoryDTO categorydto=new CategoryDTO();
		categorydto.setId(category.get().getId());
		categorydto.setName(category.get().getName());


		return categorydto;
		
	}

	@Override
	public void deletecategory(int categoryId) {
		try {
			categoryrepos.deleteById(categoryId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new CategoryNotDelete("category not be delete becauese of-" + e);
		}
		
	}
	@Override
	public  ResponseEntity<Object> creatcategory(CategoryDTO categorydto) {
		URI location=null;
		
			try {
				Category category= new Category();
				category.setName(categorydto.getName());
				Category categorysave=categoryrepos.save(category);
				
				 location = ServletUriComponentsBuilder.fromCurrentRequest()
						 .path("/{id}").buildAndExpand(categorysave.getId())
					      .toUri();
			} catch (Exception e) {
				throw new UncaughtCategory(" category no add because of -" + e);
			
			}

			 return ResponseEntity.created(location).build();


				
	}
	@Override
	public void updatecategory( int categoryId, CategoryDTO categorydto){
		
		try {
			Category category= categoryrepos.findById(categoryId).get();
			category.setName(categorydto.getName());
			Category categoryupdate=categoryrepos.save(category);
		} catch (Exception e) {
			throw new UncaughtCategory(" category no update because of -" + e);
			
		}
		
    	 


	}


}

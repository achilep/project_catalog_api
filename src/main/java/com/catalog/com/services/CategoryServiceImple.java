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
import com.catalog.com.repositories.CategoryRepository;
@Service
public class CategoryServiceImple implements CategoryInterface {
	
	
    private CategoryRepository categoryrepos;
    
    @Autowired
    public CategoryServiceImple(CategoryRepository categoryrepos) {
		super();
		this.categoryrepos = categoryrepos;
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
			    
			    categorytdo.setName(category.getName());
			    
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
				// TODO Auto-generated catch block
			
			}

			 return ResponseEntity.created(location).build();


				
	}
	@Override
	public void updatecategory( int categoryId, CategoryDTO categorydto){
		//URI location;
		
		try {
			Category category= categoryrepos.findById(categoryId).get();
//		if(!category.isPresent()) {
//			throw new CategoryNotFound("category of id-" + categoryId+"cannot be found");
//		}
			category.setName(categorydto.getName());
			Category categoryupdate=categoryrepos.save(category);
		} catch (Exception e) {
			throw new UncaughtCategory(" category no update because of -" + e);
			// TODO Auto-generated catch block
			
		}
		
//		CategoryDTO categorydto=new CategoryDTO();
//		categorydto.setId(category.get().getId());
//		categorydto.setName(category.get().getName());
    	 


	}


}

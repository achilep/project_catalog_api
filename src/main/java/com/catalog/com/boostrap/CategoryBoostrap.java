package com.catalog.com.boostrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.catalog.com.dto.CategoryDTO;
import com.catalog.com.repositories.CategoryRepository;

@Component
public class CategoryBoostrap implements ApplicationListener<ContextRefreshedEvent> {
	private CategoryRepository categoryrepos;
	@Autowired
	public CategoryBoostrap(CategoryRepository categoryrepos) {
		super();
		this.categoryrepos = categoryrepos;
	}
	@Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData(10);
    }

    private void initData(int count) {
       for(int i = 0; i < count; i++){
    	   String name = "aladin " + i;
            CategoryDTO categorydto = new CategoryDTO();
            categorydto.setName(name);
            
             categoryrepos.save(categorydto);
            
       }   
    }



}

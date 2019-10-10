package com.catalog.boostrap;


import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.category.product.models.Category;
import com.category.product.repositories.CategoryRepository;

@Component
public class BoostrapCategory implements ApplicationListener<ContextRefreshedEvent>{
	@Autowired
    private CategoryRepository categoryrepos;
	
    
	/*@Autowired
	public BoostrapCategory(CategoryRepository categoryrepos) {
		super();
		this.categoryrepos = categoryrepos;
	}*/

	@Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData(10);
    }

    private void initData(int count) {
       for(int i = 0; i < count; i++){
            String description = "descrpition"+i+".defult";
            
            String name = "aladin " + i;
            Category category = new Category();
            category.setName(name);
            category.setDescription(description);
            
             categoryrepos.save(category);
            
       }   
    }

}

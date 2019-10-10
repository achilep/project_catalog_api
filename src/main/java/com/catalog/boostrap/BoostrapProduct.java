package com.catalog.boostrap;


import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.category.product.models.Category;
import com.category.product.models.Product;
import com.category.product.repositories.ProductRepository;

@Component
public class BoostrapProduct implements ApplicationListener<ContextRefreshedEvent>{
	/*@Autowired
	public BoostrapProduct(ProductRepository product) {
		super();
		this.product = product;
	}*/

	@Autowired
    private ProductRepository productrepos;
    

	@Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData(10);
    }

    private void initData(int count) {
       for(int i = 0; i < count; i++){
            String description = "descrpition"+i+".defult";
            long price = (1342+i) ;
            String name = "aladin " + i;
            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            
             productrepos.save(product);
        }
    }

}

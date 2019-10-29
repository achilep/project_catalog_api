package com.catalog.com.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalog.com.dto.StandardProductDTO;
import com.catalog.com.exceptions.product.ProductNotDeletedException;
import com.catalog.com.exceptions.product.ProductNotFoundException;
import com.catalog.com.exceptions.product.UncaughtProductException;
import com.catalog.com.models.Product;
import com.catalog.com.repositories.CategoryRepository;
import com.catalog.com.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository repository;
	private CategoryRepository catRepo;

	@Autowired
	public ProductServiceImpl(ProductRepository repository, CategoryRepository catRepo) {
		this.repository = repository;
		this.catRepo = catRepo;
	}

	@Override
	public StandardProductDTO addProduct(Product product, int categoryid) {

		product.setCategory(catRepo.findById(categoryid).get());
		return repository.save(product).toStandardProductDTO();
	}

	@Override
	public StandardProductDTO editProduct(Product product, int productid, int categoryid) {
		// verify that the given product exists
		
		if (!repository.existsById(productid))
			throw new ProductNotFoundException("Product id: " + productid); // if not exists, throw new product not
																			// found exception

		// verify that the given category exists
		if (!catRepo.existsById(categoryid))
			throw new UncaughtProductException("Category not found, id: " + categoryid); // if not exists, throw
																							// category not found
																							// exception

		product.setCategory(catRepo.findById(categoryid).get()); // if all is well, set the productid and category id
		product.setId(productid);
		product.setImage(repository.findById(productid).get().getImage());

		return repository.save(product).toStandardProductDTO(); // effect the modification
	}

	@Override
	public void deleteProduct(int productid) {

		// verify that the product exists
		if (!repository.existsById(productid))
			throw new ProductNotFoundException("Product id: " + productid); // throw a product not found exception

		repository.deleteById(productid); // attempt to delete the product

		if (repository.existsById(productid)) // verify whether the product was deleted
			throw new ProductNotDeletedException("Product id:" + productid); // throw a product not deleted exception if
																				// it was not
	}

	@Override
	public List<StandardProductDTO> retrieveAllProducts() {
		List<StandardProductDTO> products = new ArrayList<StandardProductDTO>();

		//for each retrieved product, add to a listof products to be returned
		repository.findAll().forEach(product -> {
			products.add(product.toStandardProductDTO());
		});
		return products;
	}

	@Override
	public StandardProductDTO retrieveProduct(int productid) {

		Optional<Product> result = repository.findById(productid);

		return result.get().toStandardProductDTO();
	}

	@Override
	public StandardProductDTO editProduct(int productid, String imageLink) {
		//verify that product exists
		if(repository.existsById(productid)) {
			Product product = repository.findById(productid).get();
			
			product.setImage(imageLink);
			
			return repository.save(product).toStandardProductDTO();
		}
		//if the product does not exist, throw product not found exception
		throw new ProductNotFoundException(String.format("Product id: %d", productid));
	
	}

}

package com.catalog.com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.catalog.com.dto.StandardProductDTO;
import com.catalog.com.imagemanipulation.service.ImageManipulationService;
import com.catalog.com.imagemanipulation.service.ProductDTOPostRequest;
import com.catalog.com.models.Product;
import com.catalog.com.services.ProductServiceImpl;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductController {
	
	private ImageManipulationService fileStorageService;
	private ProductServiceImpl service;
	
	@Autowired
	public ProductController(ImageManipulationService fileStorageService,
			ProductServiceImpl service) {
		this.fileStorageService = fileStorageService;
		this.service = service;
	}

	@GetMapping("/load/{fileName:.+}")
	private ResponseEntity<Resource> loadImage(@PathVariable String fileName, HttpServletRequest request) {
		return fileStorageService.downloadFile(fileName, request);
	}
	
	// save a new product
	@PostMapping("/category/{categoryid}")
	public ResponseEntity<ProductDTOPostRequest> saveProduct(

			@RequestPart MultipartFile file,

			@RequestParam() String name, @RequestParam() long quantity, @RequestParam() long price,
			@PathVariable("categoryid") int categoryid) {

		Product product = new Product();
		product.setName(name);
		product.setQuantity(quantity);
		product.setPrice(price);

		// save the product (without the image)
		StandardProductDTO result = service.addProduct(product, categoryid);
		// save the corresponding image and retrieve its download url

		String fileDownloadUri = fileStorageService.getDownloadUrlFor(file, result.getId());

		product.setImage(fileDownloadUri);
		result = service.editProduct(product, product.getId(), categoryid);

		ResponseEntity<ProductDTOPostRequest> response = new ResponseEntity<ProductDTOPostRequest>(result.toPostDTO(), HttpStatus.CREATED);

		return response;
	}

	// modify an existing product
	@PutMapping("/{productid}/category/{categoryid}")
	public ResponseEntity<StandardProductDTO> editProduct(@RequestBody Product product,
			@PathVariable("productid") int productid, @PathVariable("categoryid") int categoryid) {

		ResponseEntity<StandardProductDTO> response = new ResponseEntity<StandardProductDTO>(
				service.editProduct(product, productid, categoryid), HttpStatus.CREATED);

		return response;
	}

	//modify an existing product's image
	@PutMapping("/{productid}")
	public ResponseEntity<StandardProductDTO> editProductImage(@RequestPart MultipartFile productimage,
			@PathVariable("productid") int productid) {

		String imageLink = fileStorageService.getDownloadUrlFor(productimage, productid);
		
		ResponseEntity<StandardProductDTO> response = new ResponseEntity<StandardProductDTO>(
				service.editProduct(productid, imageLink), HttpStatus.CREATED);

		return response;
	}
	
	// delete a product
	@DeleteMapping("/{productid}")
	public ResponseEntity<Object> deleteProduct(@PathVariable("productid") int productid) {
		service.deleteProduct(productid);

		ResponseEntity<Object> response = new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		return response;
	}

	// view all products
	@GetMapping("/")
	public ResponseEntity<List<StandardProductDTO>> getAllProducts() {
		ResponseEntity<List<StandardProductDTO>> response = new ResponseEntity<List<StandardProductDTO>>(service.retrieveAllProducts(),
				HttpStatus.OK);

		return response;
	}

	// view a single product
	@GetMapping("/{productid}")
	public ResponseEntity<StandardProductDTO> getSingleProduct(@PathVariable("productid") int productid) {
		
		
		
		ResponseEntity<StandardProductDTO> response = new ResponseEntity<StandardProductDTO>(service.retrieveProduct(productid),
				HttpStatus.OK);

		return response;
	}

	
}

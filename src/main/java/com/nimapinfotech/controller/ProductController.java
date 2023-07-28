package com.nimapinfotech.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nimapinfotech.dto.ProductDto;
import com.nimapinfotech.dto.ProductPagingResponse;
import com.nimapinfotech.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

	private ProductService productservice;

	public ProductController(ProductService productservice) {
		this.productservice = productservice;
	}

	// getAllproducts
	@GetMapping("/products")
	public ResponseEntity<ProductPagingResponse> getAllProduct(
			@RequestParam(value = "page", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "3", required = false) int pageSize)

	{
		ProductPagingResponse allProduct = productservice.viewAllProduct(pageNo, pageSize);
		return new ResponseEntity<>(allProduct, HttpStatus.OK);
	}

	// create new product
	@PostMapping("/products")
	public ResponseEntity<String> createProduct(@RequestBody ProductDto ProductDto) {
		boolean flag = productservice.addProduct(ProductDto);
		String msg = "";
		if (flag) {
			msg = " product created successfully";
		} else {
			msg = " product creation fail";
		}
		return new ResponseEntity<String>(msg, HttpStatus.CREATED);

	}
	
	// get products by id-> send invalid id and see result
	@GetMapping("/products/{id}")
	public ResponseEntity<ProductDto> getCategoryById(@PathVariable("id") Integer id) {
		return new ResponseEntity<>(productservice.getProductById(id), HttpStatus.OK);
	}

	// update product by id
	@PutMapping("/products/{id}")
	public ResponseEntity<String> updateProduct(@PathVariable("id") Integer id, @RequestBody ProductDto ProductDtoo) {
		boolean flag = productservice.updateProductById(id, ProductDtoo);
		String msg = "";
		if (flag) {
			msg = "product updated successfully";
		} else {
			msg = "update fails";
		}
		return new ResponseEntity<String>(msg, HttpStatus.CREATED);
	}


	// delete product by id
	@DeleteMapping("/products/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") Integer id) {
		productservice.deleteProductById(id);
		return new ResponseEntity<String>("record deleted successfully", HttpStatus.OK);
	}
}

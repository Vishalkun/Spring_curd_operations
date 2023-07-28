package com.nimapinfotech.service;

import com.nimapinfotech.dto.ProductDto;
import com.nimapinfotech.dto.ProductPagingResponse;

public interface ProductService {

	// GET all the products;
	public ProductPagingResponse viewAllProduct(int pageNo, int pageSize);

	// POST - create a new product
	public boolean addProduct(ProductDto productDto);

	// GET product by Id
	public ProductDto getProductById(Integer id);

	// PUT - update product by id
	public boolean updateProductById(Integer id, ProductDto productDto);

	// DELETE - Delete product by id
	public void deleteProductById(Integer id);

}

package com.nimapinfotech.serviceImpl;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.nimapinfotech.dto.ProductDto;
import com.nimapinfotech.dto.ProductPagingResponse;
import com.nimapinfotech.entity.Product;
import com.nimapinfotech.exception.ResourceNotFoundException;
import com.nimapinfotech.repository.CategoryRepo;
import com.nimapinfotech.repository.ProductRepo;
import com.nimapinfotech.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private ModelMapper mapper;

	private ProductRepo productRepo;

	public ProductServiceImpl(CategoryRepo repository, ModelMapper mapper, ProductRepo productrepo) {
		this.mapper = mapper;
		this.productRepo = productrepo;
	}

	public ProductPagingResponse viewAllProduct(int pageNo, int pageSize) {

		PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
		Page<Product> page = productRepo.findAll(pageRequest);
		List<Product> contents = page.getContent();
		List<ProductDto> list = contents.stream().map((content) -> mapToDTO(content)).collect(Collectors.toList());

		ProductPagingResponse productResponse = new ProductPagingResponse();
		productResponse.setProductDto(list);
		productResponse.setPageNo(page.getNumber());
		productResponse.setPageSize(page.getSize());
		productResponse.setTotalElements(page.getTotalElements());
		productResponse.setTotalPages(page.getTotalPages());
		productResponse.setLast(page.isLast());
		return productResponse;
	}

	@Override
	public boolean addProduct(ProductDto productDto) {
		Product product = mapToEntity(productDto);
		productRepo.save(product);
		return product.getProductId() != null;
	}

	@Override
	public ProductDto getProductById(Integer id) {
		Product product = productRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("product", "id", id));

		return mapToDTO(product);
	}

	@Override
	public boolean updateProductById(Integer id, ProductDto productDto) {
		Product product = productRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("product", "id", id));
		product.setProductName(productDto.getProductName());
		product.setProductPrice(productDto.getProductPrice());
		productRepo.save(product);

		return product.getProductId() != null;
	}

	@Override
	public void deleteProductById(Integer id) {
		Product product = productRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("product", "id", id));
		productRepo.delete(product);
	}

	// convert Entity into DTO
	private ProductDto mapToDTO(Product product) {
		ProductDto productDto = mapper.map(product, ProductDto.class);
		return productDto;
	}

	// convert DTO to entity
	private Product mapToEntity(ProductDto productdto) {
		Product product = mapper.map(productdto, Product.class);
		return product;
	}

}

package com.nimapinfotech.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.nimapinfotech.dto.CategoryDto;
import com.nimapinfotech.entity.Category;
import com.nimapinfotech.exception.ResourceNotFoundException;
import com.nimapinfotech.repository.CategoryRepo;
import com.nimapinfotech.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepo repository;

	private ModelMapper mapper;
	private static Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

	public CategoryServiceImpl(CategoryRepo repository, ModelMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public List<CategoryDto> getAllCategories(int pageNo, int pageSize) {

		PageRequest pageRequest = PageRequest.of(pageNo, pageSize);

		Page<Category> page = repository.findAll(pageRequest);
		List<Category> content = page.getContent();
		List<CategoryDto> categoryDto = content.stream().map(categorie -> mapToDTO(categorie))
				.collect(Collectors.toList());
		return categoryDto;
	}

	// create
	@Override
	public boolean upsert(CategoryDto categorydto) {
		Category category = mapToEntity(categorydto);
		repository.save(category);
		return category.getCategoryId() != null;
	}

	// update
	@Override
	public CategoryDto updatePostById(Integer id, CategoryDto categorydto) {
		Category category = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
		category.setCategoryTitle(categorydto.getCategoryTitle());
		category.setCategoryDescription(categorydto.getCategoryDescription());
		repository.save(category);
		return mapToDTO(category);
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		Category category = repository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
		return mapToDTO(category);
	}

	@Override
	public void deleteCategoryById(Integer id) {
		Category category = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
		repository.delete(category);

	}

	// convert Entity into DTO
	private CategoryDto mapToDTO(Category category) {
		CategoryDto categoryDto = mapper.map(category, CategoryDto.class);
		return categoryDto;
	}

	// convert DTO to entity
	private Category mapToEntity(CategoryDto categoryDto) {
		Category category = mapper.map(categoryDto, Category.class);
		return category;
	}

}

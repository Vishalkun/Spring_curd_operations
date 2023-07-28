package com.nimapinfotech.service;

import java.util.List;

import com.nimapinfotech.dto.CategoryDto;

public interface CategoryService {

	// GET all the categories
	public List<CategoryDto> getAllCategories(int pageNo, int pageSize);

	// create new plan as well as update category
	public boolean upsert(CategoryDto categorydto);

	// GET category by Id
	public CategoryDto getCategoryById(Integer categoryId);

	// DELETE - Delete category by id 
	// hard delete
	public void deleteCategoryById(Integer id);

	//update 
	CategoryDto updatePostById(Integer id, CategoryDto categorydto);

}

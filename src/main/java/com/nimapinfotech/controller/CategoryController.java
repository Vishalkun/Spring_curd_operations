package com.nimapinfotech.controller;

import java.util.List;

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

import com.nimapinfotech.dto.CategoryDto;
import com.nimapinfotech.service.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryController {

	private CategoryService service;

	public CategoryController(CategoryService service) {
		this.service = service;
	}

	// create
	@PostMapping("/categories")
	public ResponseEntity<String> createCategory(@RequestBody CategoryDto categoryDto) {
		boolean flag = service.upsert(categoryDto);
		String msg = "";
		if (flag) {
			msg = "category created successfully";
		} else {
			msg = "category creation fail";
		}
		return new ResponseEntity<String>(msg, HttpStatus.CREATED);

	}

	// update
	@PutMapping("/categories/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@PathVariable("id") Integer id,
			@RequestBody CategoryDto categoryDto) {
		CategoryDto updatePostById = service.updatePostById(id, categoryDto);
		return new ResponseEntity<CategoryDto>(updatePostById, HttpStatus.OK);
	}

	// getAllCategories
	@GetMapping("/categories")
	public ResponseEntity<List<CategoryDto>> getAllCategorys(
			@RequestParam(value = "page", defaultValue ="0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize)
	
	{
		List<CategoryDto> list = service.getAllCategories(pageNo,pageSize);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	// get category by id-> send invalid id and see result
	@GetMapping("/categories/{id}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") Integer id) {
		CategoryDto categoryById = service.getCategoryById(id);
		return new ResponseEntity<>(categoryById, HttpStatus.OK);
	}

	// delete category by id
	@DeleteMapping("/categories/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") Integer id) {
		service.deleteCategoryById(id);
		return new ResponseEntity<String>("record deleted successfullt", HttpStatus.OK);
	}

}

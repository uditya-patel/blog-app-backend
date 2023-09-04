package com.blogApp.service;

import java.util.List;

import com.blogApp.dto.CategoryDto;

public interface CategoryService {

	CategoryDto addCategory(CategoryDto categoryDto);

	CategoryDto getCategory(Long id);

	List<CategoryDto> getAllCategories();

	CategoryDto updateCategory(Long id, CategoryDto categoryDto);

	void deleteCategory(Long id);
	
	

}

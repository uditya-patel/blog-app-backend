package com.blogApp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogApp.dto.CategoryDto;
import com.blogApp.dto.CommentDto;
import com.blogApp.entity.Category;
import com.blogApp.exception.ResourceNotFoundException;
import com.blogApp.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public CategoryDto addCategory(CategoryDto categoryDto) {
		
		Category category = mapper.map(categoryDto, Category.class);
		
		 Category savedCategory = categoryRepository.save(category);
		
		return mapper.map(savedCategory, CategoryDto.class);
		
	}

	@Override
	public CategoryDto getCategory(Long id) {
		
		Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", id));
		
		return mapper.map(category, CategoryDto.class);
		
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		
		List<Category> categories = categoryRepository.findAll();
		
		return categories.stream().map(category -> mapper.map(category, CategoryDto.class)).collect(Collectors.toList());
		
	}

	@Override
	public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
		
		Category category =  categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", id));
		
		category.setName(categoryDto.getName());
		category.setDescription(categoryDto.getDescription());
		
		Category updatedCategory = categoryRepository.save(category);
		
		return mapper.map(updatedCategory, CategoryDto.class);
		
	
	}

	@Override
	public void deleteCategory(Long id) {
		
		Category  category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", id));
		
		categoryRepository.delete(category);
		
	}

	

}

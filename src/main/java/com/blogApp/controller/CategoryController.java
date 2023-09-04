package com.blogApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogApp.dto.CategoryDto;
import com.blogApp.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto) {
		
		return new ResponseEntity<>(categoryService.addCategory(categoryDto), HttpStatus.CREATED);
		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") Long id) {
		
		return new ResponseEntity<>(categoryService.getCategory(id), HttpStatus.OK);
		
	}
	
	@GetMapping("/allCategories")
	public ResponseEntity<List<CategoryDto>> getAllCategories() {
		
		return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
	}
	
	@PutMapping("/{id}/update")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CategoryDto> updateCategory(@PathVariable("id") Long id, @RequestBody CategoryDto categoryDto) {
		
		return new ResponseEntity<>(categoryService.updateCategory(id, categoryDto), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id) {
		
		categoryService.deleteCategory(id);
		
		return ResponseEntity.ok("category deleted successfully");
		
	}
	
	

}

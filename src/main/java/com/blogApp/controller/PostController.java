package com.blogApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogApp.dto.PostDto;
import com.blogApp.dto.PostResponse;
import com.blogApp.service.PostService;
import com.blogApp.utils.AppConstants;

@RestController
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping("/post")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
		
		return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
		
	}
	
	@GetMapping("/posts")
	public PostResponse getAllPosts(@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
									@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize, 
									@RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
									@RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
									) {
		
		return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long id) {
		
		return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
		
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable(name = "id") Long id) {
		
		return new ResponseEntity<>(postService.updatePost(postDto, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePost(@PathVariable(name = "id") Long id) {
		
		postService.deletePost(id);
		return new ResponseEntity<>("post deleted successfully", HttpStatus.OK);
		
	}
}

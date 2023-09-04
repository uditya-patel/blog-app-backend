package com.blogApp.service;

import java.util.List;

import com.blogApp.dto.PostDto;
import com.blogApp.dto.PostResponse;

public interface PostService {

	PostDto createPost(PostDto postDto);

	PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

	PostDto getPostById(Long id);

	PostDto updatePost(PostDto postDto, Long id);

	void deletePost(Long id);

	List<PostDto> getPostsByCategory(Long categoryId);

}

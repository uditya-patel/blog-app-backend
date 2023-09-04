package com.blogApp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blogApp.dto.PostDto;
import com.blogApp.dto.PostResponse;
import com.blogApp.entity.Category;
import com.blogApp.entity.Post;
import com.blogApp.exception.ResourceNotFoundException;
import com.blogApp.repository.CategoryRepository;
import com.blogApp.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	private ModelMapper mapper;
	
	private CategoryRepository categoryRepository;

	public PostServiceImpl(PostRepository postRepository, ModelMapper mapper, CategoryRepository categoryRepository) {
		super();
		this.postRepository = postRepository;
		this.mapper = mapper;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public PostDto createPost(PostDto postDto) { 
		
		Category category = categoryRepository.findById(postDto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", postDto.getCategoryId()));
		
		Post post = mapToEntity(postDto);
		
		post.setCategory(category);
		
		Post newPost = postRepository.save(post);
		
		PostDto postResponse = mapToDto(newPost);
		
		return postResponse;
		
		
	}
	
	private PostDto mapToDto(Post post) {
		
		PostDto postDto = mapper.map(post, PostDto.class);
		
//		PostDto postDto = new PostDto();
//		
//		postDto.setId(post.getId());
//		postDto.setContent(post.getContent());
//		postDto.setDescription(post.getDescription());
//		postDto.setTitle(post.getTitle());
		
		return postDto;
		
		
	}
	
	private Post mapToEntity(PostDto postDto) {
		
		Post post = mapper.map(postDto, Post.class);
		

		
//		Post post = new Post();
//		
//		post.setId(postDto.getId());
//		post.setContent(postDto.getContent());
//		post.setDescription(postDto.getDescription());
//		post.setTitle(postDto.getTitle());
		
		return post;
		
		
	}

	@Override
	public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
		
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		
		Page<Post> posts = postRepository.findAll(pageable);
		
		List<Post> listOfPosts = posts.getContent();
		
		List<PostDto> contents =  listOfPosts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
		
		
		PostResponse postResponse = new PostResponse();
		
		postResponse.setContent(contents);
		postResponse.setPageNo(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalElements(posts.getTotalElements());
		postResponse.setTotalPages(posts.getTotalPages());
		postResponse.setLast(posts.isLast());
		
		return postResponse;
		
	
		
	}

	@Override
	public PostDto getPostById(Long id) {
		
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post",  "id", id));
		
		return mapToDto(post);
		
	}

	@Override
	public PostDto updatePost(PostDto postDto, Long id) {
		
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
		
		Category category = categoryRepository.findById(postDto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", postDto.getCategoryId()));
		
		
		post.setContent(postDto.getContent());
		post.setDescription(postDto.getDescription());
		post.setTitle(postDto.getTitle());
		post.setCategory(category);
		
		postRepository.save(post);
		
		return mapToDto(post);
		
		
	}

	@Override
	public void deletePost(Long id) {
		
		 Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
		
		 postRepository.delete(post);
		
		
	}

	@Override
	public List<PostDto> getPostsByCategory(Long categoryId) {
		
		Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));
		
		List<Post> posts = postRepository.findByCategoryId(categoryId);
		
		return posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
		
	}
	

}

package com.blogApp.dto;

import java.util.Set;

import com.blogApp.entity.Comment;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDto {
	
	private Long id;
	
	@NotEmpty
	@Size(min = 2, message = "post title should be atleast 2 characters")
	private String title;
	
	@NotEmpty
	@Size(min = 10, message = "post description should be atleast 10 characters")
	private String description;
	
	@NotEmpty
	private String content;
	
	private Set<Comment> comments;
	
	private Long categoryId;
	
	

}

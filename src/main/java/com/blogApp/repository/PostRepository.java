package com.blogApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogApp.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	
	List<Post> findByCategoryId(Long categoryId);

}
;
package com.blogApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogApp.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}

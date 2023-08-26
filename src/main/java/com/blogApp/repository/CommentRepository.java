package com.blogApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogApp.entity.Comment;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	List<Comment> findByPostId(Long postId);

}

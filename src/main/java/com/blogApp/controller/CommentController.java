package com.blogApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogApp.dto.CommentDto;
import com.blogApp.service.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/comment")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/{postId}/addComment")
	public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") Long postId, @Valid @RequestBody CommentDto commentDto) {
		
		return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
	}
	
	@GetMapping("/comments/{postId}")
	public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId") Long postId) {
		
		return commentService.getCommentsByPostId(postId);
		
	}
	
	@GetMapping("/{commentId}/post/{postId}")
	public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "commentId") Long commentId, @PathVariable(value = "postId") Long postId) {
		
		return new ResponseEntity<>(commentService.getCommentById(commentId, postId), HttpStatus.OK);
	}
	
	@PutMapping("/updateComment/{commentId}/post/{postId}")
	public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") Long postId, @PathVariable(value = "commentId") Long commentId, @Valid @RequestBody CommentDto newComment) {
		
		return new ResponseEntity<>(commentService.updateComment(postId, commentId, newComment), HttpStatus.OK);
		
	}
	
	@DeleteMapping("/deleteComment/{commentId}/post/{postId}")
	public ResponseEntity<String> deleteComment(@PathVariable(value = "postId") Long postId, @PathVariable(value = "commentId") Long commentId) {
		
		commentService.deleteComment(postId, commentId);
		
		return new ResponseEntity<>("comment deleted successfully", HttpStatus.OK);
		
		
	}

}

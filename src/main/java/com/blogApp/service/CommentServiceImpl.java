package com.blogApp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.blogApp.dto.CommentDto;
import com.blogApp.entity.Comment;
import com.blogApp.entity.Post;
import com.blogApp.exception.BlogAPIException;
import com.blogApp.exception.ResourceNotFoundException;
import com.blogApp.repository.CommentRepository;
import com.blogApp.repository.PostRepository;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public CommentDto createComment(Long postId, CommentDto commentDto) {
		
		Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
		
		Comment comment = mapToEntity(commentDto);
		
		comment.setPost(post);
		
		Comment newComment = commentRepository.save(comment);
		
		return mapToDto(newComment);
		
	}
	
	private CommentDto mapToDto(Comment comment) {
		
		CommentDto commentDto = new CommentDto();
		
		commentDto.setId(comment.getId());
		commentDto.setName(comment.getName());
		commentDto.setEmail(comment.getEmail());
		commentDto.setBody(comment.getBody());
		
		return commentDto;
		
		
	}
	
	private Comment mapToEntity(CommentDto commentDto) {
		Comment comment = new Comment();
		
		comment.setId(commentDto.getId());
		comment.setBody(commentDto.getBody());
		comment.setEmail(commentDto.getEmail());
		comment.setName(commentDto.getName());
		
		return comment;
		
		
	}

	@Override
	public List<CommentDto> getCommentsByPostId(Long postId) {
		
		List<Comment> comments = commentRepository.findByPostId(postId);
		
		return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
		
	}

	@Override
	public CommentDto getCommentById(Long commentId, Long postId) {
		
		Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));
		
		Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment", "commentId", commentId));
		
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "comment doesn't belong to post");
		
		}
		
		return mapToDto(comment);
		
		
	}

	@Override
	public CommentDto updateComment(Long postId, Long commentId, CommentDto newComment) {

		Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));
		
		Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment", "commentId", commentId));
		
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "comment doesn't belong to post");
			
			
		}
		
		comment.setName(newComment.getName());
		comment.setBody(newComment.getBody());
		comment.setEmail(newComment.getEmail());
		
		Comment updatedComment = commentRepository.save(comment);
		
		return mapToDto(updatedComment);
		
		
	
	}

	@Override
	public void deleteComment(Long postId, Long commentId) {
		
Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));
		
		Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment", "commentId", commentId));
		
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "comment doesn't belong to post");
			
			
		}
		
		commentRepository.delete(comment);
		
		
	}

}

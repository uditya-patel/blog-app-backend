package com.blogApp.service;

import java.util.List;

import com.blogApp.dto.CommentDto;

public interface CommentService {

	CommentDto createComment(Long postId, CommentDto commentDto);

	List<CommentDto> getCommentsByPostId(Long postId);

	CommentDto getCommentById(Long commentId, Long postId);

	CommentDto updateComment(Long postId, Long commentId, CommentDto newComment);

	void deleteComment(Long postId, Long commentId);

}

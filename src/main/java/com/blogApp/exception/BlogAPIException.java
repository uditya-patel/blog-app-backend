package com.blogApp.exception;

import org.springframework.http.HttpStatus;

public class BlogAPIException extends RuntimeException{
	
	private HttpStatus status;
	
	private String message;

	public BlogAPIException(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public BlogAPIException(HttpStatus status, String message, String message1) {
		super(message);
		this.status = status;
		this.message = message1;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}
	
	


	
	
	

}

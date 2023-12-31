package com.blogApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogApp.dto.JwtAuthResponse;
import com.blogApp.dto.LoginDto;
import com.blogApp.dto.RegisterDto;
import com.blogApp.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	
	private AuthService authService;
	
	public AuthController(AuthService authService) {
		super();
		this.authService = authService;
	}



	@PostMapping(value = {"/login", "/signin"})
	public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto) {
		
		String token = authService.login(loginDto);
		
		JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
		
		jwtAuthResponse.setAcessToken(token);
		
		return ResponseEntity.ok(jwtAuthResponse);
	}
	
	@PostMapping(value = {"/register", "/signup"})
	public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
		String response = authService.register(registerDto);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}

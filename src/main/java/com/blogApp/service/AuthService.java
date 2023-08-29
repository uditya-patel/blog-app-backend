package com.blogApp.service;

import com.blogApp.dto.LoginDto;
import com.blogApp.dto.RegisterDto;

public interface AuthService {
	
	String login(LoginDto loginDto);
	
	String register(RegisterDto registerDto);

}

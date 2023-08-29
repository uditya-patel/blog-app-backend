package com.blogApp.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogApp.dto.LoginDto;
import com.blogApp.dto.RegisterDto;
import com.blogApp.entity.Role;
import com.blogApp.entity.User;
import com.blogApp.exception.BlogAPIException;
import com.blogApp.repository.RoleRepository;
import com.blogApp.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {
	
	
	private AuthenticationManager authenticationManager;
	
	private PasswordEncoder passwordEncoder;
	
	private  UserRepository userRepository;
	
	private RoleRepository roleRepository;


	public AuthServiceImpl(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder,
			UserRepository userRepository, RoleRepository roleRepository) {
		super();
		this.authenticationManager = authenticationManager;
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}



	@Override
	public String login(LoginDto loginDto) {
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUserNameOrEmail(), loginDto.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return "User Logged-In successfully";
		
		
		
	}



	@Override
	public String register(RegisterDto registerDto) {
		
		if(userRepository.existsByUserName(registerDto.getUserName())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "User name already exists!");
		}
		
		if(userRepository.existsByEmail(registerDto.getEmail())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "email already exists!");
			
		}
		
		User user = new User();
		
		user.setName(registerDto.getName());
		user.setEmail(registerDto.getEmail());
		user.setUserName(registerDto.getUserName());
		user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
		
		Set<Role> roles = new HashSet<>();
		
		Role userRole = roleRepository.findByName("ROLE_USER").get();
		
		roles.add(userRole);
		user.setRoles(roles);
		
		userRepository.save(user);
		
		return "User registered successfully";
		
		
		
	}

}

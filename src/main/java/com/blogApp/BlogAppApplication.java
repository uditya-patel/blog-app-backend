package com.blogApp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@SpringBootApplication
public class BlogAppApplication {
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
//	@Bean
//	public AuthenticationManager authenticationManager() {
//		return new AuthenticationManager() {
//			
//			@Override
//			public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//				// TODO Auto-generated method stub
//				return null;
//			}
//		};
//	}

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApplication.class, args);
	}

}

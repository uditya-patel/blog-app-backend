package com.blogApp.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.blogApp.exception.BlogAPIException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;



@Component
public class JwtTokenProvider {
	
	@Value("${app.jwt-secret}")
	private String jwtSecret;
	
	
	@Value("${app-jwt-expiration-milliseconds}")
	private Long jwtexpirationDate;
	
	// generate jwt token
	public String generateToken(Authentication authentication) {
		
		String userName = authentication.getName();
		
		System.out.println("jwtProvider" + userName);
		
		Date currentDate = new Date();
		
		Date ExpiryDate = new Date(currentDate.getTime() + jwtexpirationDate);
		
		
		String token = Jwts.builder()
							.setSubject(userName)
							.setIssuedAt(new Date())
							.setExpiration(ExpiryDate)
							.signWith(key())
							.compact();
		
		return token;
		
	}
	
	private Key key() {
		
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
		
	}
	
	//get user name from jwt token
	public String getUserName(String token) {
		Claims claims = Jwts.parserBuilder()
		.setSigningKey(key())
		.build()
		.parseClaimsJws(token)
		.getBody();
		
		String userName = claims.getSubject();
		
		return userName;
		
	}
	//validate jwt token
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder()
			.setSigningKey(key())
			.build()
			.parse(token);
			
			return true;
			
		}catch (MalformedJwtException ex) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Invalid jwt token");
			
		}catch(ExpiredJwtException ex) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "expired jwt toke");
			
		}catch(UnsupportedJwtException ex) {
			
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Unsupported jwt token");
			
		}catch(IllegalArgumentException ex) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "jwt claims string is empty");
		}
		
	}
	
	

}

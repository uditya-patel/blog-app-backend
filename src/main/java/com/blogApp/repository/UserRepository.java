package com.blogApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogApp.entity.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long>{
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findByUserNameOrEmail(String userName, String Email);
	
	Optional<User> findByUserName(String userName);
	
	Boolean existsByUserName(String userName);
	
	Boolean existsByEmail(String email);

}

package com.paypal.user_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paypal.user_service.entity.User;

public interface UserRepository extends JpaRepository{
	
	Optional<User> findByEmail(String email);

}

package com.paypal.user_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.user_service.entity.User;
import com.paypal.user_service.service.UserService;

@RestController
@RequestMapping("/api/users/")
public class UserController {
	
	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		
//		“Send back a letter (ResponseEntity) with:
//		status: CREATED (201)
//		body: the newly saved user info”
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id){
		return userService.getUserById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllUser(){
		return ResponseEntity.ok(userService.getAllUsers());

	}
}







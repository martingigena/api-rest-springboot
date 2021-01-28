package com.javafullstack.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javafullstack.app.entity.User;
import com.javafullstack.app.service.UserService;



@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//Create
	@PostMapping
	public ResponseEntity<?> create(@RequestBody User user){
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
	}
	
	//Read User
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id") Long userId){
		Optional<User> oUser = userService.findById(userId);
		
		if (!oUser.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(oUser);
		
	}
	
	//Update	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody User userDetails, @PathVariable Long id){
		Optional<User> oUser = userService.findById(id);
		if (!oUser.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		//BeanUtils.copyProperties(userDetails, oUser);
		oUser.get().setName(userDetails.getName());
		oUser.get().setLastname(userDetails.getLastname());
		oUser.get().setEmail(userDetails.getEmail());
		oUser.get().setEnable(userDetails.getEnable());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(oUser.get()));
		
	}
	
	//Delete
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		if(!userService.findById(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		userService.deleteById(id);		
		return ResponseEntity.ok().build();
		
		
	}
	
	
	//Read All
	@GetMapping
	public List<User> readAll(){
		List<User> users = StreamSupport
				.stream(userService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		return users;
		
	}
	
	
}

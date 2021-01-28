package com.javafullstack.app.service;

import org.springframework.data.domain.Pageable;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.javafullstack.app.entity.User;


public interface UserService {
	
	public Iterable<User> findAll();
	
	public Page<User> findAll(Pageable pageable);
	
	public Optional<User> findById(Long Id);
	
	public User save(User user);
	
	public void deleteById(Long Id);
	
}

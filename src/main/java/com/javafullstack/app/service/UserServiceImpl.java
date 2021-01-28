package com.javafullstack.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javafullstack.app.dao.UserDAO;
import com.javafullstack.app.entity.User;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO userDao;
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<User> findAll() {
		
		return userDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<User> findAll(Pageable pageable) {
		
		return userDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findById(Long Id) {
		
		return userDao.findById(Id);
	}

	@Override
	@Transactional
	public User save(User user) {
		
		return userDao.save(user);
	}

	@Override
	@Transactional
	public void deleteById(Long Id) {
		userDao.deleteById(Id);
		
	}
	
	
}

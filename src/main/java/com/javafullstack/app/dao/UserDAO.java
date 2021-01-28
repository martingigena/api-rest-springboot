package com.javafullstack.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javafullstack.app.entity.User;

@Repository
public interface UserDAO extends JpaRepository<User, Long>{

}

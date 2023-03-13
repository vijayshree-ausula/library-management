package com.library.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.api.dao.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUname(String username);
	
}

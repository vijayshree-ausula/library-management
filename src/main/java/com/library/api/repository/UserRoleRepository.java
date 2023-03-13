package com.library.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.api.dao.User;
import com.library.api.dao.User_Role;

@Repository
public interface UserRoleRepository extends JpaRepository<User_Role, Integer> {

}

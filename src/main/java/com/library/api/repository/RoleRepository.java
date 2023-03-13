package com.library.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.api.dao.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}

package com.library.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.library.test.dao.Quantity;

@Repository
public interface QuantityRepository extends JpaRepository<Quantity, Integer> {
	

}

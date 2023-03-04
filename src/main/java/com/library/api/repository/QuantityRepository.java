package com.library.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.library.api.dao.Quantity;

@Repository
public interface QuantityRepository extends JpaRepository<Quantity, Integer> {
	@Query(value = "SELECT * FROM Quantity where book_id = :book_id", nativeQuery=true)
	   public Quantity findAvailabilityByBookIdNative(@Param("book_id")  Integer book_id);
	
	@Modifying
	@Query(value = "UPDATE Quantity SET available = available-1 WHERE book_id = :book_id", nativeQuery=true)
	   public void updateReduceQuantityByBookIdNative(@Param("book_id")  Integer book_id);
	
	@Modifying
	@Query(value = "UPDATE Quantity SET available = available+1 WHERE book_id = :book_id", nativeQuery=true)
	   public void updateIncreaseQuantityByBookIdNative(@Param("book_id")  Integer book_id);

}

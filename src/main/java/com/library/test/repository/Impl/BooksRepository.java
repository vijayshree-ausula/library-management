package com.library.test.repository.Impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.test.Books;

@Repository
public interface BooksRepository extends JpaRepository<Books, Integer> {
	

}

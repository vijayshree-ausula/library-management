package com.library.test;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RestController;

import com.library.test.Books;

public interface BooksRepository extends CrudRepository<Books, Integer> {
	

}

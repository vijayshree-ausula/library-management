package com.library.test.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.library.test.dao.Book;
import com.library.test.dto.BooksDto;
import com.library.test.model.BookQuantity;

@Component
public interface BooksService {
	public String saveAllBooks(List<BookQuantity> books);
	public List<Book> getAllBooks();
}

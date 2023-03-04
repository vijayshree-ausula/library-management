package com.library.api.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.library.api.dao.Book;
import com.library.api.dao.Issue;
import com.library.api.dao.Member;
import com.library.api.exception.BookNotAvailableException;
import com.library.api.utilities.ValidList;

import jakarta.validation.Valid;

@Component
public interface BooksService {
	public List<Book> saveAllBooks(@Valid ValidList books);
	public List<Book> getAllBooks();
	public Member saveMember(com.library.api.model.Member member);
	public Issue saveIssue(com.library.api.model.Issue issue) throws BookNotAvailableException;
	public String saveReturnBook(com.library.api.model.Issue issue) throws BookNotAvailableException;
}

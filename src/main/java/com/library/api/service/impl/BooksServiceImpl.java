package com.library.api.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.api.dao.Book;
import com.library.api.dao.Quantity;
import com.library.api.exception.BookNotAvailableException;
import com.library.api.model.Issue;
import com.library.api.model.Member;
import com.library.api.repository.BooksRepository;
import com.library.api.repository.QuantityRepository;
import com.library.api.service.BooksService;
import com.library.api.utilities.Converter;
import com.library.api.utilities.ValidList;

import jakarta.transaction.Transactional;

@Service("booksServiceImpl")
public class BooksServiceImpl implements BooksService {

	@Autowired
	private BooksRepository booksRepository;
	
	@Autowired
	private QuantityRepository quantityRepository;
	
	@Autowired 
	Converter converter;
	
	@Override
	@Transactional
	public List<Book> saveAllBooks(ValidList books) {

		List<Book> bookResponse = new ArrayList<Book>();
		try {
			bookResponse = converter.splitIntoBookAndGenre(books);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return bookResponse;
	}
	
	@Override
	@Transactional
	public com.library.api.dao.Member saveMember(Member member) {

		com.library.api.dao.Member memberResponse = new com.library.api.dao.Member();
		try {
			memberResponse = converter.saveMember(member);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return memberResponse;
	}
	
	@Override
	@Transactional
	public com.library.api.dao.Issue saveIssue(Issue issue) throws BookNotAvailableException {

		com.library.api.dao.Issue issueResponse = new com.library.api.dao.Issue();
		try {
				issueResponse = converter.saveIssue(issue);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return issueResponse;
	}
	
	@Override
	@Transactional
	public String saveReturnBook(Issue issue) throws BookNotAvailableException {

		String issueResponse = "";
		try {
				issueResponse = converter.saveReturn(issue);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return issueResponse;
	}

	@Override
	public List<Book> getAllBooks() {
		
		List<Book> bookList = booksRepository.findAll();
		
		return bookList;
	}

}

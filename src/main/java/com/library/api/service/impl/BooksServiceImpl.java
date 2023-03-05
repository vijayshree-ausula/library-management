package com.library.api.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.api.dao.Book;
import com.library.api.dao.Quantity;
import com.library.api.exception.BookNotAvailableException;
import com.library.api.model.Issue;
import com.library.api.model.Member;
import com.library.api.model.ValidList;
import com.library.api.repository.BooksRepository;
import com.library.api.repository.QuantityRepository;
import com.library.api.service.BooksService;
import com.library.api.utilities.Converter;

import jakarta.transaction.Transactional;

@Service("booksServiceImpl")
public class BooksServiceImpl implements BooksService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BooksServiceImpl.class);

	@Autowired
	private BooksRepository booksRepository;
	
	@Autowired
	private QuantityRepository quantityRepository;
	
	@Autowired 
	Converter converter;
	
	@Override
	@Transactional
	public List<Book> saveAllBooks(ValidList books) {

		LOGGER.debug("Saving all the books");
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

		LOGGER.debug("Save new member to the Library");
		com.library.api.dao.Member memberResponse = new com.library.api.dao.Member();
		try {
			memberResponse = converter.saveMember(member);
		} catch (ParseException e) {
			LOGGER.error("Parse error while saving a member");
			e.printStackTrace();
		}
		return memberResponse;
	}
	
	@Override
	@Transactional
	public com.library.api.dao.Issue saveIssue(Issue issue) throws BookNotAvailableException {

		LOGGER.debug("Save new book issue to the Library");
		com.library.api.dao.Issue issueResponse = new com.library.api.dao.Issue();
		try {
				issueResponse = converter.saveIssue(issue);
		} catch (ParseException e) {
			LOGGER.error("Parse error while saving book issue");
			e.printStackTrace();
		}
		return issueResponse;
	}
	
	@Override
	@Transactional
	public String saveReturnBook(Issue issue) throws BookNotAvailableException {

		LOGGER.debug("Save return of the book");
		String issueResponse = "";
		try {
				issueResponse = converter.saveReturn(issue);
		} catch (ParseException e) {
			LOGGER.error("Error while returning the book");
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

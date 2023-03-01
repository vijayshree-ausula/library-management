package com.library.test.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.test.dao.Book;
import com.library.test.dao.Genre;
import com.library.test.dto.BooksDto;
import com.library.test.model.BookGenre;
import com.library.test.repository.BooksRepository;
import com.library.test.repository.GenreRepository;
import com.library.test.service.BooksService;
import com.library.test.utilities.Converter;

import jakarta.transaction.Transactional;

@Service("booksServiceImpl")
public class BooksServiceImpl implements BooksService {

	@Autowired
	private BooksRepository booksRepository;
	
	@Autowired
	private GenreRepository genreRepository;
	
	@Autowired 
	Converter converter;
	
	@Override
	@Transactional
	public String saveAllBooks(List<BookGenre> books) {
		
		 try {
			converter.splitIntoBookAndGenre(books);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 return "Saved";
	}

	@Override
	public List<Book> getAllBooks() {
		
		List<Book> bookList = booksRepository.findAll();
		List<Genre> genreList = genreRepository.findAll();
		List<BookGenre> retList = new ArrayList<BookGenre>();
		
		
		return null;
	}

}

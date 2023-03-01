package com.library.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.test.dao.Book;
import com.library.test.dao.Genre;
import com.library.test.dto.BooksDto;
import com.library.test.model.BookGenre;
import com.library.test.repository.BooksRepository;
import com.library.test.repository.GenreRepository;
import com.library.test.service.BooksService;
import com.library.test.utilities.Converter;


@RestController
public class LibraryController {
	
	@Autowired 
	BooksRepository booksRepository;
	
	@Autowired 
	GenreRepository genreRepository;
	
	@Autowired 
	BooksService booksService;
	
	@Autowired 
	Converter converter;
	
	@GetMapping(path = "/all")
	public @ResponseBody Iterable<BooksDto> getAllBooks() {
		List<Book> list = booksService.getAllBooks();
		Iterator<Book> itr = list.iterator();
		List<BooksDto> retList = new ArrayList<BooksDto>();
		try {
			while (itr.hasNext()) {
				ModelMapper modelMapper = new ModelMapper();
				BooksDto bookDto = modelMapper.map(itr.next(), BooksDto.class);
				retList.add(bookDto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retList;
	}
	
	@GetMapping(path = "/isbn/{isbn}")
	public @ResponseBody Iterable<Book> getBooksByISBN(@PathVariable("isbn") Integer isbn) {
		
		return booksRepository.findBooksByISBN(isbn);
	}
	
	@GetMapping(path = "/author")
	public @ResponseBody Iterable<Book> getBooksByAuthor(@RequestParam String author) {
		
		return booksRepository.findBooksByAuthorNative(author);
	}
	
	@GetMapping(path = "/genre")
	public @ResponseBody Iterable<Genre> getBooksByGenre(@RequestParam String genre) {
		
		return genreRepository.findBooksByGenreNative(genre);
	}
	  
	
	@PostMapping(path="/addBooks", headers = "Accept=application/json") 
	public @ResponseBody String addNewBook(@RequestBody List<BookGenre> books) {

		return booksService.saveAllBooks(books);
	}
	
	@RequestMapping(path="/greeting")
	public String getGreeting() {
		return "Welcome to Library Management";
	}

}

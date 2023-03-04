package com.library.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.tomcat.util.json.JSONParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.library.api.dao.Book;
import com.library.api.dao.Issue;
import com.library.api.dao.Member;
import com.library.api.dto.BooksDto;
import com.library.api.exception.BookNotAvailableException;
import com.library.api.exception.BookNotFoundException;
import com.library.api.exception.MemberNotFoundException;
import com.library.api.model.Books;
import com.library.api.repository.BooksRepository;
import com.library.api.repository.MemberRepository;
import com.library.api.repository.QuantityRepository;
import com.library.api.service.BooksService;
import com.library.api.utilities.Converter;
import com.library.api.utilities.ValidList;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;


@RestController
//@Validated
@RequestMapping("/library/api")
public class LibraryController {
	
	@Autowired 
	BooksRepository booksRepository;
	
	@Autowired 
	QuantityRepository quantityRepository;
	
	@Autowired 
	MemberRepository memberRepository;
	
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
	public @ResponseBody Iterable<Book> getBooksByISBN(@PathVariable("isbn") Integer isbn) throws BookNotFoundException {
		
		Iterable<Book> books = booksRepository.findBooksByISBN(isbn);
		
		if(books.iterator().hasNext()) {
			return books;
		} else {
			throw new BookNotFoundException("Book not found with isbn "+ isbn);
		}
	}
	
	@GetMapping(path = "/author")
	public @ResponseBody Iterable<Book> getBooksByAuthor(@RequestParam String author) throws BookNotFoundException {
		
		Iterable<Book> books = booksRepository.findBooksByAuthorNative(author);
		if(books.iterator().hasNext()) {
			return books;
		} else {
			throw new BookNotFoundException("Book not found with author "+ author);
		}
	}
	
	@GetMapping(path = "/title")
	public @ResponseBody Iterable<Book> getBooksByTitle(@RequestParam String title) throws BookNotFoundException {
		
		Iterable<Book> books = booksRepository.findBooksByTitleNative(title);
		if(books.iterator().hasNext()) {
			return books;
		} else {
			throw new BookNotFoundException("Book "+ title + " not available.");
		}
	}
	
	@GetMapping(path = "/genre")
	public @ResponseBody Iterable<Book> getBooksByGenre(@RequestParam String genre) throws BookNotFoundException {
		
		Iterable<Book> books = booksRepository.findBooksByGenreNative(genre);
		if(books.iterator().hasNext()) {
			return books;
		} else {
			throw new BookNotFoundException("Book not found with genre "+ genre);
		}
	}
	
	@GetMapping(path = "/email")
	public @ResponseBody Member getMemberByEmail(@RequestParam @Email String email) throws MemberNotFoundException {
		
		Member member = memberRepository.findMemberByEmailNative(email);
		if(member != null) {
			return member;
		} else {
			throw new MemberNotFoundException("Member not found with email "+ email);
		}
	}
	
	@GetMapping(path = "/phone")
	public @ResponseBody Member getMemberByPhone(@RequestParam @Pattern(regexp="^\\d{10}$") String phone) throws MemberNotFoundException {
		
		Member member = memberRepository.findMemberByPhoneNative(phone);
		if(member != null) {
			return member;
		} else {
			throw new MemberNotFoundException("Member not found with phone "+ phone);
		}
	}
	
	@GetMapping(path = "/name")
	public @ResponseBody Member getMemberByName(@RequestParam String name) throws MemberNotFoundException {
		
		Member member = memberRepository.findMemberByNameNative(name);
		if(member != null) {
			return member;
		} else {
			throw new MemberNotFoundException("Member not found with name "+ name);
		}
	}
	
	@PostMapping(path="/addBooks", headers = "Accept=application/json") 
	@Validated
	public @ResponseBody List<Book> addNewBook(@RequestBody @Valid ValidList books) {
		return booksService.saveAllBooks(books);
	}
	
	@PostMapping(path="/addMember", headers = "Accept=application/json") 
	@Validated
	public @ResponseBody Member addNewMember(@RequestBody @Valid com.library.api.model.Member member) {
		return booksService.saveMember(member);
	}
	
	@PostMapping(path="/issueBook", headers = "Accept=application/json") 
	@Validated
	public @ResponseBody Issue addIssueBook(@RequestBody @Valid com.library.api.model.Issue issue) throws BookNotAvailableException {
		return booksService.saveIssue(issue);
	}
	
	@PutMapping(path="/returnBook", headers = "Accept=application/json") 
	@Validated
	public @ResponseBody String returnBook(@RequestBody @Valid com.library.api.model.Issue issue) throws BookNotAvailableException {
		return booksService.saveReturnBook(issue);
	}
	
	@RequestMapping(path="/greeting")
	public String getGreeting() {
		return "Welcome to Library Management";
	}

}

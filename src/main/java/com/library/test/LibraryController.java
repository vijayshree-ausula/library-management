package com.library.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.test.repository.Impl.BooksRepository;

@RestController
public class LibraryController {
	
	@Autowired 
	BooksRepository booksRepository;
	
	@GetMapping("/all")
	public @ResponseBody Iterable<Books> getAllBooks() {
	  Iterable<Books> list =  booksRepository.findAll();
	  System.out.println("RETURNED VALUE:  "+list.iterator().next());
	  return list;
	}
	
	@RequestMapping("/greeting")
	public String getGreeting() {
		return "Welcome to Library Management";
	}

}

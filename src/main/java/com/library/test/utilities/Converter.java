package com.library.test.utilities;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.test.dao.Book;
import com.library.test.dao.Quantity;
import com.library.test.dto.BooksDto;
import com.library.test.model.BookQuantity;
import com.library.test.repository.BooksRepository;
import com.library.test.repository.QuantityRepository;

@Service("converter")
public class Converter {
	
	@Autowired
	private BooksRepository booksRepository;
	
	@Autowired
	private QuantityRepository quantityRepository;
	
	ModelMapper modelMapper = new ModelMapper();

	public BooksDto convertToDto(Book book) {
		BooksDto bookDto = modelMapper.map(book, BooksDto.class);
		bookDto.setIsbn(book.getIsbn());
		bookDto.setAuthor(book.getAuthor());
		bookDto.setTitle(book.getTitle());
	    return bookDto;
	}
	
	public Book convertToEntity(BooksDto bookDto) throws ParseException {
	    Book book = modelMapper.map(bookDto, Book.class);
	    book.setIsbn(bookDto.getIsbn());
	    book.setAuthor(bookDto.getAuthor());
	    book.setTitle(bookDto.getTitle());
	    return book;
	}
	
	public String splitIntoBookAndGenre(List<BookQuantity> bookAndGenre) throws ParseException {
		Iterator<BookQuantity> itr = bookAndGenre.iterator();
		List<Book> bookDaoList = new ArrayList<Book>();
		List<Quantity> quantityDaoList = new ArrayList<Quantity>();
		while(itr.hasNext()) {
			BookQuantity bookQuantity = itr.next();
			Book bookDao = new Book();
		    bookDao.setIsbn(bookQuantity.getIsbn());
		    bookDao.setTitle(bookQuantity.getTitle());
		    bookDao.setAuthor(bookQuantity.getAuthor());
		    bookDao.setGenre(bookQuantity.getGenre());
//		    bookDaoList.add(bookDao);
		    
		    Book book = booksRepository.save(bookDao);
		    
		    Quantity quantityDao = new Quantity();
		    if(bookQuantity.getTotal() != null) {
		    	quantityDao.setTotal(bookQuantity.getTotal());
		    } else {
		    	quantityDao.setTotal(1);
		    }
		    //Can add more logic. For now total and available are same.
		    if(bookQuantity.getTotal() != null) {
		    	quantityDao.setAvailable(bookQuantity.getTotal());
		    } else {
		    	quantityDao.setAvailable(1);
		    }
		    quantityDao.setBook(book);
		    quantityRepository.save(quantityDao);
		    
//		    quantityDaoList.add(quantityDao);
		}
//		booksRepository.saveAll(bookDaoList);
//		quantityRepository.saveAll(quantityDaoList);
	    
	    return "Saved";
	}
	
}

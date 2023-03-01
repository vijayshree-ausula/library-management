package com.library.test.utilities;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.test.dao.Book;
import com.library.test.dao.Genre;
import com.library.test.dto.BooksDto;
import com.library.test.model.BookGenre;
import com.library.test.repository.BooksRepository;
import com.library.test.repository.GenreRepository;

@Service("converter")
public class Converter {
	
	@Autowired
	private BooksRepository booksRepository;
	
	@Autowired
	private GenreRepository genreRepository;
	
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
	
	public String splitIntoBookAndGenre(List<BookGenre> bookAndGenre) throws ParseException {
		Iterator<BookGenre> itr = bookAndGenre.iterator();
		List<Book> bookDaoList = new ArrayList<Book>();
		List<Genre> genreDaoList = new ArrayList<Genre>();
		while(itr.hasNext()) {
			BookGenre bookGenre = itr.next();
			Book bookDao = new Book();
		    bookDao.setIsbn(bookGenre.getIsbn());
		    bookDao.setTitle(bookGenre.getTitle());
		    bookDao.setAuthor(bookGenre.getAuthor());
		    bookDaoList.add(bookDao);
		    
		    Genre genreDao = new Genre();
//		    genreDao.setBookId(bookGenre.getId());
		    if(!bookGenre.getGenre().isBlank()) {
		    	genreDao.setGenre(bookGenre.getGenre());
		    }
		    if(bookGenre.getTotal() != null) {
		    	genreDao.setTotal(bookGenre.getTotal());
		    } else {
		    	genreDao.setTotal(1);
		    }
		    //Can add more logic. For now total and available are same.
		    if(bookGenre.getTotal() != null) {
		    	genreDao.setAvailable(bookGenre.getTotal());
		    } else {
		    	genreDao.setAvailable(1);
		    }
		    genreDaoList.add(genreDao);
		}
		booksRepository.saveAll(bookDaoList);
		genreRepository.saveAll(genreDaoList);
	    
	    return "Saved";
	}
	
}

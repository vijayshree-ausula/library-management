package com.library.api.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.api.dao.Book;
import com.library.api.dao.Quantity;
import com.library.api.dto.BooksDto;
import com.library.api.exception.BookNotAvailableException;
import com.library.api.model.Books;
import com.library.api.model.Issue;
import com.library.api.model.Member;
import com.library.api.repository.BooksRepository;
import com.library.api.repository.IssueRepository;
import com.library.api.repository.MemberRepository;
import com.library.api.repository.QuantityRepository;

@Service("converter")
public class Converter {
	
	@Autowired
	private BooksRepository booksRepository;
	
	@Autowired
	private QuantityRepository quantityRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private IssueRepository issueRepository;
	
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

	public List<Book> splitIntoBookAndGenre(ValidList books) throws ParseException {
		Iterator<Books> itr = books.getBooks().iterator();
		List<Book> bookList  = new ArrayList<Book>();
		while(itr.hasNext()) {
			Books bookQuantity = itr.next();
			Book bookDao = new Book();
		    bookDao.setIsbn(bookQuantity.getIsbn());
		    bookDao.setTitle(bookQuantity.getTitle());
		    bookDao.setAuthor(bookQuantity.getAuthor());
		    bookDao.setGenre(bookQuantity.getGenre());
		    
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
		   Quantity quantity =  quantityRepository.save(quantityDao);
		   book.setQuantity(quantity);
		   bookList.add(book);
		    
		}
	    return bookList;
	}
	
	public com.library.api.dao.Member saveMember(Member member) throws ParseException {
		com.library.api.dao.Member memberDao = modelMapper.map(member, com.library.api.dao.Member.class);
		com.library.api.dao.Member response = memberRepository.save(memberDao);
	    return response;
		
	}
	
	public com.library.api.dao.Issue saveIssue(Issue issue) throws ParseException, BookNotAvailableException {
		com.library.api.dao.Issue issueDao = new com.library.api.dao.Issue();
		List<Book> books = booksRepository.findBooksByTitleNative(issue.getBookName());
		Book book = books.iterator().next();
		Integer bookId = book.getId();
		
		Quantity quantity = quantityRepository.findAvailabilityByBookIdNative(bookId);
		if(quantity.getAvailable() == 0) {
			throw new BookNotAvailableException("Book not available. Please check again later for availability");
		}
		
		List<com.library.api.dao.Member> members = memberRepository.findMemberByIdNative(issue.getMemberId());
		com.library.api.dao.Member member = members.iterator().next();
		String memberName = member.getName();
		
		issueDao.setBook_name(issue.getBookName());
		issueDao.setBook_id(bookId);
		issueDao.setMember_id(issue.getMemberId());
		issueDao.setMember_name(memberName);  
		Date issueDate = new Date();   
		issueDao.setIssue_date(issueDate);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(issueDate);
		cal.add(Calendar.DAY_OF_MONTH, 14);
		Date returnDate = cal.getTime();
		issueDao.setReturn_date(returnDate);		
		com.library.api.dao.Issue response = issueRepository.save(issueDao);
		
		quantityRepository.updateReduceQuantityByBookIdNative(bookId);
	    return response;
		
	}
	
	public String saveReturn(Issue issue) throws ParseException {
//		com.library.api.dao.Issue issueDao = new com.library.api.dao.Issue();
		List<Book> books = booksRepository.findBooksByTitleNative(issue.getBookName());
		Book book = books.iterator().next();
		Integer bookId = book.getId();

		issueRepository.updateBookReturnByBookNameAndMemberIdNative(issue.getBookName(), issue.getMemberId());
		quantityRepository.updateIncreaseQuantityByBookIdNative(bookId);
	    return "Book has been returned";
		
	}
	
}

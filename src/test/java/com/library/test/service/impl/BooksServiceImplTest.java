package com.library.test.service.impl;

import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.library.api.dao.Book;
import com.library.api.dao.Issue;
import com.library.api.dao.Member;
import com.library.api.dao.Quantity;
import com.library.api.repository.BooksRepository;
import com.library.api.repository.IssueRepository;
import com.library.api.repository.MemberRepository;
import com.library.api.repository.QuantityRepository;
import com.library.api.service.BooksService;
import com.library.api.service.impl.BooksServiceImpl;
import com.library.api.utilities.Converter;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BooksServiceImpl.class)
public class BooksServiceImplTest {
	
//	@MockBean
//	private BooksServiceImpl bookService;
	
	@Autowired
	private BooksServiceImpl bookService;
//
	@MockBean
	private BooksRepository booksRepository;
	
	@MockBean
	private QuantityRepository quantityRepository;
	
	@MockBean
	private IssueRepository issueRepository;
	
	@MockBean
	private MemberRepository memberRepository;
//	
	@MockBean 
	Converter converter;
	
	@Test
	public void testGetAllBooks() throws Exception {
		Book book = new Book();
		book = getBook();
		List<Book> books = new ArrayList<>();
		books.add(book);
		given(booksRepository.findAll()).willReturn(books);
		List<Book> result = bookService.getAllBooks();
	    assertEquals(result.size(), 1);
	}
	
	@Test
	public void testSaveIssue() throws Exception {
		com.library.api.model.Issue reqIssue = new com.library.api.model.Issue();
		reqIssue = getIssue();
		bookService.saveIssue(reqIssue);
		List<Book> books = new ArrayList<Book>();
		books.add(getBookById());
		given(booksRepository.findBooksByTitleNative("Harry Potter")).willReturn(books);
		given(quantityRepository.findAvailabilityByBookIdNative(1)).willReturn(getQuantity());
		List<com.library.api.dao.Member> members = new ArrayList<com.library.api.dao.Member>();
		members.add(getMember());
		given(memberRepository.findMemberByIdNative(1)).willReturn(members);
		com.library.api.dao.Issue reqIssueDao = getIssueDao();
		given(issueRepository.save(reqIssueDao)).willReturn(reqIssueDao);
		assertEquals(reqIssue.getMemberId(), reqIssueDao.getMember_id());
	}
	
	private Issue getIssueDao() {
		Issue issue = new Issue();
		issue.setBook_id(1);
		issue.setBook_name("Harry Potter");
		issue.setIssue_date(new Date());
		issue.setMember_id(1);
		issue.setId(1);
		issue.setMember_name("Sam");
		issue.setReturn_date(new Date());
		issue.setStatus("Issued");
		return issue;
	}

	private Member getMember() {
		Member member = new Member();
		member.setAddress("San Jose");
		member.setEmail("sam@gmail.com");
		member.setId(1);
		member.setName("Sam");
		member.setPhone("1234356363");
		member.setStatus("Active");
		return member;
	}

	private Quantity getQuantity() {
		Quantity quantity = new Quantity();
		quantity.setAvailable(5);
		quantity.setId(1);
		quantity.setTotal(10);
		return quantity;
	}

	private com.library.api.model.Issue getIssue() {
		com.library.api.model.Issue reqIssue = new com.library.api.model.Issue();
		reqIssue.setBook_id("1");
		reqIssue.setId(1);
		reqIssue.setMemberId(1);
		reqIssue.setStatus("ISSUED");
		return reqIssue;
	}
	
	private Book getBookById() {
		Book book = new Book();
		
		book.setAuthor("JK Rowling");
		book.setBook_id(1);
		book.setGenre("Fication");
		book.setIsbn(1111);
		book.setTitle("Harry Potter");
		Quantity quantity = new Quantity();
		quantity.setTotal(10);
		quantity.setAvailable(5);
		book.setQuantity(quantity);
		
		return book;
		
		
	}

	private Book getBook() {
		Book book = new Book();
		
		book.setAuthor("JK Rowling");
		book.setBook_id(1);
		book.setGenre("Fication");
		book.setIsbn(1111);
		book.setTitle("Harry Potter");
		Quantity quantity = new Quantity();
		quantity.setTotal(10);
		quantity.setAvailable(5);
		book.setQuantity(quantity);
		
		return book;
	}
}

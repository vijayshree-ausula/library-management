package com.library.api.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.library.api.dao.Book;
import com.library.api.dao.Quantity;
import com.library.api.dao.User;
import com.library.api.dto.BooksDto;
import com.library.api.exception.BookNotAvailableException;
import com.library.api.model.Books;
import com.library.api.model.Issue;
import com.library.api.model.Member;
import com.library.api.model.ValidList;
import com.library.api.repository.BooksRepository;
import com.library.api.repository.IssueRepository;
import com.library.api.repository.MemberRepository;
import com.library.api.repository.QuantityRepository;
import com.library.api.repository.UserRepository;
import com.library.api.repository.UserRoleRepository;
import com.library.api.service.impl.MemberUserDetailsService;

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
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private MemberUserDetailsService userDetailsService;
	
	ModelMapper modelMapper = new ModelMapper();

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
		 
		 com.library.api.dao.Member memberDao = mapRequestToMemberDao(member);
		 com.library.api.dao.Member response = memberRepository.save(memberDao);
		 
		 com.library.api.dao.User userDao = mapRequestToUserDao(member);
		 com.library.api.dao.User userDaoResponse = userDetailsService.createUser(userDao);
		 com.library.api.dao.User_Role userRoleDao = new com.library.api.dao.User_Role();
		 userRoleDao.setRoleId(2);
		 userRoleDao.setUser_id(userDaoResponse.getId());
		 
		 com.library.api.dao.User_Role userRoleDaoResponse = userRoleRepository.save(userRoleDao);
		 
		 return response;
		
	}
	
	private User mapRequestToUserDao(Member member) {
		User userDao = new User();
		userDao.setPassword(encodePassword(member.getPassword()));
		userDao.setUname(member.getUsername());
		return userDao;
	}

	private com.library.api.dao.Member mapRequestToMemberDao(Member member) {
		com.library.api.dao.Member memberDao = new com.library.api.dao.Member();
		memberDao.setEmail(member.getEmail());
		memberDao.setAddress(member.getAddress());
		memberDao.setName(member.getName());
		memberDao.setPhone(member.getPhone());
		memberDao.setStatus(member.getStatus());
		return memberDao;
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
		
		List<Book> books = booksRepository.findBooksByTitleNative(issue.getBookName());
		Book book = books.iterator().next();
		Integer bookId = book.getId();

		issueRepository.updateBookReturnByBookNameAndMemberIdNative(issue.getBookName(), issue.getMemberId());
		quantityRepository.updateIncreaseQuantityByBookIdNative(bookId);
	    return "Book has been returned";
		
	}
	
	String encodePassword(String password) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedString = encoder.encode(password);
		return encodedString;
		
	}
	
	String decodeToPassword(String encodedPassword) {
		
		byte[] decodedBytes = Base64.getDecoder().decode(encodedPassword);
		String password = new String(decodedBytes);
		return password;
		
	}
	
}

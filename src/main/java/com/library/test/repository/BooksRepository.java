package com.library.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.library.test.dao.Book;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
	
	@Query(value = "SELECT b FROM Book b where b.isbn = :isbn")
	   public List<Book> findBooksByISBN(@Param("isbn") Integer isbn);
	
	@Query(value = "SELECT * FROM Book b where b.author = :author", nativeQuery=true)
	   public List<Book> findBooksByAuthorNative(@Param("author")  String author);

	@Query(value = "SELECT * FROM Book b where b.genre = :genre", nativeQuery=true)
	   public List<Book> findBooksByGenreNative(@Param("genre")  String genre);
}

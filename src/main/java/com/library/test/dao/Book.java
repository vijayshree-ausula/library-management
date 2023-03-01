package com.library.test.dao;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity 
public class Book {
  @Id
  @GeneratedValue(strategy=GenerationType.SEQUENCE)
  private Integer book_id;

  private Integer isbn;

  private String title;

  private String author;
  
  @OneToOne(mappedBy = "book", fetch = FetchType.LAZY, orphanRemoval = false)
  private List<Genre> listGenre = new ArrayList<>();
  
  public Integer getBookId() {
    return book_id;
  }

  public void setId(Integer book_id) {
    this.book_id = book_id;
  }

  public Integer getIsbn() {
	  return isbn;
  }

  public void setIsbn(Integer isbn) {
	  this.isbn = isbn;
  }

  public String getTitle() {
	  return title;
  }

  public void setTitle(String title) {
	  this.title = title;
  }

  public String getAuthor() {
	  return author;
  }

  public void setAuthor(String author) {
	  this.author = author;
  }
 
}

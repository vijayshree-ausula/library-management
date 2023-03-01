package com.library.test.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity 
public class Genre {
  @Id
  @GeneratedValue(strategy=GenerationType.SEQUENCE)
  private Integer genre_id;

//  private Integer bookId;
  
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "book.book_id")
  private Book book;
  
  private String genre;
  
  private Integer total;
  
  private Integer available;
  
  public Integer getGenreId() {
    return genre_id;
  }

  public void setGenreId(Integer genre_id) {
    this.genre_id = genre_id;
  }

//  public Integer getBookId() {
//	  return bookId;
//  }
//
//  public void setBookId(Integer bookId) {
//	  this.bookId = bookId;
//  }

public String getGenre() {
	return genre;
}

public void setGenre(String genre) {
	this.genre = genre;
}

public Integer getTotal() {
	return total;
}

public void setTotal(Integer total) {
	this.total = total;
}

public Integer getAvailable() {
	return available;
}

public void setAvailable(Integer available) {
	this.available = available;
}
 
}

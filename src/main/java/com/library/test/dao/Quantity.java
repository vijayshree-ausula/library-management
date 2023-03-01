package com.library.test.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity 
public class Quantity {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;
  
  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "book_id", referencedColumnName = "id")
  @JsonBackReference
  private Book book;
  
  private Integer total;
  
  private Integer available;
  
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
 
  public Book getBook() {
	return book;
}

public void setBook(Book book) {
	this.book = book;
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

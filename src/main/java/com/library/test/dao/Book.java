package com.library.test.dao;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity 
public class Book {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  private Integer isbn;

  private String title;

  private String author;
  
  private String genre;
  
  @OneToOne(mappedBy = "book", orphanRemoval = true, cascade = CascadeType.PERSIST)
  @JsonManagedReference
  private Quantity quantity;
  
  public Integer getId() {
    return id;
  }

  public void setBook_id(Integer id) {
    this.id = id;
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

  public String getGenre() {
	  return genre;
  }

  public void setGenre(String genre) {
	  this.genre = genre;
  }

  public Quantity getQuantity() {
	  return quantity;
  }

  public void setQuantity(Quantity quantity) {
	  this.quantity = quantity;
  }
 
}

package com.library.api.dao;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity 
public class Book {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

//  @NotNull
  private Integer isbn;

//  @NotEmpty
  private String title;

//  @NotEmpty
  private String author;
  
  private String genre;
  
  private String status;
  
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

  public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public Quantity getQuantity() {
	  return quantity;
  }

  public void setQuantity(Quantity quantity) {
	  this.quantity = quantity;
  }
 
}

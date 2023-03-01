package com.library.test.model;

public class BookQuantity {

	  private Integer id;
	  
	  private Integer book_id;

	  private Integer isbn;

	  private String title;

	  private String author;
	  
	  private String genre;
	  
	  private Integer total;
	  
	  private Integer available;
	  
	  public Integer getId() {
	    return id;
	  }

	  public void setId(Integer id) {
	    this.id = id;
	  }

	  public Integer getBook_id() {
		  return book_id;
	  }

	  public void setBook_id(Integer book_id) {
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

package com.library.api.dto;

import lombok.Getter;
import lombok.Setter;

public class BooksDto {
	
	@Getter
	@Setter
	private Integer isbn;
	
	@Getter
	@Setter
	private String title;
	
	@Getter
	@Setter
	private String author;
	
	private String genre;
	
	private String total;

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

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
}

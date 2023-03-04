package com.library.api.utilities;

import java.util.List;

import com.library.api.model.Books;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class ValidList<E>  {
	
	@Valid
	@NotNull
	private List<Books> books;

	public List<Books> getBooks() {
		return books;
	}

	public void setBooks(List<Books> books) {
		this.books = books;
	}
   	
}

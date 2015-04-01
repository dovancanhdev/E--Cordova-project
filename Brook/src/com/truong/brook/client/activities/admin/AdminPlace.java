package com.truong.brook.client.activities.admin;

import com.truong.brook.client.activities.basic.BasicPlace;
import com.truong.brook.shared.Book;
import com.truong.brook.shared.Category;

public class AdminPlace extends BasicPlace {
	
	private Category category;
	private Book book;
	
	public AdminPlace(BasicPlace place, Category category) {
		super(place);
		this.category = category;
	}
	
	public AdminPlace(BasicPlace place, Book book, Category category) {
		super(place);
		this.book = book;
		this.category = category;
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
	public String getToken() {
		return "admin";
	}
}

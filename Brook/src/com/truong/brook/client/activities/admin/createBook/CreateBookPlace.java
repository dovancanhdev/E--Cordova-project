package com.truong.brook.client.activities.admin.createBook;

import com.truong.brook.client.activities.basic.BasicPlace;
import com.truong.brook.shared.Book;

public class CreateBookPlace extends BasicPlace {
	
	private Book book;
	private Long parentId;
	
	public CreateBookPlace(BasicPlace place, Book book, Long parentId) {
		super(place);
		this.book = book;
		this.parentId = parentId;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	public String getToken() {
		return "createbook";
	}
}

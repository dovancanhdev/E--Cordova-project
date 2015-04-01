package com.truong.brook.client.activities.book;

import com.truong.brook.client.activities.basic.BasicPlace;
import com.truong.brook.shared.Book;

public class BookPlace extends BasicPlace {
	private Book book;
	public BookPlace(BasicPlace place, Book book) {
		super(place);
		this.book = book;
	}
	
	public Book getBook() {
		return book;
	}
}

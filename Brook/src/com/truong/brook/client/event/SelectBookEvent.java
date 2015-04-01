package com.truong.brook.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.truong.brook.shared.Book;

public class SelectBookEvent extends GwtEvent<SelectBookEventHandler> {

	public static Type<SelectBookEventHandler> TYPE = new Type<SelectBookEventHandler>();
	
	private Long bookId;
	private Book book;
	
	public SelectBookEvent(Long bookId) {
		this.bookId = bookId;
	}
	
	public SelectBookEvent(Book film) {
		this.book = film;
	}
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<SelectBookEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(SelectBookEventHandler handler) {
		handler.onTapBook(this);
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
}

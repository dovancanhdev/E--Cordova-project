package com.truong.brook.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.truong.brook.shared.Book;

public class EditBookEvent extends GwtEvent<EditBookEventHandler> {

	public static Type<EditBookEventHandler> TYPE = new Type<EditBookEventHandler>();
	
	private Book book;
	
	public EditBookEvent(Book film) {
		this.book = film;
	}
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<EditBookEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(EditBookEventHandler handler) {
		handler.onTapBook(this);
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
}

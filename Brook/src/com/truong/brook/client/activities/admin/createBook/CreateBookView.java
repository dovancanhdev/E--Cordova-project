package com.truong.brook.client.activities.admin.createBook;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.truong.brook.shared.Book;

public interface CreateBookView{
	Widget asWidget();
	void refresh();
	void viewBook(Book book);
	
	HasText getTitle();
	HasText getDescription();
	HasText getAuthor();
	HasText getStreamUrl();
	HasClickHandlers getSaveBtn();
	HasClickHandlers getCancelBtn();
}

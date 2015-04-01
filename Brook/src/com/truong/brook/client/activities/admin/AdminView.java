package com.truong.brook.client.activities.admin;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Widget;
import com.truong.brook.shared.Book;
import com.truong.brook.shared.Category;

public interface AdminView {
	Widget asWidget();

	void refresh();

	void showCategory(Category category); 
	
	HasClickHandlers getCreateCategoryButton();
	HasClickHandlers getCreateBookButton();

	void showBooks(Book book);
}

package com.truong.brook.client.activities.book;

import com.truong.brook.client.activities.basic.BasicView;
import com.truong.brook.shared.Book;

public interface BookView extends BasicView{
	void viewBooks(Book currentBook);
}

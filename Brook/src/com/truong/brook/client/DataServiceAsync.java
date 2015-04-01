package com.truong.brook.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.truong.brook.shared.Book;
import com.truong.brook.shared.Category;
import com.truong.brook.shared.IBasic;

public interface DataServiceAsync {

	void getRootCategory(AsyncCallback<Category> asyncCallback);

	void getChildrent(Long parentId, AsyncCallback<List<IBasic>> asyncCallback);

	void updateCategory(Category category, AsyncCallback<Long> asyncCallback);

	void updateBook(Book book, AsyncCallback<Long> asyncCallback);
}

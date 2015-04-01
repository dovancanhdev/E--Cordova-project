package com.truong.brook.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.truong.brook.shared.Book;
import com.truong.brook.shared.Category;
import com.truong.brook.shared.IBasic;

public class ClientData {

	public static void getCategories(AsyncCallback<List<Category>> callback) {
		callback.onSuccess(genCategories());
	}
	
	public static void getBooks(Long id,
			AsyncCallback<List<IBasic>> callback) {
		callback.onSuccess(genBooks(id));
	}
	
	private static List<Category> genCategories() {
		List<Category> categories = new ArrayList<Category>();
		for(int i=0;i<10;i++) {
			Category category = new Category();
			category.setTitle("Category " + (i+1));
			categories.add(category);
		}
		ClientUtils.log("Gen: " + categories.size() + " categories");
		return categories;
	}
	
	private static List<IBasic> genBooks(Long parentId) {
		List<IBasic> books = new ArrayList<IBasic>();
		for(int i = 0;i<120;i++) {
			Book book = new Book();
			book.setId((long) (i+1));
			book.setTitle("Book " + (i+1));
			book.setParentId(parentId);
			book.setStreamUrl(Random.nextBoolean() ? "http://storage.googleapis.com/kstest-asia/xxx.mp3" : "http://storage.googleapis.com/kstest-asia/01.mp3");
			books.add(book);
		}
		return books;
	}

}

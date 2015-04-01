package com.truong.brook.server;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.ObjectifyService;
import com.truong.brook.client.DataService;
import com.truong.brook.shared.Book;
import com.truong.brook.shared.Category;
import com.truong.brook.shared.Config;
import com.truong.brook.shared.IBasic;

public class DataServiceImpl extends DataManager implements DataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static {
		ObjectifyService.register(Book.class);
		ObjectifyService.register(Category.class);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp){
	}

	@Override
	public Category getRootCategory() {
		Category root = new Category();
		root.setTitle("Root");
		root.setId(-1L);
		root.setChildCategories(getCategories(Config.ROOT_CATEGORY_ID));
		return root;
	}

	@Override
	public List<IBasic> getChildrent(Long parentId) {
		List<IBasic> iBasics = new ArrayList<IBasic>();
		List<Category> categories = getCategories(parentId);
		if(categories == null || categories.isEmpty()) {
			List<Book> books = getBooks(parentId);
			for(Book book : books) {
				iBasics.add(book);
			}
		}
		else {
			for(Category category : categories) {
				iBasics.add(category);
			}
		}
		return iBasics;
	}
	
	@Override
	public Long updateCategory(Category category) {
		return super.updateCategory(category);
	}
	
	@Override
	public Long updateBook(Book book) {
		return super.updateBook(book);
	}
}

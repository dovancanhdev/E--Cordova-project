package com.truong.brook.server;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.Closeable;
import com.truong.brook.shared.Book;
import com.truong.brook.shared.Category;

public class DataManager extends CustomRemoteServiceServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected final Logger log = Logger
			.getLogger(DataServiceImpl.class.getName()); 
	
	private Closeable closeable;

	protected void setUp() {
      closeable = ObjectifyService.begin();
    }
	
	protected void tearDown() {
      closeable.close();
	}
	
	protected Long updateBook(Book book) {
		setUp();
		Long id = ofy().save().entity(book).now().getId();
		tearDown();
		return id;
	}
	
	protected Book getBook(Long id) {
		setUp();
		Book book =  ofy().load().type(Book.class).id(id).now();
		tearDown();
		return book;
	}
	
	protected List<Book> getBooks(Long parentId) {
		setUp();
		List<Book> books = null;
		try{
			books = new ArrayList<Book>(ofy().load().type(Book.class).filter("parentId", parentId).list());
		}
		catch (Exception e) {
			e.printStackTrace();
			books = new ArrayList<Book>();
		}
		tearDown();
		return books;
	}
	
	protected void deleteBook(Long id) {
		setUp();
		ofy().delete().type(Book.class).id(id);
		tearDown();
	}
	
	protected Long updateCategory(Category category) {
		setUp();
		Long id = ofy().save().entity(category).now().getId();
		tearDown();
		return id;
	}
	
	protected Category getCategory(Long id) {
		setUp();
		Category category = ofy().load().type(Category.class).id(id).now();
		tearDown();
		return category;
	}
	
	protected List<Category> getCategories(Long parentId) {
		setUp();
		List<Category> categories = null;
		try{
			categories = new ArrayList<Category>(ofy().load().type(Category.class).filter("parentId", parentId).list());
		}
		catch (Exception e) {
			e.printStackTrace();
			categories =  new ArrayList<Category>();
		}
		tearDown();
		return categories;
		
	}
	
	protected void deleteCateogry(Long id) {
		setUp();
		ofy().delete().type(Category.class).id(id);
		tearDown();
	}
	
}

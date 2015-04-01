package com.truong.brook.client.activities;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.truong.brook.client.activities.admin.AdminView;
import com.truong.brook.client.activities.admin.AdminViewImpl;
import com.truong.brook.client.activities.admin.createBook.CreateBookView;
import com.truong.brook.client.activities.admin.createBook.CreateBookViewImpl;
import com.truong.brook.client.activities.admin.createCategory.CreateCategoryView;
import com.truong.brook.client.activities.admin.createCategory.CreateCategoryViewImpl;
import com.truong.brook.client.activities.basic.BasicView;
import com.truong.brook.client.activities.book.BookView;
import com.truong.brook.client.activities.book.BookViewImpl;
import com.truong.brook.client.activities.category.CategoryView;
import com.truong.brook.client.activities.category.CategoryViewImpl;
import com.truong.brook.client.activities.home.HomeView;
import com.truong.brook.client.activities.home.NewHomeViewImpl;


public class ClientFactoryImpl implements ClientFactory {
	private SimpleEventBus eventBus;
	private PlaceController placeController;
	private BasicView basicView;
	private HomeView homeView;
	private CategoryView categoryView;
	private BookView bookView;
	private AdminView adminView;
	private CreateBookView createBookView;
	private CreateCategoryView createCategoryView;
	
	public ClientFactoryImpl() {
		eventBus = new SimpleEventBus();
		placeController = new PlaceController(eventBus);
	}
	
	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public HomeView getHomeView() {
		if(homeView==null){
			homeView= new NewHomeViewImpl();
		}
		basicView = homeView;
		return homeView;
	}

	@Override
	public BasicView getBasicView() {
		return basicView;
	}

	@Override
	public CategoryView getCategoryView() {
		if(categoryView==null){
			categoryView= new CategoryViewImpl();
		}
		basicView = categoryView;
		return categoryView;
	}

	@Override
	public BookView getBookView() {
		if(bookView==null){
			bookView= new BookViewImpl();
		}
		basicView = bookView;
		return bookView;
	}

	@Override
	public AdminView getAdminView() {
		if(adminView==null){
			adminView= new AdminViewImpl();
		}
		return adminView;
	}

	@Override
	public CreateBookView getCreateBookView() {
		if(createBookView==null){
			createBookView= new CreateBookViewImpl();
		}
		return createBookView;
	}

	@Override
	public CreateCategoryView getCreateCategoryView() {
		if(createCategoryView==null){
			createCategoryView= new CreateCategoryViewImpl();
		}
		return createCategoryView;
	}
}

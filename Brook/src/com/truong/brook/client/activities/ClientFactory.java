package com.truong.brook.client.activities;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.truong.brook.client.activities.admin.AdminView;
import com.truong.brook.client.activities.admin.createBook.CreateBookView;
import com.truong.brook.client.activities.admin.createCategory.CreateCategoryView;
import com.truong.brook.client.activities.basic.BasicView;
import com.truong.brook.client.activities.book.BookView;
import com.truong.brook.client.activities.category.CategoryView;
import com.truong.brook.client.activities.home.HomeView;

public interface ClientFactory {
	PlaceController getPlaceController();

	EventBus getEventBus();

	BasicView getBasicView();
	
	HomeView getHomeView();

	CategoryView getCategoryView();

	BookView getBookView();

	AdminView getAdminView();

	CreateBookView getCreateBookView();

	CreateCategoryView getCreateCategoryView();
}

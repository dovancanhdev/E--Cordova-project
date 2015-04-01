package com.truong.brook.client.activities;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.truong.brook.client.activities.admin.AdminActivity;
import com.truong.brook.client.activities.admin.AdminPlace;
import com.truong.brook.client.activities.admin.createBook.CreateBookActivity;
import com.truong.brook.client.activities.admin.createBook.CreateBookPlace;
import com.truong.brook.client.activities.admin.createCategory.CreateCategoryActivity;
import com.truong.brook.client.activities.admin.createCategory.CreateCategoryPlace;
import com.truong.brook.client.activities.basic.BasicPlace;
import com.truong.brook.client.activities.book.BookActivity;
import com.truong.brook.client.activities.book.BookPlace;
import com.truong.brook.client.activities.category.CategoryActivity;
import com.truong.brook.client.activities.category.CategoryPlace;
import com.truong.brook.client.activities.home.HomeActivity;
import com.truong.brook.client.activities.home.HomePlace;

public class PhoneActivityMapper implements ActivityMapper {
	
	private ClientFactory clientFactory;
	
	public PhoneActivityMapper(ClientFactory clientFactory){
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof HomePlace)
			return new HomeActivity(clientFactory,(BasicPlace)place);
		if (place instanceof CategoryPlace)
			return new CategoryActivity(clientFactory, (BasicPlace)place);
		if (place instanceof BookPlace)
			return new BookActivity(clientFactory, (BasicPlace)place);
		if (place instanceof AdminPlace)
			return new AdminActivity(clientFactory, (BasicPlace)place);
		if (place instanceof CreateCategoryPlace)
			return new CreateCategoryActivity(clientFactory, (BasicPlace)place);
		if (place instanceof CreateBookPlace)
			return new CreateBookActivity(clientFactory, (BasicPlace)place);
		return null;
	}
}

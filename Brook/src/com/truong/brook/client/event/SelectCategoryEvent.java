package com.truong.brook.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.truong.brook.shared.Category;

public class SelectCategoryEvent extends GwtEvent<SelectCategoryEventHandler> {

	public static Type<SelectCategoryEventHandler> TYPE = new Type<SelectCategoryEventHandler>();
	
	private Category category;
	
	public SelectCategoryEvent(Category category) {
		this.setCategory(category);
	}
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<SelectCategoryEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(SelectCategoryEventHandler handler) {
		handler.onSelectCategory(this);
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}

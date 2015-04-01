package com.truong.brook.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.truong.brook.shared.Category;

public class EditCategoryEvent extends GwtEvent<EditCategoryEventHandler> {

	public static Type<EditCategoryEventHandler> TYPE = new Type<EditCategoryEventHandler>();
	
	private Category category;
	
	public EditCategoryEvent(Category category) {
		this.setCategory(category);
	}
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<EditCategoryEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(EditCategoryEventHandler handler) {
		handler.onSelectCategory(this);
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}

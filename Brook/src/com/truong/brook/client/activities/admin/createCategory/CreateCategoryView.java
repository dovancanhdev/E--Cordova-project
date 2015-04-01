package com.truong.brook.client.activities.admin.createCategory;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.truong.brook.shared.Category;

public interface CreateCategoryView {
	Widget asWidget();
	void refresh();
	
	HasText getTitle();
	HasText getDescription();
	int getType();
	HasClickHandlers getSaveBtn();
	HasClickHandlers getCancelBtn();
	void viewCategory(Category category);
}

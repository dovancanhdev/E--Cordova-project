package com.truong.brook.client.activities.category;

import com.truong.brook.client.activities.basic.BasicPlace;
import com.truong.brook.shared.Category;

public class CategoryPlace extends BasicPlace {
	private Category category;

	public CategoryPlace(BasicPlace previousPlace, Category category) {
		super(previousPlace);
		this.category = category;
	}

	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
}

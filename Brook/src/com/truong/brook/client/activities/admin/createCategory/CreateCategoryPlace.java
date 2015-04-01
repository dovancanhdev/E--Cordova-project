package com.truong.brook.client.activities.admin.createCategory;

import com.truong.brook.client.activities.basic.BasicPlace;
import com.truong.brook.shared.Category;

public class CreateCategoryPlace extends BasicPlace {
	
	private Long parentId;
	private Category category;
	
	public CreateCategoryPlace(BasicPlace place, Category category, Long parentId) {
		super(place);
		this.category = category;
		this.parentId = parentId;
	}
	
	public Category getCategory() {
		return category;
	}

	public Long getParentId() {
		return parentId;
	}
	
	public String getToken() {
		return "createcategory";
	}
}

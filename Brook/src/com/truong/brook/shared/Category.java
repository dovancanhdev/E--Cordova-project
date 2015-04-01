package com.truong.brook.shared;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;

@Entity
@Cache
public class Category extends IBasic{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id private Long id;
	@Index private Long parentId = Config.NULL_ID;
	@Index private int type = Config.TYPE_CATEGORY;
	private String title = Config.TEXT_EMPTY;
	private String description = Config.TEXT_EMPTY;
	private String avatarUrl = Config.TEXT_EMPTY;
	@Index private Long lastUpdate;
	
	@Ignore private List<Category> childCategories = null;
	@Ignore private List<Book> childBooks = null;
	
	public Category(){
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public List<Category> getChildCategories() {
		if(childCategories == null)
			childCategories = new ArrayList<Category>();
		return childCategories;
	}

	public void setChildCategories(List<Category> childCategories) {
		this.childCategories = childCategories;
	}
	
	public List<Book> getChildBooks() {
		if(childBooks == null)
			childBooks = new ArrayList<Book>();
		return childBooks;
	}

	public void setChildBooks(List<Book> childBooks) {
		this.childBooks = childBooks;
	}

	public boolean hasChild() {
		boolean hasChild = false;
		hasChild = (childCategories!= null && !childCategories.isEmpty()) ||(childBooks!=null && !childBooks.isEmpty());
		return hasChild;
	}

	public void setChildBasicCategories(List<IBasic> result) {
		this.childCategories = new ArrayList<Category>();
		for(IBasic basic : result) {
			this.childCategories.add((Category)basic);
		}
	}

	public void setChildBasicBooks(List<IBasic> result) {
		this.childBooks = new ArrayList<Book>();	
		for(IBasic basic : result) {
			this.childBooks.add((Book)basic);
		}
	}
	
	public Long getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Long lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
}

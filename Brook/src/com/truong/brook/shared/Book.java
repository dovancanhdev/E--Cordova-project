package com.truong.brook.shared;

import java.util.List;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;

@Entity
@Cache
public class Book extends IBasic{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id private Long id;
	@Index private Long parentId = Config.NULL_ID;
	@Index private Long authorId = Config.NULL_ID;
	@Index private boolean hasChild = false;
	@Index private Double vote = Config.ZERO_VALUE;
	@Index private int voteCount;
	@Index private int viewedNumber;
	@Index private Long lastUpdate;
	private String title = Config.TEXT_EMPTY;
	private String description = Config.TEXT_EMPTY;
	private String author = Config.TEXT_EMPTY;
	private String avatarUrl = Config.TEXT_EMPTY;
	private String streamUrl = Config.TEXT_EMPTY; 
	private Double price = Config.ZERO_VALUE;
	
	@Ignore private List<IBasic> childBooks = null;
	
	public Book() {
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

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getStreamUrl() {
		return streamUrl;
	}

	public void setStreamUrl(String streamUrl) {
		this.streamUrl = streamUrl;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getVote() {
		return vote;
	}

	public void setVote(Double vote) {
		this.vote = vote;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	public int getViewedNumber() {
		return viewedNumber;
	}

	public void setViewedNumber(int viewedNumber) {
		this.viewedNumber = viewedNumber;
	}

	public Long getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Long lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public boolean isHasChild() {
		return hasChild;
	}

	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}

	public List<IBasic> getChildBooks() {
		return childBooks;
	}

	public void setChildBooks(List<IBasic> childBooks) {
		this.childBooks = childBooks;
	}
}

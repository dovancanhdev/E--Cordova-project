package com.truong.brook.client.activities.admin;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.truong.brook.client.Brook;
import com.truong.brook.client.ClientUtils;
import com.truong.brook.client.activities.ClientFactory;
import com.truong.brook.client.activities.admin.createBook.CreateBookPlace;
import com.truong.brook.client.activities.admin.createCategory.CreateCategoryPlace;
import com.truong.brook.client.activities.basic.BasicActivity;
import com.truong.brook.client.activities.basic.BasicPlace;
import com.truong.brook.client.event.EditBookEvent;
import com.truong.brook.client.event.EditBookEventHandler;
import com.truong.brook.client.event.EditCategoryEvent;
import com.truong.brook.client.event.EditCategoryEventHandler;
import com.truong.brook.client.event.SelectBookEvent;
import com.truong.brook.client.event.SelectBookEventHandler;
import com.truong.brook.client.event.SelectCategoryEvent;
import com.truong.brook.client.event.SelectCategoryEventHandler;
import com.truong.brook.client.sliding.SlidingPanel;
import com.truong.brook.shared.Book;
import com.truong.brook.shared.Category;
import com.truong.brook.shared.IBasic;

public class AdminActivity extends BasicActivity {

	private AdminView view;
	private Category category;
	private Book book;
	
	public AdminActivity(ClientFactory clientFactory, BasicPlace place) {
		super(clientFactory, place);
		category = ((AdminPlace)place).getCategory();
		book = ((AdminPlace)place).getBook();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view = clientFactory.getAdminView();
		this.eventBus = eventBus;
		cancelAllHandlerRegistrations();
		panel.setWidget(view.asWidget());
		bind();
		loadData();
	}

	@Override
	protected void bind() {
		super.bind();
		addHandlerRegistration(eventBus.addHandler(SelectCategoryEvent.TYPE, new SelectCategoryEventHandler() {
			
			@Override
			public void onSelectCategory(SelectCategoryEvent event) {
				goToCategory(event.getCategory());
			}
		}));
		
		addHandlerRegistration(eventBus.addHandler(SelectBookEvent.TYPE, new SelectBookEventHandler() {
			
			@Override
			public void onTapBook(SelectBookEvent event) {
				ClientUtils.log("go to book");
				goTo(new AdminPlace(place,event.getBook(), category));
			}
		}));
		
		addHandlerRegistration(eventBus.addHandler(EditCategoryEvent.TYPE, new EditCategoryEventHandler() {
			
			@Override
			public void onSelectCategory(EditCategoryEvent event) {
				goToCreateNewCategory(event.getCategory());
			}
		}));
		
		addHandlerRegistration(eventBus.addHandler(EditBookEvent.TYPE, new EditBookEventHandler() {
			
			@Override
			public void onTapBook(EditBookEvent event) {
				goToCreateNewBook(event.getBook());
			}
		}));
		
		addHandlerRegistration(view.getCreateCategoryButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				goToCreateNewCategory(null);
			}
		}));
		
		addHandlerRegistration(view.getCreateBookButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				goToCreateNewBook(null);
			}
		}));
	}

	protected void goToCreateNewBook(Book book) {
		ClientUtils.log("goToCreateNewBook: " + (this.book != null ? "!= null" : "null"));
		Long parentId = this.book == null ? category.getId() : this.book.getId();
		if(book != null)
			goTo(new CreateBookPlace(place, book, parentId));
		else goTo(new CreateBookPlace(place, null, parentId));
	}

	protected void goToCreateNewCategory(Category category) {
		if(category != null)
			goTo(new CreateCategoryPlace(place, category, category.getParentId()));
		else goTo(new CreateCategoryPlace(place, null, this.category.getId()));
	}

	protected void goToCategory(Category category) {
		goTo(new AdminPlace(place, category));
	}

	@Override
	protected void loadData() {
		ClientUtils.log("load data");
		if(category == null) {
			//Load root category
			ClientUtils.log("Load root category");
			Brook.dataService.getRootCategory(new AsyncCallback<Category>() {

				@Override
				public void onFailure(Throwable caught) {
				}

				@Override
				public void onSuccess(Category result) {
					AdminActivity.this.category = result;
					((AdminPlace)place).setCategory(category);
					view.showCategory(category);
				}
			});
		}
		else if(book == null && category.hasChild()){
			ClientUtils.log("category has childrent show categories");
			view.showCategory(category);
		}
		else {
			ClientUtils.log("book: " + (book != null ? "!= null" : "null"));
			final Long parrentId = book == null ? category.getId() : book.getId();
			Brook.dataService.getChildrent(parrentId, new AsyncCallback<List<IBasic>>() {

				@Override
				public void onFailure(Throwable caught) {
					ClientUtils.log("getChildrent for parentId: " + parrentId + " failed");
				}

				@Override
				public void onSuccess(List<IBasic> result) {
					ClientUtils.log("getChildrent for parentId: " + parrentId + " onSuccess: " + result.size());
					if(book == null) {
						if(result != null && !result.isEmpty()) {
							if(result.get(0) instanceof Category) {
								category.setChildBasicCategories(result);
							} else if(result.get(0) instanceof Book) {
								category.setChildBasicBooks(result);
							}
						}
						view.showCategory(category);
					} else {
						if(result != null && !result.isEmpty()) {
							book.setChildBooks(result);
						}
						view.showBooks(book);
					}
				}
			});
		}
	}

	@Override
	protected void onBackButtonPressed() {
		if (SlidingPanel.hideSliding()) {
			return;
		}
		goTo(place.getPreviousPlace());
	}
}

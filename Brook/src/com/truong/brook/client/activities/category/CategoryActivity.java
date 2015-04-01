package com.truong.brook.client.activities.category;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellSelectedEvent;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellSelectedHandler;
import com.truong.brook.client.ClientData;
import com.truong.brook.client.ClientUtils;
import com.truong.brook.client.activities.ClientFactory;
import com.truong.brook.client.activities.basic.BasicActivity;
import com.truong.brook.client.activities.basic.BasicPlace;
import com.truong.brook.client.activities.book.BookPlace;
import com.truong.brook.client.event.SelectBookEvent;
import com.truong.brook.client.event.SelectBookEventHandler;
import com.truong.brook.client.sliding.SlidingPanel;
import com.truong.brook.shared.Book;
import com.truong.brook.shared.Category;
import com.truong.brook.shared.IBasic;

public class CategoryActivity extends BasicActivity {

	private CategoryView view;
	public enum Type {
		NONE, CATEGORY, COUNTRY
	}
	private Type currentType = Type.NONE;
	private Category currentCategory;
	private int categoryPage = 0;
	
	public CategoryActivity(ClientFactory clientFactory, BasicPlace place) {
		super(clientFactory, place);
		CategoryPlace categoryPlace = (CategoryPlace)place;
		if(categoryPlace.getCategory() != null) {
			currentCategory = categoryPlace.getCategory();
			currentType = Type.CATEGORY;
		}
 	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view = clientFactory.getCategoryView();
		super.start(panel, eventBus, view);
		panel.setWidget(view);
		if(currentType == Type.CATEGORY) {
			view.getLayout().getHeaderPanel().setCenter(currentCategory.getTitle());
		}
		view.getLayout().getHeaderPanel().getBackButton().setVisible(true);
	}

	@Override
	protected void bind() {
		super.bind();
		view.getCellList().addCellSelectedHandler(new CellSelectedHandler() {
			
			@Override
			public void onCellSelected(CellSelectedEvent event) {
				int index = event.getIndex();
				Book b = currentCategory.getChildBooks().get(index);
				goTo(new BookPlace(place, b));
			}
		});
	}
	
	@Override
	protected void loadData() {
		ClientUtils.log("Load data");
		categoryPage = 0;
		loadBooks();
	}

	private void loadBooks() {
		if(currentCategory != null) {
			if(currentCategory.getChildBooks() != null && !currentCategory.getChildBooks().isEmpty()){
				List<IBasic> iBasics = new ArrayList<IBasic>();
				for(Book book : currentCategory.getChildBooks()) {
					iBasics.add(book);
				}
				view.viewBooks(iBasics);
			}
			else 
			ClientData.getBooks(currentCategory.getId(), new AsyncCallback<List<IBasic>>() {

				@Override
				public void onFailure(Throwable caught) {
				}

				@Override
				public void onSuccess(List<IBasic> result) {
					currentCategory.setChildBasicBooks(result);
					view.viewBooks(result);
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

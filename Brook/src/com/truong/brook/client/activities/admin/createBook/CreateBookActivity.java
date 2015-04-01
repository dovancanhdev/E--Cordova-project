package com.truong.brook.client.activities.admin.createBook;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.truong.brook.client.Brook;
import com.truong.brook.client.activities.ClientFactory;
import com.truong.brook.client.activities.admin.AdminPlace;
import com.truong.brook.client.activities.basic.BasicActivity;
import com.truong.brook.client.activities.basic.BasicPlace;
import com.truong.brook.client.sliding.SlidingPanel;
import com.truong.brook.shared.Book;

public class CreateBookActivity extends BasicActivity {

	private CreateBookView view;
	private Book book;
	private Long parentId;
	private boolean createNew = true;
	
	public CreateBookActivity(ClientFactory clientFactory, BasicPlace place) {
		super(clientFactory, place);
		parentId = ((CreateBookPlace)place).getParentId();
		book = ((CreateBookPlace)place).getBook();
		createNew = (book == null);
		
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view = clientFactory.getCreateBookView();
		panel.setWidget(view.asWidget());
		cancelAllHandlerRegistrations();
		panel.setWidget(view.asWidget());
		view.refresh();
		bind();
		if(!createNew) {
			view.viewBook(book);
		}
	}

	@Override
	protected void bind() {
		super.bind();
		addHandlerRegistration(view.getCancelBtn().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				view.refresh();
			}
		}));
		
		addHandlerRegistration(view.getSaveBtn().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				doSaveBook();
			}
		}));	
	}

	protected void doSaveBook() {
		String title = view.getTitle().getText();
		String description = view.getDescription().getText();
		String author = view.getAuthor().getText();
		String streamUrl = view.getStreamUrl().getText();
		if(title.isEmpty()) {
			return;
		}
		if(createNew) {
			book = new Book();
		}
		book.setTitle(title);
		book.setDescription(description);
		book.setAuthor(author);
		book.setStreamUrl(streamUrl);
		book.setParentId(parentId);
		Brook.dataService.updateBook(book, new AsyncCallback<Long>() {

			@Override
			public void onFailure(Throwable caught) {	
				Window.alert("Failed to create new book");
			}

			@Override
			public void onSuccess(Long result) {
				book.setId(result);
				if(createNew)
					((AdminPlace)place.getPreviousPlace()).getCategory().getChildBooks().add(0, book);
				goTo(place.getPreviousPlace());
			}
		});
	}

	@Override
	protected void onBackButtonPressed() {
		if (SlidingPanel.hideSliding()) {
			return;
		}
		goTo(place.getPreviousPlace());
	}
}

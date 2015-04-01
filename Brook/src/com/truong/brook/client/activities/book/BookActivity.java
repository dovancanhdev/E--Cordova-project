package com.truong.brook.client.activities.book;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.truong.brook.client.Brook;
import com.truong.brook.client.activities.ClientFactory;
import com.truong.brook.client.activities.basic.BasicActivity;
import com.truong.brook.client.activities.basic.BasicPlace;
import com.truong.brook.client.sliding.SlidingPanel;
import com.truong.brook.shared.Book;

public class BookActivity extends BasicActivity {

	private BookView view;
	private Book currentBook;

	public BookActivity(ClientFactory clientFactory, BasicPlace place) {
		super(clientFactory, place);
		currentBook = ((BookPlace)place).getBook();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view = clientFactory.getBookView();
		super.start(panel, eventBus, view);
		panel.setWidget(view);
		view.getLayout().getHeaderPanel().setCenter(currentBook.getTitle());
	}

	@Override
	protected void bind() {
		super.bind();
	}

	@Override
	protected void loadData() {
		view.viewBooks(currentBook);
	}

	@Override
	protected void onBackButtonPressed() {
		if (SlidingPanel.hideSliding()) {
			return;
		}
		Brook.getAudioPlayer().stopAudio();
		goTo(place.getPreviousPlace());
	}
}

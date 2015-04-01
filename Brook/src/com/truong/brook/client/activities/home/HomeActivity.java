package com.truong.brook.client.activities.home;

import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellSelectedEvent;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellSelectedHandler;
import com.truong.brook.client.Brook;
import com.truong.brook.client.ClientData;
import com.truong.brook.client.ClientUtils;
import com.truong.brook.client.activities.ClientFactory;
import com.truong.brook.client.activities.basic.BasicActivity;
import com.truong.brook.client.activities.basic.BasicPlace;
import com.truong.brook.client.activities.book.BookPlace;
import com.truong.brook.client.sliding.SlidingPanel;
import com.truong.brook.client.view.Toaster;
import com.truong.brook.shared.Book;
import com.truong.brook.shared.IBasic;

public class HomeActivity extends BasicActivity {

	private HomeView view;
	private List<IBasic> books;
	private boolean exitApp = false;
	private Timer exitAppTimer = new Timer() {

		@Override
		public void run() {
			exitApp = false;
		}
	};
	
	public HomeActivity(ClientFactory clientFactory, BasicPlace place) {
		super(clientFactory, place);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view = clientFactory.getHomeView();
		super.start(panel, eventBus, view);
		panel.setWidget(view);
		view.getLayout().getHeaderPanel().setCenter("Brook");
		view.getLayout().getHeaderPanel().getBackButton().setVisible(false);
	}

	@Override
	protected void bind() {
		super.bind();
		view.getCellList().addCellSelectedHandler(new CellSelectedHandler() {
			
			@Override
			public void onCellSelected(CellSelectedEvent event) {
				int index = event.getIndex();
				IBasic b = books.get(index);
				goTo(new BookPlace(place, (Book)b));
			}
		});
	}

	@Override
	protected void loadData() {
		ClientData.getBooks(-1L, new AsyncCallback<List<IBasic>>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(List<IBasic> result) {
				books = result;
				view.viewBooks(books);
			}
		});
	}

	@Override
	protected void onBackButtonPressed() {
		if (SlidingPanel.hideSliding()) {
			return;
		}
		askToExitApp();
	}

	private void askToExitApp() {
		if (exitApp) {
			Brook.phoneGap.exitApp();
		} else {
			Toaster.showToast("Tap back again to exit", false, 3);
		}
		exitApp = !exitApp;
		exitAppTimer.schedule(3000);
	}
}

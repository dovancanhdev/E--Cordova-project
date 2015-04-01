package com.truong.brook.client.activities.basic;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.gwtphonegap.client.event.BackButtonPressedEvent;
import com.googlecode.gwtphonegap.client.event.BackButtonPressedHandler;
import com.googlecode.gwtphonegap.client.event.MenuButtonPressedEvent;
import com.googlecode.gwtphonegap.client.event.MenuButtonPressedHandler;
import com.googlecode.gwtphonegap.client.event.SearchButtonPressedEvent;
import com.googlecode.gwtphonegap.client.event.SearchButtonPressedHandler;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.truong.brook.client.Brook;
import com.truong.brook.client.ClientUtils;
import com.truong.brook.client.activities.ClientFactory;
import com.truong.brook.client.activities.book.BookPlace;
import com.truong.brook.client.activities.category.CategoryPlace;
import com.truong.brook.client.event.SelectBookEvent;
import com.truong.brook.client.event.SelectBookEventHandler;
import com.truong.brook.client.event.SelectCategoryEvent;
import com.truong.brook.client.event.SelectCategoryEventHandler;
import com.truong.brook.client.sliding.SlidingPanel;

public class BasicActivity extends MGWTAbstractActivity {
	
	protected final ClientFactory clientFactory;
	protected EventBus eventBus;
	protected BasicPlace place = null;
	
	public BasicActivity(ClientFactory clientFactory, BasicPlace place) {
		this.clientFactory = clientFactory;
		this.place = place;
	}
	
	@Override
	public void start(AcceptsOneWidget panel, final EventBus eventBus) {
		super.start(panel, eventBus);
		this.eventBus = eventBus;
		cancelAllHandlerRegistrations();
	}
	
	public void start(AcceptsOneWidget panel, final EventBus eventBus, final BasicView basicView) { 
		this.eventBus = eventBus;
		loadData();
		bind();
		if(basicView!=null) {
			basicView.refreshView();
			addHandlerRegistration(basicView.getLayout().getHeaderPanel().getBackButton().addTapHandler(new TapHandler() {
				
				@Override
				public void onTap(TapEvent event) {
					onBackButtonPressed();
				}
			}));
			
			//Add handler for leftmenu
			addHandlerRegistration(basicView.getLayout().getHeaderPanel().getLeftMenuButton().addTapHandler(
					new TapHandler() {
				
				@Override
				public void onTap(TapEvent event) {
					onMenuButtonPressed();
				}
			}));
			
			addHandlerRegistration(eventBus.addHandler(SelectCategoryEvent.TYPE, new SelectCategoryEventHandler() {
				
				@Override
				public void onSelectCategory(SelectCategoryEvent event) {
					goTo(new CategoryPlace(place,event.getCategory()));
					basicView.getMenuSliding().hide();
				}
			}));
			
			addHandlerRegistration(eventBus.addHandler(SelectBookEvent.TYPE, new SelectBookEventHandler() {
				
				@Override
				public void onTapBook(SelectBookEvent event) {
					goTo(new BookPlace(place,event.getBook()));
				}
			}));
		}
	}
	
	protected void bind() {
		addHandlerRegistration(Brook.phoneGap.getEvent().getBackButton().addBackButtonPressedHandler(new BackButtonPressedHandler() {
			@Override
			public void onBackButtonPressed(BackButtonPressedEvent event) {
				BasicActivity.this.onBackButtonPressed();
			}
		}));
		addHandlerRegistration(Brook.phoneGap.getEvent().getMenuButton().addMenuButtonPressedHandler(new MenuButtonPressedHandler() {
			@Override
			public void onMenuButtonPressed(MenuButtonPressedEvent event) {
				BasicActivity.this.onMenuButtonPressed();
			}
		}));
		addHandlerRegistration(Brook.phoneGap.getEvent().getSearchButton().addSearchButtonHandler(new SearchButtonPressedHandler() {
			
			@Override
			public void onSearchButtonPressed(SearchButtonPressedEvent event) {
			}
		}));
	}
	
	protected void onMenuButtonPressed() {
		if(clientFactory.getBasicView().getMenuSliding().isShowing())
			clientFactory.getBasicView().getMenuSliding().hide();
		else
			clientFactory.getBasicView().getMenuSliding().show();
	}
	
	protected void onLeftMenuPressed() {
	}

	protected void loadData() {
		
	}
	
	protected void onBackButtonPressed() {
		if(SlidingPanel.hideSliding())
			return;
		
	}
	
	protected void onRefreshScrollPanel() {
	}
	
	protected void goTo(Place newPlace) {
		if(newPlace == null)
			return;
		ClientUtils.log("Go to : " + newPlace.getClass().getName());
		clientFactory.getPlaceController().goTo(newPlace);
	}
}

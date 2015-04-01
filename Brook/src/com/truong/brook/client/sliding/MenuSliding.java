package com.truong.brook.client.sliding;

import java.util.List;

import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.dom.client.recognizer.swipe.SwipeEndEvent;
import com.googlecode.mgwt.dom.client.recognizer.swipe.SwipeEndHandler;
import com.googlecode.mgwt.dom.client.recognizer.swipe.SwipeEvent.DIRECTION;
import com.googlecode.mgwt.ui.client.util.CssUtil;
import com.googlecode.mgwt.ui.client.widget.touch.TouchPanel;
import com.truong.brook.client.ClientData;
import com.truong.brook.client.ClientUtils;
import com.truong.brook.client.CssToken;
import com.truong.brook.client.view.CategoryViewItem;
import com.truong.brook.shared.Category;

public class MenuSliding extends SlidingPanel {
	private TouchPanel touchPanel = new TouchPanel();
	private int widthMenu = 220;
	private boolean showCategory = false;
	
	public MenuSliding() {
		super();
		slidingPanel.setHeight("100%");
		this.setStyleName("leftMenuSliding", true);
		touchPanel.setWidth(ClientUtils.getScreenWidth()-widthMenu + "px");
		touchPanel.setStyleName("tyty", true);
		touchPanel.setHeight("100%");
		this.add(touchPanel);
		touchPanel.getElement().getStyle().setProperty("float", "left");
		slidingPanel.getElement().getStyle().setProperty("float", "left");
		scrollPanel.setWidth(widthMenu + "px");
		scrollPanel.getElement().getStyle().setBackgroundColor("#1d1d1d");
		touchPanel.addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				hide();
			}
		});
		
		touchPanel.addSwipeEndHandler(new SwipeEndHandler() {
			
			@Override
			public void onSwipeEnd(SwipeEndEvent event) {
				if(event.getDirection() == DIRECTION.RIGHT_TO_LEFT) {
					hide();
				}
			}
		});
		
		scrollPanel.refresh();
	}
	
	@Override
	public void show() {
		super.show();
		if(!showCategory)
			showCategories();
		scrollPanel.setHeight(ClientUtils.getScreenHeight() + 50+ "px");
		CssUtil.translate(RootPanel.get().getElement(), widthMenu, 0);
		CssUtil.setTransitionDuration(RootPanel.get().getElement(), 200);
	}
	
	private void showCategories() {
		showCategory = true;
		ClientUtils.log("Show categories");
		ClientData.getCategories(new AsyncCallback<List<Category>>() {
		
			@Override
			public void onSuccess(List<Category> result) {
				ClientUtils.log("Result: " + result.size());
				for(Category category : result) {
					CategoryViewItem item = new CategoryViewItem(category);
					item.setStyleName(CssToken.BUTTON_FLATSTYLE + " " + CssToken.LEFTMENU_BUTTON, true);
					mainPanel.add(item);
				}
				scrollPanel.refresh();
			}
			
			@Override
			public void onFailure(Throwable caught) {
				
			}
		});
		
	}
	
	@Override
	public void hide() {
		super.hide();
		CssUtil.translate(RootPanel.get().getElement(), 0, 0);
		CssUtil.setTransitionDuration(RootPanel.get().getElement(), 200);
	}
	
	@Override
	protected String getAnimationCss() {
		return "xxy";
	}
	
	@Override
	protected void setPosition() {
		this.getElement().getStyle().setPosition(Position.ABSOLUTE);
		this.getElement().getStyle().setTop(0, Unit.PX);
		this.getElement().getStyle().setLeft(-220, Unit.PX);
	}
	
	public static void clearAll() {
		if(MenuSliding.currentSliding != null) {
			MenuSliding.currentSliding.removeFromParent();
		}
	}
}

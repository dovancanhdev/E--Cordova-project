package com.truong.brook.client.view;

import java.util.List;

import com.google.gwt.user.client.ui.FlowPanel;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.touch.TouchPanel;
import com.truong.brook.client.Brook;
import com.truong.brook.client.ClientUtils;
import com.truong.brook.client.CssToken;
import com.truong.brook.client.event.SelectCategoryEvent;
import com.truong.brook.shared.Category;

public class TopicPanel extends FlowPanel {
	private KSImageButton headerPanel = new KSImageButton();
	private FlowPanel contenPanel = new FlowPanel();
	private int widthView = 0;
	
	public TopicPanel() {
		super();
		this.setWidth("100%");
		this.add(headerPanel);
		this.add(contenPanel);
//		headerPanel.add(topicTitle);
		headerPanel.setStyleName(CssToken.TOPIC_HEADER, true);
	}
	
	public TopicPanel(String titleTopic, List<Category> categories ) {
		this();
		headerPanel.setTitle(titleTopic);
//		this.topicTitle.setHTML(titleTopic);
		showListCategories(categories);
		addHandler();
	}
	
	public TopicPanel(String imageTopic, String titleTopic, int size) {
		this();
		headerPanel.setImage(imageTopic, size);
		headerPanel.setTitle(titleTopic);
//		this.topicTitle.setHTML(titleTopic);
	}
	
	public void addHandler() {
		headerPanel.addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				setVisibleContent();
			}
		});
	}
	
	public void setVisibleContent() {
		contenPanel.setVisible(!contenPanel.isVisible());
		if(contenPanel.isVisible()) {
		 	headerPanel.removeStyleName(CssToken.TOPIC_HEADER);
		 	headerPanel.setStyleName(CssToken.TOPIC_HEADER_ACTIVE, true);
		} else {
			headerPanel.removeStyleName(CssToken.TOPIC_HEADER_ACTIVE);
		 	headerPanel.setStyleName(CssToken.TOPIC_HEADER, true);
		}
	}

	public void setVisibleContent(boolean isVisible) {
		contenPanel.setVisible(!isVisible);
		setVisibleContent();
	}

	public void showListCategories(List<Category> categories) {
		if(!categories.isEmpty()) {
			if(widthView == 0) 
				widthView = ClientUtils.calItemsSize(120, ClientUtils.getScreenWidth() - 10,7);
			for(final Category category : categories) {
				Button buttonCate = new Button(" +  " + category.getTitle());
				buttonCate.setStyleName(CssToken.BUTTON_FLATSTYLE + " " + CssToken.LEFTMENU_CATEGORY, true);
				buttonCate.getElement().getStyle().setProperty("float", "left");
				buttonCate.getElement().getStyle().setProperty("clear", "left");
				contenPanel.add(buttonCate);
				buttonCate.addTapHandler(new TapHandler() {
					
					@Override
					public void onTap(TapEvent event) {
						//TODO to films with category
						Brook.getClientFactory().getEventBus().fireEvent(new SelectCategoryEvent(category));
					}
				});
				
			}
			contenPanel.setVisible(false);
		}
	}

	public TouchPanel getTouchPanel() {
		return headerPanel;
	}
}

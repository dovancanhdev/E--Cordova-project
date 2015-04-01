package com.truong.brook.client.view;

import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.widget.touch.TouchPanel;
import com.truong.brook.client.Brook;
import com.truong.brook.client.event.SelectBookEvent;
import com.truong.brook.shared.Book;

public class BookViewItem extends TouchPanel {
	private Book currentbook = null;
	private Image image = new Image();
	private HTML filmTitle = new HTML();
	private HTML filmDescription = new HTML();
	private HTMLPanel htmlPanel = new HTMLPanel("");
	
	public BookViewItem() {
		super();
		this.getElement().getStyle().setPosition(Position.RELATIVE);
		this.add(image);
		this.add(htmlPanel);
		htmlPanel.add(filmTitle);
		htmlPanel.add(filmDescription);
	}
	
	public BookViewItem(Book book) {
		this();
		htmlPanel.setWidth("100%");
		htmlPanel.getElement().getStyle().setPosition(Position.ABSOLUTE);
		htmlPanel.getElement().getStyle().setBottom(0, Unit.PX);
		htmlPanel.getElement().getStyle().setProperty("background", "rgba(0,0,0,0.6)");
		htmlPanel.getElement().getStyle().setProperty("maxHeight", "60px");

		filmTitle.getElement().getStyle().setProperty("color", "#fd4801");
		filmTitle.getElement().getStyle().setProperty("padding", "5px");
		filmTitle.getElement().getStyle().setProperty("fontSize", "1.2em");
		filmTitle.getElement().getStyle().setProperty("fontWeight", "bold");
		
		filmDescription.getElement().getStyle().setProperty("color","white");
		filmDescription.getElement().getStyle().setProperty("padding", "5px");
		filmDescription.getElement().getStyle().setProperty("fontSize", "1.2em");
		image.setHeight("220px");
		viewInfor();
	}
	
	public BookViewItem(Book book,int width, int height) {
		this();
		this.filmTitle.setWidth(width -7 + "px");
		this.filmTitle.getElement().getStyle().setProperty("color", "#ffeed4");
		this.filmTitle.getElement().getStyle().setProperty("padding", "5px");
		this.filmTitle.getElement().getStyle().setProperty("fontSize", "1em");
		this.filmTitle.getElement().getStyle().setProperty("fontWeight", "bold");
//		this.filmTitle.getElement().getStyle().setProperty("lineHeight", "1.1em");
		this.filmTitle.getElement().getStyle().setProperty("height", "1.8em");
		this.filmTitle.getElement().getStyle().setProperty("overflow", "hidden");
		this.image.getElement().getStyle().setProperty("border", "2px solid white");
		this.image.setPixelSize(width, height);
		this.currentbook = book;
		viewInfor();
		addHandler();
	}

	private void viewInfor() {
		this.image.setUrl(currentbook.getAvatarUrl());
		if(!this.currentbook.getTitle().isEmpty())
			this.filmTitle.setHTML(this.currentbook.getTitle());
		if(!this.currentbook.getTitle().isEmpty())
			this.filmDescription.setHTML(this.currentbook.getDescription());
	}

	private void addHandler() {
		this.addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				Brook.getClientFactory().getEventBus().fireEvent(new SelectBookEvent(currentbook));
			}
		});
	}
}

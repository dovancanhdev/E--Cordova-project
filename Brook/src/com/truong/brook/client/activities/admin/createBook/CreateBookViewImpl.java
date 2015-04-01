package com.truong.brook.client.activities.admin.createBook;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.base.TextBox;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.truong.brook.shared.Book;

public class CreateBookViewImpl implements CreateBookView{
	private TextBox titleTextBox;
	private TextBox descriptionTextBox;
	private TextBox authorTextBox;
	private TextBox streamUrlTextBox;
	private Button saveButton;
	private Button cancelButton;
	
	private VerticalPanel panel;
	
	public CreateBookViewImpl() {	
		super();
		this.titleTextBox = new TextBox();
		this.descriptionTextBox = new TextBox();
		this.authorTextBox = new TextBox();
		this.streamUrlTextBox = new TextBox();
		this.titleTextBox.setWidth("300px");
		this.descriptionTextBox.setWidth("300px");
		this.authorTextBox.setWidth("300px");
		this.streamUrlTextBox.setWidth("300px");
		
		this.saveButton = new Button("Save");
		this.cancelButton = new Button("Cancel");
		this.saveButton.setWidth("132px");
		this.cancelButton.setWidth("132px");
		this.panel = new VerticalPanel();
		panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		this.panel.setSpacing(10);
		panel.add(titleTextBox);
		panel.add(descriptionTextBox);
		panel.add(authorTextBox);
		panel.add(streamUrlTextBox);
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setSpacing(5);
		horizontalPanel.add(cancelButton);
		horizontalPanel.add(saveButton);
		panel.add(horizontalPanel);
	}

	@Override
	public Widget asWidget() {
		return panel;
	}

	@Override
	public void refresh() {
		this.titleTextBox.setText("");
		this.descriptionTextBox.setText("");
		this.authorTextBox.setText("");
		this.streamUrlTextBox.setText("");
	}

	@Override
	public void viewBook(Book book) {
		if(book != null){
			this.titleTextBox.setText(book.getTitle());
			this.descriptionTextBox.setText(book.getDescription());
			this.authorTextBox.setText(book.getAuthor());
			this.streamUrlTextBox.setText(book.getStreamUrl());
		}
	}

	@Override
	public HasText getTitle() {
		return titleTextBox;
	}

	@Override
	public HasText getDescription() {
		return descriptionTextBox;
	}

	@Override
	public HasText getAuthor() {
		return authorTextBox;
	}

	@Override
	public HasText getStreamUrl() {
		return streamUrlTextBox;
	}

	@Override
	public HasClickHandlers getSaveBtn() {
		return saveButton;
	}

	@Override
	public HasClickHandlers getCancelBtn() {
		return cancelButton;
	}
}

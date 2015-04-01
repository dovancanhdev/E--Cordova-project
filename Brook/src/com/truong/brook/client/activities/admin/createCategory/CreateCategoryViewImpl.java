package com.truong.brook.client.activities.admin.createCategory;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.ListBox;
import com.github.gwtbootstrap.client.ui.base.TextBox;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.truong.brook.shared.Category;
import com.truong.brook.shared.Config;

public class CreateCategoryViewImpl implements CreateCategoryView{
	private TextBox titleTextBox;
	private TextBox descriptionTextBox;
	private ListBox typeListBox;
	private Button saveButton;
	private Button cancelButton;
	
	private VerticalPanel panel;
	
	public CreateCategoryViewImpl() {	
		super();
		this.titleTextBox = new TextBox();
		this.descriptionTextBox = new TextBox();
		this.typeListBox = new ListBox();
		this.titleTextBox.setWidth("300px");
		this.descriptionTextBox.setWidth("300px");
		this.typeListBox.setWidth("315px");
		this.typeListBox.addItem("Category", Config.TYPE_CATEGORY +"");
		this.typeListBox.addItem("Author", Config.TYPE_AUTHOR+"");
		
		this.saveButton = new Button("Save");
		this.cancelButton = new Button("Cancel");
		this.saveButton.setWidth("132px");
		this.cancelButton.setWidth("132px");
		this.panel = new VerticalPanel();
		panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		this.panel.setSpacing(10);
		panel.add(titleTextBox);
		panel.add(descriptionTextBox);
		panel.add(typeListBox);
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
		this.typeListBox.setSelectedIndex(0);
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
	public int getType() {
		return Integer.parseInt(typeListBox.getSelectedValue());
	}

	@Override
	public HasClickHandlers getSaveBtn() {
		return saveButton;
	}

	@Override
	public HasClickHandlers getCancelBtn() {
		return cancelButton;
	}

	@Override
	public void viewCategory(Category category) {
		if(category != null) {
			this.titleTextBox.setText(category.getTitle());
			this.descriptionTextBox.setText(category.getDescription());
			this.typeListBox.setSelectedIndex(category.getType());
		}
	}
}

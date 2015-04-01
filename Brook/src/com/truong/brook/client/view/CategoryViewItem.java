package com.truong.brook.client.view;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.HTML;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.widget.touch.TouchPanel;
import com.truong.brook.client.Brook;
import com.truong.brook.client.event.SelectCategoryEvent;
import com.truong.brook.shared.Category;

public class CategoryViewItem extends TouchPanel implements KSWidget {
	
	private HTML titleButton = null;
	private Category category = null;
	
	public CategoryViewItem() {
		super();
		setStyleName(getKSClassName(), true);
	}
	
	public CategoryViewItem(Category category) {
		this(category.getTitle());
		this.category = category;
		addHandler();
	}
	
	public CategoryViewItem(String title) {
		this();
		if(title != null) {
			this.titleButton = new HTML(title);
			this.titleButton.getElement().getStyle().setPaddingTop(10, Unit.PX);
			this.titleButton.getElement().getStyle().setPaddingLeft(10, Unit.PX);
			this.add(titleButton);
		}
	}
	private void addHandler() {
		this.addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				Brook.getClientFactory().getEventBus().fireEvent(new SelectCategoryEvent(category));
			}
		});
	}

	@Override
	public String getKSClassName() {
		return "Category-Item";
	}

}

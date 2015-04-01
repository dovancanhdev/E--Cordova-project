package com.truong.brook.client.activities.admin.createCategory;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.truong.brook.client.Brook;
import com.truong.brook.client.ClientUtils;
import com.truong.brook.client.activities.ClientFactory;
import com.truong.brook.client.activities.admin.AdminPlace;
import com.truong.brook.client.activities.basic.BasicActivity;
import com.truong.brook.client.activities.basic.BasicPlace;
import com.truong.brook.client.sliding.SlidingPanel;
import com.truong.brook.shared.Category;

public class CreateCategoryActivity extends BasicActivity {

	private CreateCategoryView view;
	private Category category;
	private Long parentId;
	private boolean createNew = true;
	
	public CreateCategoryActivity(ClientFactory clientFactory, BasicPlace place) {
		super(clientFactory, place);
		parentId = ((CreateCategoryPlace)place).getParentId();
		category = ((CreateCategoryPlace)place).getCategory();
		if(category == null) {
			createNew = true;
		}else {
			createNew = false;
		}
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view = clientFactory.getCreateCategoryView();
		this.eventBus = eventBus;
		cancelAllHandlerRegistrations();
		panel.setWidget(view.asWidget());
		view.refresh();
		bind();
		if(!createNew) {
			view.viewCategory(category);
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
				doSaveCategory();
			}
		}));
	}

	private void doSaveCategory() {
		String title = view.getTitle().getText();
		String description = view.getDescription().getText();
		int type = view.getType();
		if(title.isEmpty()) {
			return;
		}
		if(createNew) {
			category = new Category();
		}
		category.setTitle(title);
		category.setDescription(description);
		category.setType(type);
		ClientUtils.log("parentId: " + parentId);
		category.setParentId(parentId);
		Brook.dataService.updateCategory(category, new AsyncCallback<Long>() {

			@Override
			public void onFailure(Throwable caught) {	
				Window.alert("Failed to create new category");
			}

			@Override
			public void onSuccess(Long result) {
				category.setId(result);
				if(createNew)
					((AdminPlace)place.getPreviousPlace()).getCategory().getChildCategories().add(0, category);
				goTo(place.getPreviousPlace());
			}
		});
	}
	
	@Override
	protected void loadData() {
	}

	@Override
	protected void onBackButtonPressed() {
		if (SlidingPanel.hideSliding()) {
			return;
		}
		goTo(place.getPreviousPlace());
	}
}

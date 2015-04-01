package com.truong.brook.client.activities.basic;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.ui.client.widget.list.celllist.HasCellSelectedHandler;
import com.truong.brook.client.activities.basic.BasicViewImpl.Layout;
import com.truong.brook.client.sliding.MenuSliding;

public interface BasicView extends IsWidget{
	Layout getLayout();
	FlowPanel getContentPanel();
	void refreshView();
	int getViewId();
	MenuSliding getMenuSliding();
	HasCellSelectedHandler getCellList();
}

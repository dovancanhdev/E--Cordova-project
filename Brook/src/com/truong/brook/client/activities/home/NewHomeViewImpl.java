package com.truong.brook.client.activities.home;

import java.util.List;

import com.google.gwt.dom.client.Style.Unit;
import com.googlecode.mgwt.ui.client.widget.list.celllist.BasicCell;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellList;
import com.truong.brook.client.activities.basic.BasicViewImpl;
import com.truong.brook.shared.Book;
import com.truong.brook.shared.IBasic;

public class NewHomeViewImpl extends BasicViewImpl implements HomeView{
	
	public NewHomeViewImpl() {	
		super();
		cellList = new CellList<IBasic>(new BasicCell<IBasic>() {

			@Override
			public String getDisplayString(IBasic model) {
				return ((Book)model).getTitle();
			}
		});
		layout.getScrollPanel().add(cellList);
		cellList.getElement().getStyle().setMarginLeft(0, Unit.PX);
		layout.getScrollPanel().setScrollingEnabledX(false);
	}
	
	@Override
	public void refreshView() {
		super.refreshView();
	}

	@Override
	public void viewBooks(List<IBasic> list) {
		cellList.render(list);
		layout.getScrollPanel().refresh();
	}
}

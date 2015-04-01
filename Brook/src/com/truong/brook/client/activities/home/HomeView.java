package com.truong.brook.client.activities.home;

import java.util.List;

import com.googlecode.mgwt.ui.client.widget.list.celllist.HasCellSelectedHandler;
import com.truong.brook.client.activities.basic.BasicView;
import com.truong.brook.shared.IBasic;

public interface HomeView extends BasicView{
	void viewBooks(List<IBasic> list);
}

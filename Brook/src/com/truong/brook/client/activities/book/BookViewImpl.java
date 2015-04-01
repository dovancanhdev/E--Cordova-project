package com.truong.brook.client.activities.book;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.tabbar.TabPanel.TabBar;
import com.truong.brook.client.Brook;
import com.truong.brook.client.ClientUtils;
import com.truong.brook.client.activities.basic.BasicViewImpl;
import com.truong.brook.shared.Book;

public class BookViewImpl extends BasicViewImpl implements BookView {
	private static CategoryViewImplUiBinder uiBinder = GWT
			.create(CategoryViewImplUiBinder.class);

	interface CategoryViewImplUiBinder extends UiBinder<Widget, BookViewImpl> {
	}

	@UiField
	protected VerticalPanel containerPanel;
	@UiField
	protected VerticalPanel contaninerPanelHead;
	@UiField
	protected Image imageBook;
	@UiField
	protected Label txtNameBook;
	@UiField
	protected Label txtAuthor;
	@UiField
	protected Label txtIntrodue;

	public BookViewImpl() {
		super();
		this.layout.getScrollPanel().setWidget(uiBinder.createAndBindUi(this));
		layout.getHeaderPanel().getBackButton().setVisible(true);

	}

	@Override
	public void refreshView() {
		super.refreshView();
	}

	@Override
	public void viewBooks(Book book) {
		Brook.getAudioPlayer().initAudio(book.getStreamUrl(),
				ClientUtils.getScreenWidth());

		HTML author = new HTML(book.getAuthor());
		HTML description = new HTML(book.getDescription());
		containerPanel.add(author);
		containerPanel.add(description);
		containerPanel.add(Brook.getAudioPlayer().isWidget());

		containerPanel.setCellHorizontalAlignment(author,
				HasAlignment.ALIGN_CENTER);
		containerPanel.setCellHorizontalAlignment(description,
				HasAlignment.ALIGN_CENTER);
		containerPanel.setCellHorizontalAlignment(Brook.getAudioPlayer()
				.isWidget(), HasAlignment.ALIGN_CENTER);

		imageBook.setUrl("images/imagesaudio.png");
		txtNameBook.setText("the Book very good");
		txtAuthor.setText("Tác Giả: book ver 1111");
		txtIntrodue.setText("hôm nay trời đẹp, mưa bay tả tơi ướt hết quần áo");

	}

}

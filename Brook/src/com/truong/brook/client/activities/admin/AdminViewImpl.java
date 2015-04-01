package com.truong.brook.client.activities.admin;

import java.util.ArrayList;
import java.util.List;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.ButtonCell;
import com.github.gwtbootstrap.client.ui.CellTable;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.SafeHtmlCell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.truong.brook.client.Brook;
import com.truong.brook.client.ClientUtils;
import com.truong.brook.client.event.EditBookEvent;
import com.truong.brook.client.event.EditCategoryEvent;
import com.truong.brook.client.event.SelectBookEvent;
import com.truong.brook.client.event.SelectCategoryEvent;
import com.truong.brook.shared.Book;
import com.truong.brook.shared.Category;
import com.truong.brook.shared.IBasic;

public class AdminViewImpl implements AdminView{
	private CellTable<IBasic> wordTable;
	private ListDataProvider<IBasic> dataProvider;
	private VerticalPanel panel;
	private CaptionPanel captionPanel;
	private ScrollPanel scrollPanel;
	private List<IBasic> cards;
	private int pageSize = 10;
	private Button createCategoryButton;
	private Button createBookButton;
	
	public AdminViewImpl() {	
		super();
		createCategoryButton = new Button("Create Category");
		createBookButton = new Button("Create Book");
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.add(createCategoryButton);
		hPanel.add(createBookButton);
		createCategoryButton.setType(ButtonType.INFO);
		createBookButton.setType(ButtonType.INFO);
		
		scrollPanel = new ScrollPanel();
		captionPanel = new CaptionPanel();
		captionPanel.setWidth("100%");
		panel = new VerticalPanel();
		panel.setWidth("100%");
		captionPanel.add(panel);
		panel.add(hPanel);
		panel.setCellHorizontalAlignment(hPanel, HasHorizontalAlignment.ALIGN_RIGHT);
		panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		wordTable = new CellTable<IBasic>();
		wordTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		wordTable.setWidth("100%", true);

		dataProvider = new ListDataProvider<IBasic>();
		dataProvider.addDataDisplay(wordTable);
		
		SimplePager pagerTop;
		SimplePager.Resources paResourcesTop = GWT
				.create(SimplePager.Resources.class);
		pagerTop = new SimplePager(TextLocation.CENTER, paResourcesTop, true,
				0, true);
		pagerTop.setDisplay(wordTable);
		pagerTop.setPageSize(pageSize);

		SimplePager pagerBottom;
		SimplePager.Resources paResourcesBottom = GWT
				.create(SimplePager.Resources.class);
		pagerBottom = new SimplePager(TextLocation.CENTER, paResourcesBottom,
				true, 0, true);
		pagerBottom.setDisplay(wordTable);
		pagerBottom.setPageSize(pageSize);
		panel.add(pagerTop);
		scrollPanel.setWidth("900px");
		scrollPanel.setHeight("400px");
		scrollPanel.add(wordTable);
		panel.add(scrollPanel);
		panel.add(pagerBottom);
		addColumn();
	}
	
	private void addColumn() {
		SafeHtmlCell idCell = new SafeHtmlCell();
		Column<IBasic, SafeHtml> idCol = new Column<IBasic, SafeHtml>(idCell) {
			@Override
			public SafeHtml getValue(IBasic w) {
				SafeHtmlBuilder frontText = new SafeHtmlBuilder();
				String s = "";
				if(w instanceof Category) {
					s = ((Category)w).getId() + "";
				}
				else if(w instanceof Book) {
					s = ((Book)w).getId() + "";
				}
				frontText.appendHtmlConstant(s);
				return frontText.toSafeHtml();
			}
		};

		wordTable.addColumn(idCol, "ID");
		wordTable.setColumnWidth(idCol, "100px");
		
		SafeHtmlCell frontTextCell = new SafeHtmlCell();
		Column<IBasic, SafeHtml> frontTextCol = new Column<IBasic, SafeHtml>(frontTextCell) {
			@Override
			public SafeHtml getValue(IBasic w) {
				SafeHtmlBuilder frontText = new SafeHtmlBuilder();
				String s = "";
				if(w instanceof Category) {
					s = ((Category)w).getTitle();
				}
				else if(w instanceof Book) {
					s = "<font color = 'blue'>" + ((Book)w).getTitle() + "</font>";
				}
				frontText.appendHtmlConstant(s);
				return frontText.toSafeHtml();
			}
		};

		wordTable.addColumn(frontTextCol, "Title");
		wordTable.setColumnWidth(frontTextCol, "200px");
		
		Column<IBasic, String> enterCol = new Column<IBasic, String>(
				new ButtonCell()) {
			@Override
			public String getValue(IBasic object) {
				return "View";
			}
		};
		enterCol.setFieldUpdater(new FieldUpdater<IBasic, String>() {
			@Override
			public void update(int index, IBasic object, String value) {
				if(object instanceof Category) 
					Brook.getClientFactory().getEventBus().fireEvent(new SelectCategoryEvent((Category)object));
				else if(object instanceof Book) 
					Brook.getClientFactory().getEventBus().fireEvent(new SelectBookEvent((Book)object));
			}
		});
		wordTable.addColumn(enterCol, "");
		wordTable.setColumnWidth(enterCol, "50px");
		
		Column<IBasic, String> editCol = new Column<IBasic, String>(
				new ButtonCell()) {
			@Override
			public String getValue(IBasic object) {
				return "Edit";
			}
		};
		editCol.setFieldUpdater(new FieldUpdater<IBasic, String>() {
			@Override
			public void update(int index, IBasic object, String value) {
				if(object instanceof Category) 
					Brook.getClientFactory().getEventBus().fireEvent(new EditCategoryEvent((Category)object));
				else if(object instanceof Book) 
					Brook.getClientFactory().getEventBus().fireEvent(new EditBookEvent((Book)object));
			}
		});
		wordTable.addColumn(editCol, "");
		wordTable.setColumnWidth(editCol, "50px");
		
		Column<IBasic, String> delete = new Column<IBasic, String>(new ButtonCell()) {
			@Override
			public String getValue(IBasic object) {
				return "Delete";
			}
		};
		delete.setFieldUpdater(new FieldUpdater<IBasic, String>() {
			@Override
			public void update(int index, IBasic object, String value) {
				//TODO: delete
			}
		});
		wordTable.addColumn(delete, "");
		wordTable.setColumnWidth(delete, "50px");
	}
	
	@Override
	public void showCategory(Category category) {
		if (category == null) {
			captionPanel.setVisible(false);
			return;
		} else {
			captionPanel.setVisible(true);
		}
		ClientUtils.log("show category: "+ category.getTitle());
		String text = "";
		this.cards = new ArrayList<IBasic>();
		if(category.getChildCategories() != null && !category.getChildCategories().isEmpty()) {
			ClientUtils.log("child categories: " + category.getChildCategories().size());
			text = " <b> Có " + category.getChildCategories().size()+ " Categories con trong \"" + category.getTitle() + "\"</b>";
			for(IBasic basic : category.getChildCategories()) {
				this.cards.add(basic);
			}
		} else if(category.getChildBooks() != null && !category.getChildBooks().isEmpty()) {
			ClientUtils.log("child books: " + category.getChildBooks().size());
			text = " <b> Có " + category.getChildBooks().size()+ " Books trong \"" + category.getTitle() + "\"</b>";
			for(IBasic basic : category.getChildBooks()) {
				this.cards.add(basic);
			}
		}
		captionPanel.setCaptionHTML(text);
		dataProvider.getList().clear();
		dataProvider.getList().addAll(cards);
		
		dataProvider.flush();
		dataProvider.refresh();
		wordTable.redraw();
	}
	
	@Override
	public void showBooks(Book book) {
		if (book == null) {
			captionPanel.setVisible(false);
			return;
		} else {
			captionPanel.setVisible(true);
		}
		dataProvider.getList().clear();
		String text = "";
		if(book.getChildBooks() != null) {
			cards = book.getChildBooks();
			ClientUtils.log("child books: " + book.getChildBooks().size());
			text = " <b> Có " + book.getChildBooks().size()+ " phần trong truyện \"" + book.getTitle() + "\"</b>";
			dataProvider.getList().addAll(cards);
		}
		captionPanel.setCaptionHTML(text);
		dataProvider.flush();
		dataProvider.refresh();
		wordTable.redraw();
	}
	
	@Override
	public Widget asWidget() {
		return captionPanel;
	}
	
	@Override
	public void refresh() {
		dataProvider.flush();
		dataProvider.refresh();
		wordTable.redraw();
	}

	@Override
	public HasClickHandlers getCreateCategoryButton() {
		return createCategoryButton;
	}

	@Override
	public HasClickHandlers getCreateBookButton() {
		return createBookButton;
	}
}

package com.truong.brook.client.view;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.truong.brook.client.ClientUtils;
import com.truong.brook.client.CssToken;
import com.truong.brook.client.resource.BHClientBundleBaseTheme;

public class BhHeaderPanel extends HorizontalPanel {
	private HorizontalPanel leftPanel = new HorizontalPanel();
	private HorizontalPanel centerPanel = new HorizontalPanel();
	private HorizontalPanel rightPanel = new HorizontalPanel();
	private BHTouchImage backButton = new BHTouchImage(BHClientBundleBaseTheme.IMPL.getBHMGWTClientBundle().back());
	private BHTouchImage leftMenuButton = new BHTouchImage(BHClientBundleBaseTheme.IMPL.getBHMGWTClientBundle().menu(),
			BHClientBundleBaseTheme.IMPL.getBHMGWTClientBundle().menu_active());
		
	//image logo
	private Image img = new Image();
	
	public static final int height = 50;

	public BhHeaderPanel() {
		this.addStyleName("bh-header-panel");
		this.setHeight(height + "px");
		//sua
		backButton.setPixelSize(40,40);
		//THEM
		backButton.setStyleName(CssToken.BUTTON_BACK);
			
		leftMenuButton.setPixelSize(height,height);
		leftPanel.setWidth("100px");
		//rightPanel.setWidth("50px");
		leftPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		rightPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		rightPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		centerPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		centerPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		leftPanel.setHeight(height + "px");
		centerPanel.setHeight(height + "px");
		rightPanel.setHeight(height + "px");
		centerPanel.setWidth("100%");
		this.add(leftPanel);
		this.add(centerPanel);
		this.add(rightPanel);
		this.setCellWidth(leftPanel, "20px");
		this.setCellWidth(rightPanel, "100px");
		this.setCellHorizontalAlignment(rightPanel, HasHorizontalAlignment.ALIGN_RIGHT);
		leftPanel.add(backButton);
		leftPanel.add(leftMenuButton);
//		rightPanel.getElement().getStyle().setProperty("borderLeft", "1px solid #C78100");
//		leftPanel.getElement().getStyle().setProperty("borderRight", "1px solid #C78100");
		//leftPanel.add(homeButton);
		this.setWidth("100%");
		//notification.setSize("30px", "30px");
		//image
		img.setUrl("images/logoHeader.png");
		img.setWidth("100%");
		rightPanel.add(img);
	}

//	public static ChatWindow getChatPanel() {
//		if (chatWindow == null)
//			chatWindow = new ChatWindow();
//		return chatWindow;
//	}
	
	public void	setLeftWidget(Widget left) {
		leftPanel.clear();
		leftPanel.add(left);
	}
	public void	setRightWidget(Widget right) {
		rightPanel.clear();
		rightPanel.add(right);
	}
	
	public void setCenter(String text) {
		centerPanel.clear();
		centerPanel.add(new HTML("<div style='color: #6D1B1a;font-size:18px; text-overflow: ellipsis;overflow: hidden;white-space: nowrap !important; max-width:"+(ClientUtils.getScreenWidth()-120)+"px;'>" + text + "</div>"));
	}

	public HorizontalPanel getLeftPanel() {
		return leftPanel;
	}

	public HorizontalPanel getCenterPanel() {
		return centerPanel;
	}

	public HorizontalPanel getRightPanel() {
		return rightPanel;
	}

	public BHTouchImage getBackButton() {
		return backButton;
	}

	public BHTouchImage getLeftMenuButton() {
		return leftMenuButton;
	}

}
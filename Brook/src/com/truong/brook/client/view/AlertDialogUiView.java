package com.truong.brook.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.widget.animation.Animation;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.dialog.ConfirmDialog.ConfirmCallback;
import com.googlecode.mgwt.ui.client.widget.dialog.overlay.PopinDialogOverlay;
import com.truong.brook.client.ClientUtils;
import com.truong.brook.client.CssToken;

public class AlertDialogUiView extends PopinDialogOverlay {

	private static AlertDialogUiBinder uiBinder = GWT
			.create(AlertDialogUiBinder.class);
	
	@UiField public Button btnLeft;
	@UiField public Button btnRight;
	@UiField public VerticalPanel contentPanel;
	@UiField public HorizontalPanel topPanel;
	@UiField public HTML titleHTML;
	@UiField public VerticalPanel  mainPanel;
	@UiField public HorizontalTouchPanel closeBtn;
	@UiField public HorizontalPanel controlPanel;
	@UiField public HorizontalPanel buttonLeftPanel;
	@UiField public HorizontalPanel buttonRightPanel;
	public static AlertDialogUiView currentDialogUiView  =null;
	
	private int fontSize =ClientUtils.getScreenWidth()/20;

	interface AlertDialogUiBinder extends UiBinder<Widget, AlertDialogUiView> {
	}

	public AlertDialogUiView() {
		super();
		super.add(uiBinder.createAndBindUi(this));
		super.center();
		btnLeft.getElement().getStyle().setFontSize(fontSize, Unit.PX);
		btnRight.getElement().getStyle().setFontSize(fontSize, Unit.PX);
		mainPanel.setStyleName(CssToken.ALERT_DIALOG, true);
		if(AlertDialogUiView.currentDialogUiView != null) 
			AlertDialogUiView.currentDialogUiView.hide();
		AlertDialogUiView.currentDialogUiView = this; 
	}
	
	public AlertDialogUiView(String title, Widget content, String leftBtn, String rightBtn, final ConfirmCallback callback) {
		this();
		closeBtn.setVisible(false);
		boolean isShowLeftButton = leftBtn!=null && !leftBtn.isEmpty();
		boolean isShowRightButton = rightBtn!=null && !rightBtn.isEmpty();
		if(isShowLeftButton) {
			btnLeft.getElement().getStyle().setFontSize(fontSize, Unit.PX);
			btnLeft.setText(leftBtn);
		} else {
			btnLeft.setVisible(false);
			buttonLeftPanel.removeFromParent();
			controlPanel.setCellWidth(btnLeft, "0px");
		}
		if(isShowRightButton) {
			btnRight.getElement().getStyle().setFontSize(fontSize, Unit.PX);
			btnRight.setText(rightBtn);
		} else {
			btnRight.setVisible(false);
			buttonRightPanel.removeFromParent();
			controlPanel.setCellWidth(btnRight, "0px");
		}
		if (content !=null) {
			contentPanel.add(content);
		}
		btnLeft.addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				hide();
				if (callback !=null){
					callback.onOk();
				}
			}
		});
		
		btnRight.addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				hide();
				if (callback !=null){
					callback.onCancel();
				}
			}
		});
		titleHTML.setHTML(title);
		bind();
	}
	
	public AlertDialogUiView(String title, Widget content) {
		this();
		closeBtn.setVisible(false);
		titleHTML.setHTML(title);
		if (content !=null) {
			contentPanel.add(content);
		}
		controlPanel.setVisible(false);
		bind();
	}
	public AlertDialogUiView(String title, Widget content, String leftBtn, String rightBtn, int timeClose, final ConfirmCallback callback) {
		this(title, content, leftBtn, rightBtn, callback);
		if(timeClose > 0)
			new Timer() {
				
				@Override
				public void run() {
					if(AlertDialogUiView.currentDialogUiView != null) 
						AlertDialogUiView.currentDialogUiView.hide();
				}
			}.schedule(timeClose);
	}
	
	public AlertDialogUiView(String title, Widget content, String leftBtn, String rightBtn, String colorTheme, final ConfirmCallback callback) {
		this(title, content, leftBtn, rightBtn, callback);
		topPanel.getElement().getStyle().setBackgroundColor(colorTheme);
		btnRight.getElement().getStyle().setColor(colorTheme);
		btnLeft.getElement().getStyle().setColor(colorTheme);
	}
	
	public AlertDialogUiView(String title, Widget content,boolean enableClose) {
		this();
		closeBtn.setVisible(enableClose);
		titleHTML.setHTML(title);
		if (content !=null) {
			contentPanel.add(content);
		}
		controlPanel.setVisible(false);
		bind();
		super.center();
	}
	
	public AlertDialogUiView(String title, Widget content,boolean enableClose, String colorTheme) {
		this();
		closeBtn.setVisible(enableClose);
		titleHTML.setHTML(title);
		if (content !=null) {
			contentPanel.add(content);
		}
		controlPanel.setVisible(false);
		bind();
		topPanel.getElement().getStyle().setBackgroundColor(colorTheme);
		btnRight.getElement().getStyle().setColor(colorTheme);
		btnLeft.getElement().getStyle().setColor(colorTheme);
		super.center();
		
	}
	
	public AlertDialogUiView(String title, Widget content,boolean enableClose, String colorTheme, int timeClose) {
		this(title, content, enableClose, colorTheme);
		if(timeClose > 0)
			new Timer() {
				
				@Override
				public void run() {
					if(AlertDialogUiView.currentDialogUiView != null) 
						AlertDialogUiView.currentDialogUiView.hide();
				}
			}.schedule(timeClose);
		
	}
	
	private void bind(){
		closeBtn.addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				hide();
			}
		});
	}
	
	@Override
	public void hide() {
		super.hide();
	}
	
	@Override
	protected Animation getHideAnimation() {
		if(ClientUtils.isFireFoxBrowser()) return null;
		else return super.getHideAnimation();
	}
	
	@Override
	protected Animation getShowAnimation() {
		if(ClientUtils.isFireFoxBrowser()) return null;
		else return super.getShowAnimation();
	}
	
	public VerticalPanel getMainPanel() {
		return mainPanel;
	}
	
	public static void hideAll() {
		if(AlertDialogUiView.currentDialogUiView != null) {
			AlertDialogUiView.currentDialogUiView.hide();
			AlertDialogUiView.currentDialogUiView = null;
		}
	}
}

package com.truong.brook.client.view;

import com.google.gwt.dom.client.Style.Float;
import com.google.gwt.dom.client.Style.TextAlign;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.googlecode.mgwt.ui.client.widget.touch.TouchPanel;

/**
 * @author hungzombxd
 *
 */
public class KSImageButton extends TouchPanel implements KSWidget {

	private Image imageButton = null;
	private HTML titleButton = null;
	private ImageResource imageActiveResource = null;
	private ImageResource imageResource = null;
	private String urlImageButton = null;
	private boolean isActive = false;
	public KSImageButton() {
		super();
		setStyleName(getKSClassName(), true);
	}
	
	public KSImageButton(String title) {
		this();
		if(title != null) {
			titleButton = new HTML();
			this.add(titleButton);
		}
	}
	
	public KSImageButton(String imageButton, String titleButton) {
		this();
		this.urlImageButton = imageButton;
		this.imageButton = new Image(imageButton);
		this.imageButton.getElement().getStyle().setMargin(5, Unit.PX);
		this.imageButton.getElement().getStyle().setFloat(Float.LEFT);
		this.add(this.imageButton);
		if(titleButton != null) {
			this.titleButton = new HTML(titleButton);
			this.titleButton.getElement().getStyle().setPaddingTop(10, Unit.PX);
			this.titleButton.getElement().getStyle().setTextAlign(TextAlign.LEFT);
			this.add(this.titleButton);
		}
	}
	
	public KSImageButton(ImageResource imageButton, ImageResource imageActive, String titleButton) {
		this();
		this.imageResource = imageButton;
		this.imageActiveResource= imageActive;
		this.imageButton = new Image(imageButton);
		this.imageButton.getElement().getStyle().setMargin(5, Unit.PX);
		this.imageButton.getElement().getStyle().setFloat(Float.LEFT);
		this.add(this.imageButton);
		if(titleButton != null) {
			this.titleButton = new HTML(titleButton);
			this.titleButton.getElement().getStyle().setPaddingTop(10, Unit.PX);
			this.titleButton.getElement().getStyle().setTextAlign(TextAlign.LEFT);
			this.add(this.titleButton);
		}
	}
	
	public KSImageButton(ImageResource imageButton, ImageResource imageActive, String titleButton, int size) {
		this(imageButton, imageActive, titleButton);
		this.imageButton.setPixelSize(size, size);
		this.setHeight(size + 10 + "px");
		this.titleButton.getElement().getStyle().setPaddingTop(size/2, Unit.PX);
	}
	
	public KSImageButton(String imageButton, String titleButton, int size) {
		this(imageButton, titleButton);
		this.imageButton.setPixelSize(size, size);
		this.setHeight(size + 10 + "px");
	}
	
	public void setActive(boolean isActive) {
		this.isActive= isActive;
		if(isActive) {
			if(imageActiveResource != null)
				this.imageButton.setResource(imageActiveResource);
			this.setStyleName(getKSClassName() + "-active", true);
		} else {
			if(imageResource!= null) {
				this.imageButton.setResource(imageResource);
			} else if(urlImageButton != null) {
				this.imageButton.setUrl(urlImageButton);
			}
			this.removeStyleName(getKSClassName() + "-active");
		}
	}
	
	public void setImage(String imageButton, int size) {
		if(imageButton.isEmpty()) {
			if(this.imageButton != null)
				this.imageButton.setVisible(false);
			return;
		}
		this.urlImageButton = imageButton;
		this.setHeight(size + 10 + "px");
		if(this.imageButton == null) {
			this.imageButton = new Image();
			this.insert(this.imageButton, 0);
			this.imageButton.getElement().getStyle().setMargin(5, Unit.PX);
			this.imageButton.getElement().getStyle().setFloat(Float.LEFT);
		}
		this.imageButton.setUrl(imageButton);
		this.imageButton.setPixelSize(size, size);
	}
	
	public void setTitle(String title) {
		if(this.titleButton == null) {
			this.titleButton = new HTML();
			this.titleButton.getElement().getStyle().setPaddingTop(10, Unit.PX);
			this.add(titleButton);
		} 
		this.titleButton.setHTML(title);
	}
	
	public boolean isActive() {
		return this.isActive;
	}
	
	
	@Override
	public String getKSClassName() {
		return "KS-ImageButton";
	}
	
	
	
}

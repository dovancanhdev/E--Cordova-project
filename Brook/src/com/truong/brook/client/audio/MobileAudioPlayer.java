package com.truong.brook.client.audio;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwtphonegap.client.media.MediaStatus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.truong.brook.client.ClientUtils;
import com.truong.brook.client.CssToken;
import com.truong.brook.client.resource.BHClientBundleBaseTheme;
import com.truong.brook.client.view.BHTouchImage;
import com.truong.brook.client.view.KSSlider;
import com.truong.brook.client.view.Toaster;

public class MobileAudioPlayer extends AudioPlayer{
	private HorizontalPanel timePanel;
	private HorizontalPanel volumePanel;
	private VerticalPanel vPanel;
	private HTML stateHtml;
	private KSSlider timeSlider = new KSSlider();
	private BHTouchImage playButton = null;
	private HTML infoHtml;
	private int currentStatus = MediaStatus.MEDIA_NONE;
	private boolean initSlide = false;
	private JavaScriptObject currentAudio;
	private String currentAudioUrl;
	private JavaScriptObject readyStateInterval;
	
	public MobileAudioPlayer() {
		timePanel = new HorizontalPanel();
		vPanel = new VerticalPanel();
		volumePanel = new HorizontalPanel();
		stateHtml = new HTML();
		playButton = new BHTouchImage(BHClientBundleBaseTheme.IMPL.getBHMGWTClientBundle().playMedia(),
				BHClientBundleBaseTheme.IMPL.getBHMGWTClientBundle().pauseMedia());
		playButton.setPixelSize(35, 35);
		
		infoHtml = new HTML();
		timeSlider.getElement().setId("timeSlider");
		timeSlider.getElement().setId("volumeSlider");
		playButton.addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				play();
			}
		});
		
		timePanel.setSpacing(5);
		timePanel.setStyleName(CssToken.AUDIO_PLAYER_PANEL, true);
		volumePanel.setSpacing(5);
		volumePanel.setStyleName(CssToken.AUDIO_PLAYER_PANEL, true);
		
		vPanel.setSpacing(5);
		vPanel.setStyleName(CssToken.AUDIO_PLAYER_PANEL, true);
		playButton.setStyleName(CssToken.AUDIO_PLAYER_PLAYBUTTON, true);
		timePanel.add(playButton);
		timePanel.add(timeSlider);
		timePanel.setCellWidth(playButton, "35px");
		timeSlider.setWidth(ClientUtils.getScreenWidth()-70 + "px");
		timePanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		timePanel.setCellVerticalAlignment(timeSlider, HasVerticalAlignment.ALIGN_MIDDLE);
		timePanel.setCellVerticalAlignment(playButton, HasVerticalAlignment.ALIGN_MIDDLE);
		
		vPanel.add(stateHtml);
		vPanel.add(timePanel);
		vPanel.add(volumePanel);
		
		vPanel.setCellHorizontalAlignment(stateHtml, HasAlignment.ALIGN_CENTER);
		vPanel.setCellHorizontalAlignment(timePanel, HasAlignment.ALIGN_CENTER);
		vPanel.setCellHorizontalAlignment(volumePanel, HasAlignment.ALIGN_CENTER);
	}
	
	protected void onVolumeChange(Integer value) {
		volumeChange(currentAudio, value);
	}

	public void play() {
		ClientUtils.log("Current status" + currentStatus);
		switch (currentStatus) {
		case MediaStatus.MEDIA_NONE:
			Toaster.showToast("Audio is loading, please wait!");
			break;
		case MediaStatus.MEDIA_STARTING:
			playAudio(currentAudio);
			break;
		case MediaStatus.MEDIA_PAUSED:
			playAudio(currentAudio);
			break;
		case MediaStatus.MEDIA_RUNNING:
			pauseAudio(currentAudio);
			break;
		case MediaStatus.MEDIA_STOPPED:
			playAudio(currentAudio);
			break;
		}
	}

	private int getCurrentStatus() {
		return 0;
	}
	
	@Override
	public void initAudio(final String url,final int width) {
		if(timeSlider != null)
			timeSlider.setValue(0);
		infoHtml.setHTML("0/0");
		String newUrl = "";
		if(url.startsWith("file://"))
			newUrl = url.replace("file://", "");
		if(url.startsWith("localhost"))
			newUrl = url.replace("localhost", "");
		stopAudio();
		ClientUtils.log("Init audio " + newUrl);
		initSlide = false;
		currentAudio = getAudio(url, readyStateInterval);
		this.currentAudioUrl = url;
		initView(width);
	}
	
	private void setMaxSlide() {
		if(!initSlide) {
			int maxDuration = 0;
			try{
				maxDuration = (int)getDuration(currentAudio);
			}catch (Exception e){
			}
			timeSlider.setMax(maxDuration);
			initSlide = true;
		}
	}
	
	private void initView(int width) {
		if(width > 0)
			timePanel.setWidth(width + "px");
		playButton.setActive(false);
	}

	@Override
	public boolean stopAudio() {
		if(currentAudio!=null) {
			ClientUtils.log("Stoped audio");
			stopAudio(currentAudio,readyStateInterval);
			playButton.setActive(false);
			currentStatus = MediaStatus.MEDIA_NONE;
			timeSlider.setValue(0);
			return true;
		}
		return false;
	}
	
	@Override
	public Widget isWidget() {
		return vPanel;
	}
	
	private void pause() {
		pauseAudio(currentAudio);
	}
	
//	private native void setSrc(String url,JavaScriptObject myaudio ) /*-{
//		myaudio.src = url;
//		myaudio.load();
//	}-*/;	
	
	private native JavaScriptObject getAudio(String audioUrl, JavaScriptObject readyStateInterval ) /*-{
		$wnd.console.log('get new audio with url: ' + audioUrl);
		var myaudio = new Audio(audioUrl);
		var app = this;
		readyStateInterval = setInterval(function(){
			$wnd.console.log('myaudio.readyState: ' + myaudio.readyState);
			 if (myaudio.readyState < 1) {
				$wnd.console.log('myaudio.readyState: ' + myaudio.readyState + " not ready");
				 app.@com.truong.brook.client.audio.MobileAudioPlayer::onNotReady()();
			 }
			 else {
			 	clearInterval(readyStateInterval);
			 	$wnd.console.log('myaudio.readyState: ' + myaudio.readyState + " ready");
			 	app.@com.truong.brook.client.audio.MobileAudioPlayer::onReady()();
			 }
		},1000);
		myaudio.addEventListener("timeupdate", function() {
			 app.@com.truong.brook.client.audio.MobileAudioPlayer::onTimeUpdate()();
		}, false);
		myaudio.addEventListener("error", function() {
			 $wnd.console.log('myaudio ERROR');
		}, false);
		myaudio.addEventListener("canplay", function() {
			 $wnd.console.log('myaudio CAN PLAY: ' + myaudio.duration);
			 app.@com.truong.brook.client.audio.MobileAudioPlayer::setMaxSlide()();
		}, false);
		myaudio.addEventListener("waiting", function() {
			 $wnd.console.log('audio WAITING');
		}, false);
		myaudio.addEventListener("pause", function() {
			 $wnd.console.log('audio PAUSE');
			 app.@com.truong.brook.client.audio.MobileAudioPlayer::onPause()();
		}, false);
		myaudio.addEventListener("playing", function() {
			 $wnd.console.log('audio playing');
			 app.@com.truong.brook.client.audio.MobileAudioPlayer::onPlay()();
		}, false);
		myaudio.addEventListener("ended", function() {
			 $wnd.console.log('myaudio ENDED');
			 app.@com.truong.brook.client.audio.MobileAudioPlayer::onEnded()();
			 app.@com.truong.brook.client.audio.MobileAudioPlayer::stopAudio()();
		}, false);
		return myaudio;
	}-*/;
	
	private native void stopAudio(JavaScriptObject audio, JavaScriptObject readyStateInterval) /*-{
		if(audio){
			audio.pause();
			audio.currentTime = 0;
		}
		if(readyStateInterval)
			clearInterval(readyStateInterval);
	}-*/;
	
	private native void pauseAudio(JavaScriptObject myaudio) /*-{
		myaudio.pause();
	}-*/;
	
	private native void playAudio(JavaScriptObject myaudio) /*-{
		$wnd.console.log('playAudio');
		myaudio.play();
	}-*/;
	
	private native double getDuration(JavaScriptObject myaudio) /*-{
		if(myaudio) {
			$wnd.console.log("Duration: " + myaudio.duration);
			return myaudio.duration;
		}
		else  {
			$wnd.console.log("Duration: 0");
			return 0;
		}
	}-*/;
	
	private native double getCurrentTime(JavaScriptObject myaudio) /*-{
		if(myaudio)
			return myaudio.currentTime;
		else return 0;
	}-*/;
	
	private native void volumeChange(JavaScriptObject myaudio, int volume) /*-{
		if(myaudio) {
			$wnd.console.log('volumeChange');
			myaudio.volume = volume/100;
		}
	}-*/;
	
	private void onPlay() {
		currentStatus = MediaStatus.MEDIA_RUNNING;
		setMaxSlide();
		playButton.setActive(true);
	}
	
	private void onPause() {
		currentStatus = MediaStatus.MEDIA_PAUSED;
		playButton.setActive(false);
	}
	
	private void onEnded() {
		currentStatus = MediaStatus.MEDIA_STOPPED;
	}
	
	private void onTimeUpdate() {
		int currentTime = (int) getCurrentTime(currentAudio);
		timeSlider.setValue(currentTime);
	}
	
	private void onReady() {
		stateHtml.setVisible(false);
		currentStatus = MediaStatus.MEDIA_STARTING;
	}
	
	private void onNotReady() {
		stateHtml.setVisible(true);
		stateHtml.setHTML("Loading...");
		currentStatus = MediaStatus.MEDIA_NONE;
	}
}

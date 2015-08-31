package com.loser.game.bubblefire.android;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.bluebirdaward.dangerball.MainMenu;
import com.bluebirdaward.dangerball.screens.IActivityRequestHandler;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class AndroidLauncher extends AndroidApplication implements IActivityRequestHandler {
	private static final String AD_BANNER_UNIT_ID = "ca-app-pub-6288116306885369/2207249930";
	private static final String AD_INTERSTITIAL_UNIT_ID = "ca-app-pub-6288116306885369/3683983138";
	
	private boolean isShowInterstitial;
	private AdRequest adRequestBuilder;
	protected AdView adView;
	private InterstitialAd mInterstitialAd;
	protected View gameView;
	private final byte SHOW_ADS = 1;
	private final byte HIDE_ADS = 0;

	@SuppressLint("HandlerLeak") public Handler handler = new Handler() {
		@Override public void handleMessage(Message msg) {
			switch(msg.what) {
			case SHOW_ADS: adView.setVisibility(View.VISIBLE);
			break;
			case HIDE_ADS: adView.setVisibility(View.GONE);
			break;
			}
		}
	};

	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        setContentView(R.layout.main);
    // Create the layout
        RelativeLayout layout = new RelativeLayout(this);
        
    // Create the libgdx View
	    AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
	    cfg.useAccelerometer = false;
	    cfg.useCompass = false;
	    View gameView = initializeForView(new MainMenu(this), cfg);
        
    // Create and setup the AdMob view
	    adView = new AdView(this);
	    adView.setAdSize(AdSize.SMART_BANNER);
	    adView.setAdUnitId(AD_BANNER_UNIT_ID);
	    adView.setId(12345);
	    adView.setBackgroundColor(Color.TRANSPARENT);
    
    // Create InterstitialAd
	    mInterstitialAd = new InterstitialAd(this);
	    mInterstitialAd.setAdUnitId(AD_INTERSTITIAL_UNIT_ID);
	    adView.setId(123456);
    
    // Add the libgdx view
    	layout.addView(gameView);
    // Add the AdMob view
    	RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(
    		RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	    adParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
	    adParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
    
    layout.addView(adView, adParams);

    // Hook it all up
	    setContentView(layout);
	    AdRequest adRequest = new AdRequest.Builder().build();
	    adView.loadAd(adRequest);
    
    // load InterstitialAd
	    adRequestBuilder = new AdRequest.Builder().build();
	    mInterstitialAd.loadAd(adRequestBuilder);
	    isShowInterstitial = false;
	}
	@Override public void onBackPressed () {
		showInterstitial();
    	if (isShowInterstitial || !mInterstitialAd.isLoaded()) {
			super.onBackPressed();
			isShowInterstitial = false;
		}
	}
	
	private void showInterstitial(){
    	if ((mInterstitialAd != null && mInterstitialAd.isLoaded()) && !isShowInterstitial ) {
    		mInterstitialAd.show();
    		isShowInterstitial = true;
    	} else return;

    }
	
	@Override public void onDestroy() {
		if (adView != null) adView.destroy();
		super.onDestroy();
	}

	@Override public void showAds(boolean show) {
		handler.sendEmptyMessage(show ? SHOW_ADS : HIDE_ADS);
	}
}

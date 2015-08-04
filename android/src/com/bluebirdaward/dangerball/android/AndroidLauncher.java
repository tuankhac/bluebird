package com.bluebirdaward.dangerball.android;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.bluebirdaward.dangerball.MainMenu;
import com.bluebirdaward.dangerball.screens.IActivityRequestHandler;
import com.bluebirdaward.dangerball.screens.MainScreen;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;

public class AndroidLauncher extends AndroidApplication implements IActivityRequestHandler {
	private static final String AD_BANNER_UNIT_ID = "ca-app-pub-3940256099942544/6300978111";

	protected AdView adView;
	protected View gameView;
	private final int SHOW_ADS = 1;
	private final int HIDE_ADS = 0;

	@SuppressLint("HandlerLeak") public Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
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
		RelativeLayout layout = new RelativeLayout(this);

		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		cfg.useAccelerometer = false;
		cfg.useCompass = false;
		gameView = initializeForView(new MainMenu(this), cfg);

		adView = new AdView(this);
		adView.setAdSize(AdSize.SMART_BANNER);
		adView.setAdUnitId(AD_BANNER_UNIT_ID);
		adView.setId(12345);
		adView.setBackgroundColor(Color.TRANSPARENT);

		adView.setId(123456);

		layout.addView(gameView);
		RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		adParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
		adParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);

		layout.addView(adView, adParams);

		setContentView(layout);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);

	}
	@Override public void onBackPressed () {
		MainScreen.exit ++;
		if(MainScreen.exit < 2)
			Toast.makeText(getContext(), "Double press to exit", Toast.LENGTH_SHORT).show();
		if(MainScreen.exit >= 2){
			super.onBackPressed();
		}
	}
	@Override public void onResume() {	super.onResume();	}
	@Override public void onPause() { super.onPause();	}
	@Override public void onDestroy() {
		MainScreen.exit = 0;
		MainScreen.timer = 0;
		if (adView != null) adView.destroy();
		super.onDestroy();
	}

	@Override public void showAds(boolean show) {
		handler.sendEmptyMessage(show ? SHOW_ADS : HIDE_ADS);
	}
}
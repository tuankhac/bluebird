package com.loser.game.bubblefire.android;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class SplashScreen extends Activity {

    private static int SPLASH_TIME_OUT = 2000;
 
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        setContentView(R.layout.splash_screen);
        
        ImageView imgAssets = (ImageView) findViewById(R.id.imgAssets);
        
        // To load image
             try {
              // get input stream
            	InputStream ims = getAssets().open("images/bluebird_logo.jpg");
              // create drawable from stream
              Drawable d = Drawable.createFromStream(ims, null);
              // set the drawable to imageview
              imgAssets.setImageDrawable(d);
             } catch (IOException e) {throw new RuntimeException(e);}
             
        new Handler().postDelayed(new Runnable() {
            /** Showing splash screen with a timer. This will be useful when you
             * 	want to show case your app logo / company
             */
            @Override public void run() {
                // This method will be executed once the timer is over
            	Intent i = new Intent(SplashScreen.this, AndroidLauncher.class);
            	startActivity(i);
                // close this activity
                finish();
                onDestroy();
            }
        }, SPLASH_TIME_OUT);
        
    }
}

package com.bluebirdaward.dangerball.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.bluebirdaward.dangerball.MainMenu;
import com.bluebirdaward.dangerball.screens.IActivityRequestHandler;

public class DesktopLauncher implements IActivityRequestHandler{
	private static final IActivityRequestHandler IActivityRequestHandler = null;

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 480;
		config.height = 700;
		new LwjglApplication(new MainMenu(IActivityRequestHandler), config);
	}

	@Override
	public void showAds(boolean show) {
		// TODO Auto-generated method stub
		
	}
}

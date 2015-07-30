package com.bluebirdaward.dynaball;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.bluebirdaward.dynaball.logic.Assets;
import com.bluebirdaward.dynaball.screens.MainScreen;
import com.bluebirdaward.dynaball.utils.Audios;

public class MainMenu extends Game{
	
	@Override	public void create() {
		Assets.instance.init(new AssetManager());
		Audios.audio.initAudio();
		setScreen(new MainScreen());
		
	}
}

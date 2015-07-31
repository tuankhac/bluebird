package com.bluebirdaward.dynaball;
/*
 *  created by tuankhac 
 *  group losers
 *  update 31/7/2015
 * */
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.bluebirdaward.dynaball.logic.Assets;
import com.bluebirdaward.dynaball.screens.IActivityRequestHandler;
import com.bluebirdaward.dynaball.screens.MainScreen;
import com.bluebirdaward.dynaball.utils.Audios;

public class MainMenu extends Game{
	IActivityRequestHandler iActHandler;
	public MainMenu(IActivityRequestHandler iActHandler) {
		this.iActHandler = iActHandler;
	}
	public MainMenu() {}
	@Override	public void create() {
		Assets.instance.init(new AssetManager());
		Audios.audio.initAudio();
		setScreen(new MainScreen(this.iActHandler));	
	}
}

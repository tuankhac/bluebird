
package com.bluebirdaward.dangerball;
/*
 *  created by tuankhac 
 *  group losers
 *  update 31/7/2015
 * */
import com.badlogic.gdx.Game;
import com.bluebirdaward.dangerball.logic.Assets;
import com.bluebirdaward.dangerball.screens.IActivityRequestHandler;
import com.bluebirdaward.dangerball.screens.MainScreen;
import com.bluebirdaward.dangerball.utils.Audios;

public class MainMenu extends Game{
	IActivityRequestHandler iActHandler;
	public MainMenu(){}
	public MainMenu(IActivityRequestHandler iActHandler) {
		this.iActHandler = iActHandler;
	}
	@Override	public void create() {
		Assets.instance.init();
		Audios.audio.initAudio();
		setScreen(new MainScreen(this.iActHandler));	
	}
}

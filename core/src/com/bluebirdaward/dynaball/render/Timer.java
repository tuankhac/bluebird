package com.bluebirdaward.dynaball.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bluebirdaward.dynaball.logic.Assets;
import com.bluebirdaward.dynaball.utils.Constants;

public class Timer extends Actor {
	private float _elapsed = 0;
	
	public int timer = 5;
	
	public Timer() {}
	
	public void updateDeltaTime(){
		_elapsed = _elapsed + Gdx.graphics.getDeltaTime();
		if(_elapsed >= 1){
			timer = timer - 1;
			_elapsed = 0;
		}
	}
	@Override public void draw(Batch batch,float delta){
		Assets.instance.fonts.defaultSmall.draw(batch, ""+ timer, 
				Constants.APP_WIDTH/6, Constants.APP_HEIGHT - Constants.BALL_RADIUS*10);
	}
}

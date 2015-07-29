package com.bluebirdaward.dynaball.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bluebirdaward.dynaball.logic.Assets;
import com.bluebirdaward.dynaball.utils.Constants;

public class Level extends Actor {
	public int level = 0;
	public int timer = 10;
	public float rotation = 0;

	private float _elapsed = 0;
	private float _xArrow = Constants.APP_WIDTH /2 -2.2f* Constants.BARIE_WIDTH;
	private float _yArrow =  3f*Constants.BARIE_WIDTH ;
	private float _originX =  Constants.VP_WIDTH/2, _originY = 0;
	private float _height = 200f;
	private float _width = 50f;
	@Override public void draw(Batch batch,float delta){
		batch.draw(Assets.instance.assetatlas.background, 0,0,Constants.APP_WIDTH,Constants.APP_HEIGHT);
		Assets.instance.fonts.defaultSmall.draw(batch, ""+ level,
				Constants.APP_WIDTH*0.8f, Constants.APP_HEIGHT - Constants.BALL_RADIUS*10);
		Assets.instance.fonts.defaultSmall.draw(batch, ""+ timer, 
				Constants.APP_WIDTH/6, Constants.APP_HEIGHT - Constants.BALL_RADIUS*10);
		batch.draw(Assets.instance.assetatlas.arrow,_xArrow, _yArrow, _originX, _originY, _width, _height, 1, 1, rotation);
	}

	public void updateDeltaTime(){
		_elapsed = _elapsed + Gdx.graphics.getDeltaTime();
		if(_elapsed >= 1){
			timer = timer - 1;
			_elapsed = 0;
		}
	}
}

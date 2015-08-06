package com.bluebirdaward.dangerball.render;
/*
 *  created by tuankhac 
 *  group losers
 *  update 31/7/2015
 * */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bluebirdaward.dangerball.logic.Assets;
import com.bluebirdaward.dangerball.utils.Constants;

public class Level extends Actor {
	public byte level = 0;
	public byte timer = 10;
	public float rotation = 0;

	private float _elapsed = 0;
	private float _originX =  Constants.VP_WIDTH/2 + 10f, _originY = 0;
	private float _height = 200f;
	private float _width = 50f;
	
	public float _yArrow =  3f*Constants.BARIE_WIDTH ;
	public float _xArrow = Constants.APP_WIDTH /2 -_width/2;
	
	@Override public void draw(Batch batch,float delta){
		batch.draw(Assets.instance.assetatlas.set("background").get(), 0,0,Constants.APP_WIDTH,Constants.APP_HEIGHT);
		batch.draw(Assets.instance.assetatlas.set("bt_top").get(), 0, Constants.APP_HEIGHT - Constants.VP_HEIGHT, Constants.APP_WIDTH, Constants.VP_HEIGHT);
		batch.draw(Assets.instance.assetatlas.set("touch_range").get(), 0, 0, Constants.APP_WIDTH, Constants.APP_HEIGHT/3);
		Assets.instance.fonts.defaultSmall.draw(batch, ""+ level,Constants.APP_WIDTH * 0.8f, Constants.APP_HEIGHT - Constants.BALL_RADIUS*10);
		Assets.instance.fonts.defaultSmall.draw(batch, ""+ timer,Constants.APP_WIDTH/6, Constants.APP_HEIGHT - Constants.BALL_RADIUS*10);
		batch.draw(Assets.instance.assetatlas.set("arrow").get(),_xArrow , _yArrow, _originX, _originY, _width, _height, 1, 1, rotation);
	}

	public void updateDeltaTime(){
		_elapsed = _elapsed + Gdx.graphics.getDeltaTime();
		if(_elapsed >= 1){
			 timer --;
			_elapsed = 0;
		}
	}
}

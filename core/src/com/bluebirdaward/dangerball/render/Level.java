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

	private float app_width = Constants.APP_WIDTH;
	private float app_height = Constants.APP_HEIGHT;
	private float ball_radius = Constants.BALL_RADIUS;
	private float vp_height = Constants.VP_HEIGHT;
	private Assets assets = Assets.instance;
	
	private float _elapsed = 0;
	private float _originX =  Constants.VP_WIDTH/2 + 10f, _originY = 0;
	private float _height = 300f;
	private float _width = 25f;
	
	public float _yArrow =  3f*Constants.BARIE_WIDTH ;
	public float _xArrow = app_width /2 -_width/2;
	
	@Override public void draw(Batch batch,float delta){
<<<<<<< HEAD
		batch.draw(assets.assetatlas.set("background").get(), 0,0,app_width,app_height);
		batch.draw(assets.assetatlas.set("bt_top").get(), 0, app_height - vp_height, app_width, vp_height);
		batch.draw(assets.assetatlas.set("touch_range").get(), 0, 0, app_width, app_height/3);
		assets.fonts.defaultSmall.draw(batch, ""+ level,app_width * 0.8f, app_height - ball_radius*10);
		assets.fonts.defaultSmall.draw(batch, ""+ timer,app_width/6, app_height - ball_radius*10);
		batch.draw(assets.assetatlas.set("arrow").get(),_xArrow , _yArrow, _originX, _originY, _width, _height, 1, 1, rotation);
=======
		batch.draw(Assets.instance.assetatlas.set("background").get(), 0,0,Constants.APP_WIDTH,Constants.APP_HEIGHT);
		batch.draw(Assets.instance.assetatlas.set("bt_top").get(), 0, Constants.APP_HEIGHT - Constants.VP_HEIGHT, Constants.APP_WIDTH, Constants.VP_HEIGHT);
		batch.draw(Assets.instance.assetatlas.set("touch_range").get(), 0, 0, Constants.APP_WIDTH, Constants.APP_HEIGHT/3);
		Assets.instance.fonts.defaultSmall.draw(batch, ""+ level,
				Constants.APP_WIDTH*0.8f, Constants.APP_HEIGHT - Constants.BALL_RADIUS*10);
		Assets.instance.fonts.defaultSmall.draw(batch, ""+ timer, 
				Constants.APP_WIDTH/6, Constants.APP_HEIGHT - Constants.BALL_RADIUS*10);
		Assets.instance.fonts.defaultSmall.draw(batch, ""+ level,Constants.APP_WIDTH * 0.8f, Constants.APP_HEIGHT - Constants.BALL_RADIUS*10);
		Assets.instance.fonts.defaultSmall.draw(batch, ""+ timer,Constants.APP_WIDTH/6, Constants.APP_HEIGHT - Constants.BALL_RADIUS*10);
		batch.draw(Assets.instance.assetatlas.set("arrow").get(),_xArrow , _yArrow, _originX, _originY, _width, _height, 1, 1, rotation);
>>>>>>> 5c9816ea6841b230c2e1df31d3711646ff55e128
	}

	public void updateDeltaTime(){
		_elapsed = _elapsed + Gdx.graphics.getDeltaTime();
		if(_elapsed >= 1){
			 timer --;
			_elapsed = 0;
		}
	}
}

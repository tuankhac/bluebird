package com.bluebirdaward.dangerball.render;
/*
 *  created by tuankhac 
 *  group losers
 *  update 31/7/2015
 * */
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bluebirdaward.dangerball.logic.Assets;
import com.bluebirdaward.dangerball.logic.PlayerLogic;
import com.bluebirdaward.dangerball.utils.Constants;

public class Player extends Actor{
	private float _x;
	private float _y;
	private float _width;
	private float _height;
	
	private float ball_radius = Constants.BALL_RADIUS;
	
	PlayerLogic playerLogic;
	public Player(PlayerLogic playerLogic) {
		this.playerLogic = playerLogic;
	}

	@Override public void draw(Batch batch, float delta){
		batch.draw(Assets.instance.assetatlas.set("redball").get(), _x, _y, _width, _height);
	}
	
	@Override public void act(float delta) {
		super.act(delta);
		_x = transformToScreen(playerLogic.getBody().getPosition().x -  ball_radius);
		_y = transformToScreen(playerLogic.getBody().getPosition().y - ball_radius);
		_width = transformToScreen(2*ball_radius);
		_height = transformToScreen(2*ball_radius);
	}
	
	 protected float transformToScreen(float n) { return Constants.LOGIC_TO_RENDER * n; }
}

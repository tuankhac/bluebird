package com.bluebirdaward.dynaball.render;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.bluebirdaward.dynaball.logic.Assets;
import com.bluebirdaward.dynaball.logic.PlayerLogic;
import com.bluebirdaward.dynaball.utils.Constants;

public class Player extends RenderActor{
	private float _x;
	private float _y;
	private float _width;
	private float _height;
	
	public Player(PlayerLogic playerLogic) {
		super(playerLogic);
	}

	@Override public void draw(Batch batch, float delta){
		
		batch.draw(Assets.instance.assetatlas.player, _x, _y, _width, _height);
	}
	
	@Override public void act(float delta) {
		_x = transformToScreen(gameLogic.getBody().getPosition().x -  Constants.BALL_RADIUS);
		_y = transformToScreen(gameLogic.getBody().getPosition().y - Constants.BALL_RADIUS);
		_width = transformToScreen(2*Constants.BALL_RADIUS);
		_height = transformToScreen(2*Constants.BALL_RADIUS);
		super.act(delta);
	}
}

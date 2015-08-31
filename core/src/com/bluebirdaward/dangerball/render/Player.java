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
	
<<<<<<< HEAD
	private float ball_radius = Constants.BALL_RADIUS;
	
=======
	PlayerLogic playerLogic;
>>>>>>> 5c9816ea6841b230c2e1df31d3711646ff55e128
	public Player(PlayerLogic playerLogic) {
		this.playerLogic = playerLogic;
	}

	@Override public void draw(Batch batch, float delta){
		batch.draw(Assets.instance.assetatlas.set("redball").get(), _x, _y, _width, _height);
	}
	
	@Override public void act(float delta) {
		super.act(delta);
<<<<<<< HEAD
		_x = transformToScreen(gameLogic.getBody().getPosition().x -  ball_radius);
		_y = transformToScreen(gameLogic.getBody().getPosition().y - ball_radius);
		_width = transformToScreen(2*ball_radius);
		_height = transformToScreen(2*ball_radius);
=======
		_x = transformToScreen(playerLogic.getBody().getPosition().x -  Constants.BALL_RADIUS);
		_y = transformToScreen(playerLogic.getBody().getPosition().y - Constants.BALL_RADIUS);
		_width = transformToScreen(2*Constants.BALL_RADIUS);
		_height = transformToScreen(2*Constants.BALL_RADIUS);
>>>>>>> 5c9816ea6841b230c2e1df31d3711646ff55e128
	}
	
	 protected float transformToScreen(float n) { return Constants.LOGIC_TO_RENDER * n; }
}

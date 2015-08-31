package com.bluebirdaward.dangerball.render;
/*
 *  created by tuankhac 
 *  group losers
 *  update 31/7/2015
 * */
import com.badlogic.gdx.graphics.g2d.Batch;
import com.bluebirdaward.dangerball.logic.Assets;
import com.bluebirdaward.dangerball.logic.GameLogic;
import com.bluebirdaward.dangerball.utils.Constants;

public class Balloon extends RenderActor {
	private float x, y, width, height;
	private float  ball_radius = Constants.BALL_RADIUS;
	
	public Balloon(GameLogic gameLogic) {
		super(gameLogic);
	}
	
	 public void act() {
		x = transformToScreen(gameLogic.getBody().getPosition().x -  ball_radius);
		y = transformToScreen(gameLogic.getBody().getPosition().y - ball_radius);
		width = transformToScreen(2*ball_radius);
		height = transformToScreen(2*ball_radius);
	}

	@Override
	public void draw(Batch batch) {
		batch.draw(Assets.instance.assetatlas.set("greenball").get(), x, y, width, height);
	}
}

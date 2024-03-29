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

public class Bar extends RenderActor {
	private float x, y, width, height;
	
	public Bar(GameLogic gameLogic) {
		super(gameLogic);
	}
	
	 public void draw(Batch batch){
		if(gameLogic.allowHit)
			batch.draw(Assets.instance.assetatlas.set("brick_ice").get(), x, y, width, height);
		else
			batch.draw(Assets.instance.assetatlas.set("brick").get(), x, y, width, height);
	}
	
	 public void act() {
		x = transformToScreen(gameLogic.getBody().getPosition().x -  Constants.BALL_RADIUS);
		y = transformToScreen(gameLogic.getBody().getPosition().y -  Constants.BALL_RADIUS);
		width = transformToScreen(2*Constants.BALL_RADIUS);
		height = transformToScreen(2*Constants.BALL_RADIUS);
	}
}

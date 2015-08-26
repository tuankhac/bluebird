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
	
	public Balloon(GameLogic gameLogic) {
		super(gameLogic);
	}
	
<<<<<<< HEAD
	 public void draw(Batch batch){
=======
	@Override public void draw(Batch batch, float delta){
>>>>>>> b2fcd0be945d07866a34cff9663536fab150b88e
		batch.draw(Assets.instance.assetatlas.set("greenball").get(), x, y, width, height);
	}
	
	 public void act() {
		x = transformToScreen(gameLogic.getBody().getPosition().x -  Constants.BALL_RADIUS);
		y = transformToScreen(gameLogic.getBody().getPosition().y - Constants.BALL_RADIUS);
		width = transformToScreen(2*Constants.BALL_RADIUS);
		height = transformToScreen(2*Constants.BALL_RADIUS);
	}
}

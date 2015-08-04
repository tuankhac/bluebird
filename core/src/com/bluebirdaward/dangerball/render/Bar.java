package com.bluebirdaward.dangerball.render;
/*
 *  created by tuankhac 
 *  group losers
 *  update 31/7/2015
 * */
import com.badlogic.gdx.graphics.g2d.Batch;
import com.bluebirdaward.dangerball.logic.Assets;
import com.bluebirdaward.dangerball.logic.BarieLogic;
import com.bluebirdaward.dangerball.utils.Constants;

public class Bar extends RenderActor {
	private float x, y, width, height;
	
	public Bar(BarieLogic gameLogic) {
		super(gameLogic);
	}
	
	@Override public void draw(Batch batch, float delta){
		if(gameLogic.allowHit)
			batch.draw(Assets.instance.assetatlas.brick_ice, x, y, width, height);
		else
			batch.draw(Assets.instance.assetatlas.brick, x, y, width, height);
	}
	
	@Override public void act(float delta) {
		x = transformToScreen(gameLogic.getBody().getPosition().x -  Constants.BALL_RADIUS);
		y = transformToScreen(gameLogic.getBody().getPosition().y -  Constants.BALL_RADIUS);
		width = transformToScreen(2*Constants.BALL_RADIUS);
		height = transformToScreen(2*Constants.BALL_RADIUS);
		super.act(delta);
	}
}

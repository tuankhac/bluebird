package com.bluebirdaward.dynaball.render;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.bluebirdaward.dynaball.logic.Assets;
import com.bluebirdaward.dynaball.logic.BarieLogic;
import com.bluebirdaward.dynaball.utils.Constants;

public class Bar extends RenderActor {
	private float x, y, width, height;
	
	public Bar(BarieLogic gameLogic) {
		super(gameLogic);
	}
	
	@Override public void draw(Batch batch, float delta){
		batch.draw(Assets.instance.assetatlas.bar, x, y, width, height);
	}
	
	@Override public void act(float delta) {
		x = transformToScreen(gameLogic.getBody().getPosition().x -  Constants.BALL_RADIUS);
		y = transformToScreen(gameLogic.getBody().getPosition().y -  Constants.BALL_RADIUS);
		width = transformToScreen(2*Constants.BALL_RADIUS);
		height = transformToScreen(2*Constants.BALL_RADIUS);
		super.act(delta);
	}
}

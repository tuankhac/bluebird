package com.bluebirdaward.dynaball.render;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bluebirdaward.dynaball.logic.Assets;
import com.bluebirdaward.dynaball.utils.Constants;

public class Level extends Actor {
	public int level = 0;
	
	@Override public void draw(Batch batch,float delta){
		Assets.instance.fonts.defaultSmall.draw(batch, ""+ level,
				Constants.APP_WIDTH*0.8f, Constants.APP_HEIGHT - Constants.BALL_RADIUS*10);
	}
}

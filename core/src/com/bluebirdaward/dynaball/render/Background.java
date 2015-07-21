package com.bluebirdaward.dynaball.render;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.bluebirdaward.dynaball.logic.Assets;
import com.bluebirdaward.dynaball.utils.Constants;

public class Background extends RenderActor {

	public Background() {
		super();
	}
	
	@Override public void draw(Batch batch, float delta){
		batch.draw(Assets.instance.assetatlas.background, 0,0,Constants.APP_WIDTH,Constants.APP_HEIGHT);
	}
}

package com.bluebirdaward.dynaball.assets;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetAtlats {
	
	public TextureRegion background;
	public TextureRegion bar;
	public TextureRegion player;
	public TextureRegion ball;
	public TextureRegion arrow;
	
	public AssetAtlats(TextureAtlas atlas) {
		super();
		background=atlas.findRegion("bg_running");
		bar=atlas.findRegion("bg_start");
		player=atlas.findRegion("redball");
		ball=atlas.findRegion("greenball");
		arrow=atlas.findRegion("arrow");
	}
}

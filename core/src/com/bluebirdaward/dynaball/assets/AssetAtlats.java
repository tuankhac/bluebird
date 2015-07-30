package com.bluebirdaward.dynaball.assets;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetAtlats {
	
	public TextureRegion background;
	public TextureRegion touch_range;
	public TextureRegion brick;
	public TextureRegion brick_ice;
	public TextureRegion player;
	public TextureRegion ball;
	public TextureRegion arrow;
	public TextureRegion btTop;
	public TextureRegion gameOver;
	public TextureRegion finish;
	
	public AssetAtlats(TextureAtlas atlas) {
		super();
		background=atlas.findRegion("background");
		brick=atlas.findRegion("brick");
		brick_ice = atlas.findRegion("brick_ice");
		touch_range = atlas.findRegion("touch_range");
		player=atlas.findRegion("redball");
		ball=atlas.findRegion("greenball");
		arrow=atlas.findRegion("arrow");
		btTop = atlas.findRegion("bt_top");
		gameOver = atlas.findRegion("game_over");
		finish = atlas.findRegion("finish");
	}
}

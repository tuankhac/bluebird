package com.bluebirdaward.dangerball.logic;
/*
 *  created by tuankhac 
 *  group losers
 *  update 31/7/2015
 * */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;
import com.bluebirdaward.dangerball.assets.AssetAtlats;
import com.bluebirdaward.dangerball.utils.Constants;

public class Assets implements Disposable {
	public static final Assets instance = new Assets();
	public AssetAtlats assetatlas;

	private TextureAtlas atlas;
	private Assets() {}
	public void init() {
		atlas = new TextureAtlas(Constants.TEXTURE_ATLAS_OBJECTS);
		for(Texture t : atlas.getTextures())
			t.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		// create game resource objects
		assetatlas = new AssetAtlats(atlas);
		fonts = new AssetFonts();
	}

	@Override	public void dispose() {
		atlas.dispose();
		fonts.defaultSmall.dispose();
	}

	public AssetFonts fonts;

	public class AssetFonts {
		public final BitmapFont defaultSmall;

		public AssetFonts() {
			// create three fonts using Libgdx's 15px bitmap font
			defaultSmall = new BitmapFont(Gdx.files.internal("font/arial-15.fnt"), false);
			// set font sizes
			defaultSmall.getData().setScale(2.5f);
			// enable linear texture filtering for smooth fonts
			defaultSmall.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
	}
}

package com.bluebirdaward.dynaball.logic;
/*
 *  created by tuankhac 
 *  group losers
 *  update 31/7/2015
 * */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;
import com.bluebirdaward.dynaball.assets.AssetAtlats;
import com.bluebirdaward.dynaball.utils.Constants;

public class Assets implements Disposable {
	public static final Assets instance = new Assets();
	public AssetAtlats assetatlas;

	private AssetManager _assetManager;

	private Assets() {}

	public void init(AssetManager assetManager) {
		this._assetManager = assetManager;
		assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
		assetManager.finishLoading();
		TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);
		// enable texture filtering for pixel smoothing
		for (Texture t : atlas.getTextures())
			t.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		// create game resource objects
		assetatlas = new AssetAtlats(atlas);
		fonts = new AssetFonts();
	}

	@Override	public void dispose() {
		_assetManager.dispose();
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

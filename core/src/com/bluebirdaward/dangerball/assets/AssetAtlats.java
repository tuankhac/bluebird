package com.bluebirdaward.dangerball.assets;
/*
 *  created by tuankhac 
 *  group losers
 *  update 31/7/2015
 * */
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetAtlats {
	
	public TextureRegion textureRegion;
	private TextureAtlas atlas;
	//initial all texture used in game from atlas
	public  AssetAtlats(TextureAtlas atlas) {  this.atlas = atlas;	}
	
	public AssetAtlats set(String name){
		textureRegion = this.atlas.findRegion(name);
		return this;
	}
	public TextureRegion get(){
		return textureRegion;
	}
}

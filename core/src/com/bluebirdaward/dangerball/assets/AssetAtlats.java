package com.bluebirdaward.dangerball.assets;
/*
 *  created by tuankhac 
 *  group losers
 *  update 6/8/2015
 * */
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetAtlats {

	public TextureRegion textureRegion;
	private TextureAtlas atlas;
	//initial all texture used in game from atlas
<<<<<<< HEAD
	public  AssetAtlats(TextureAtlas atlas) {  this.atlas = atlas;}
	
=======
	public  AssetAtlats(TextureAtlas atlas) {  this.atlas = atlas;	}

>>>>>>> 5c9816ea6841b230c2e1df31d3711646ff55e128
	public AssetAtlats set(String name){
		textureRegion = this.atlas.findRegion(name);
		return this;
	}
<<<<<<< HEAD
	
	public TextureRegion get(){	return textureRegion;}
}
=======
	public TextureRegion get(){
		return textureRegion;
	}
}
>>>>>>> 5c9816ea6841b230c2e1df31d3711646ff55e128

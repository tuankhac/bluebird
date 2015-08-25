package com.bluebirdaward.dangerball.render;

import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.Disposable;

public class Buttons extends Actor implements Disposable {
	private TextureAtlas _atlas;
	private TextButton _button;
	private Skin _buttonSkin;
	private BitmapFont _scoreFont;
	
	protected Buttons setAtlas(String name){
		_atlas = new TextureAtlas(name);
		return this;
	}
	
	protected TextureAtlas getAtlas(){
		return _atlas;
	}

	protected Buttons setSkin(){
		_buttonSkin = new Skin();
		return this;
	}
	
	protected Skin getSkin(){
		return _buttonSkin;
	}
	
	protected Buttons setButtons(String name){
		TextButtonStyle style = new TextButtonStyle();
		style.up = _buttonSkin.getDrawable(name);
		style.down = _buttonSkin.getDrawable(name);
		style.font = _scoreFont;
		_button = new TextButton("1",style);
		return this;
	}
	
	protected TextButton getButton(){
		return _button;
	}
	
	protected void initFont(){
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/avenir_game.ttf"));
		_scoreFont = new BitmapFont();
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 20;
		parameter.characters = "0123456789";

		_scoreFont = generator.generateFont(parameter);
		generator.dispose();
//		_scoreFont.setColor(Color.BLUE);
	}
	@Override
	public void dispose() {
		_scoreFont.dispose();
		_buttonSkin.dispose();
		_atlas.dispose();		
	}
}

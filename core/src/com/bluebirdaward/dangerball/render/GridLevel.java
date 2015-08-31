package com.bluebirdaward.dangerball.render;
/*
 *  created by tuankhac 
 *  group losers
 *  update 31/7/2015
 * */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Disposable;
import com.bluebirdaward.dangerball.SaveScore;
import com.bluebirdaward.dangerball.logic.Assets;
import com.bluebirdaward.dangerball.utils.Audios;
import com.bluebirdaward.dangerball.utils.Constants;

public class GridLevel extends Actor implements Disposable {

	private TextureAtlas _buttonsAtlas;

	public Button[] buttons;
	public byte[] displayGridLevel;
	public boolean [] allowActiveButton;
	public boolean touchedGridButton = false;
	public byte display;
	public GridLevel(){
		displayGridLevel = new byte[20];
		allowActiveButton = new boolean[20];
		for (byte i = 0; i < displayGridLevel.length; i++) {
			if (i > SaveScore.getDisplayGridLevel()) {
				displayGridLevel[i] = -1;
				allowActiveButton[i] = false;
			} else if(i == SaveScore.getDisplayGridLevel())
			{
				displayGridLevel[i] = 0;
				allowActiveButton[i] = true;
			}
			else if(i < SaveScore.getDisplayGridLevel()){
				displayGridLevel[i] = 1;
				allowActiveButton[i] = true;
			}
		}
		initFont();
		buttons = new TextButton[20];
		initButtons();
	}

	@Override public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(Assets.instance.assetatlas.set("background").get(), 0, 0, Constants.APP_WIDTH, Constants.APP_HEIGHT);
		for (byte i = 0; i < displayGridLevel.length; i++) {
			buttons[i].draw(batch, parentAlpha);
		}
	}

	private GridLevel initButtons(){
		_buttonsAtlas = new TextureAtlas("images/dynaball.atlas");
		_buttonSkin = new Skin();
		_buttonSkin.addRegions(_buttonsAtlas);

		initGridLevel();
		return this;
	}

	public GridLevel initGridLevel() {
		float y = 600;
		float x = 10;
		for ( byte i = 0; i < buttons.length; i++) {
			switch (displayGridLevel[i]) {
			case -1:
				buttons[i] = initButton("grid_level_item_lock", ""+(i+1));
				activeButton(buttons[i], "grid_level_item_lock", (byte)(i+1), x, y);
				break;
			case 0:
				buttons[i] = initButton("grid_level_item_already", ""+(i+1));
				activeButton(buttons[i], "grid_level_item_already", (byte)(i+1), x, y);
				break;
			case 1:
				buttons[i] = initButton("grid_level_item_passed", ""+(i+1));
				activeButton(buttons[i], "grid_level_item_passed", (byte)(i+1), x, y);
				break;

			default:
				break;
			}
			x += 120;
			if (x > 380) {
				y -= 110;
				x = 10;
			}
		}
		return this;
	}

	private Skin _buttonSkin;
	private TextButton initButton(String name, String display){
		TextButtonStyle style = new TextButtonStyle();
		style.up = _buttonSkin.getDrawable(name);
		style.down = _buttonSkin.getDrawable(name);
		style.font = _scoreFont;
		_buttonSkin.dispose();
		return new TextButton(display, style);
	}

	private void activeButton(Button button, String name, final byte display, float x, float y){
		float btnWidth = (float)_buttonsAtlas.findRegion(name).getRegionWidth();
		float btnHeight = (float)_buttonsAtlas.findRegion(name).getRegionHeight();
		button.setSize(btnWidth, btnHeight);

		button.setPosition(x, y);
		button.setBounds(x, y, button.getWidth(), button.getHeight());
		button.addListener(new ClickListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				touchedGridButton = true;
				GridLevel.this.display = (byte)display;
				Audios.audio.play(Audios.audio.click);
				return true;
			}
		});
	}

	private BitmapFont _scoreFont;
	private void initFont(){
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/avenir_game.ttf"));
		_scoreFont = new BitmapFont();
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 60;
		parameter.characters = "0123456789";

		_scoreFont = generator.generateFont(parameter);
		generator.dispose();
		_scoreFont.setColor(Color.BLUE);
	}

	@Override
	public void dispose() {
		_buttonsAtlas.dispose();
		_scoreFont.dispose();
		_buttonSkin.dispose();
	}
}


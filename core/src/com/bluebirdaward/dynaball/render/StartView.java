package com.bluebirdaward.dynaball.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bluebirdaward.dynaball.stages.MainStage;
import com.bluebirdaward.dynaball.utils.Constants;
import com.bluebirdaward.dynaball.utils.Constants.GLOBAL_STATE;
public class StartView extends Actor {
	private TextureAtlas buttonsAtlas;
	private Skin buttonSkin;
	BitmapFont scoreFont;
	public TextButton btnStart, btnGuide;
	MainStage _mainStage;
	public StartView(MainStage mainStage){
		this._mainStage = mainStage;
		initFont();
		initButtons();
	}

	@Override public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		btnStart.draw(batch, parentAlpha);
		btnGuide.draw(batch, parentAlpha);
	}

	private void initButtons(){
		buttonsAtlas = new TextureAtlas("images/buttons.pack");
		buttonSkin = new Skin();
		buttonSkin.addRegions(buttonsAtlas);

		btnStart = initButton("button_start");
		btnGuide = initButton("button_guide");

		activeButtonStart(Constants.APP_HEIGHT/2 + 55);
		activeButtonGuide(Constants.APP_HEIGHT/2 - 55 );

	}

	private TextButton initButton(String name){
		TextButtonStyle style = new TextButtonStyle();
		style.up = buttonSkin.getDrawable(name);
		style.down = buttonSkin.getDrawable(name);
		style.font = scoreFont;
		return new TextButton("", style);
	}

	private void activeButtonStart(float y){
		float btnWidth = (float)buttonsAtlas.findRegion("button_start").getRegionWidth();
		float btnHeight = (float)buttonsAtlas.findRegion("button_start").getRegionHeight();
		btnStart.setSize(btnWidth, btnHeight);
		btnStart.setPosition(Constants.APP_WIDTH/2-btnWidth/2, y);

		btnStart.setBounds(Constants.APP_WIDTH/2-btnWidth/2, y,
				btnStart.getWidth(), btnStart.getHeight());
		btnStart.addListener(new ClickListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				_mainStage.globalState = GLOBAL_STATE.GRID_LEVEL;
				System.out.println(_mainStage.globalState);
				return true;
			}
		});
	}

	private void activeButtonGuide(float y){
		float btnWidth = (float)buttonsAtlas.findRegion("button_guide").getRegionWidth();
		float btnHeight = (float)buttonsAtlas.findRegion("button_guide").getRegionHeight();
		btnGuide.setSize(btnWidth, btnHeight);
		btnGuide.setPosition(Constants.APP_WIDTH/2-btnWidth/2, y);

		btnGuide.setBounds(Constants.APP_WIDTH/2-btnWidth/2, y,
				btnGuide.getWidth(), btnGuide.getHeight());
		btnGuide.addListener(new ClickListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				_mainStage.globalState = GLOBAL_STATE.GUIDE;
//				System.out.println(_mainStage.globalState);
				return true;
			}
		});
	}

	private void initFont(){
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/avenir_game.ttf"));
		scoreFont = new BitmapFont();
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 20;
		parameter.characters = "0123456789";

		scoreFont = generator.generateFont(parameter);
		generator.dispose();
		scoreFont.setColor(Color.BLUE);
	}
}

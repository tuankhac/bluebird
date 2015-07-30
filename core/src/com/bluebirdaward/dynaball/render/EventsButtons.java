package com.bluebirdaward.dynaball.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
import com.bluebirdaward.dynaball.logic.Assets;
import com.bluebirdaward.dynaball.stages.MainStage;
import com.bluebirdaward.dynaball.utils.Audios;
import com.bluebirdaward.dynaball.utils.Constants;
import com.bluebirdaward.dynaball.utils.Constants.GLOBAL_STATE;

public class EventsButtons extends Actor {
	public Button btnPlay;
	public Button btnPlayAgain;
	public Button btnBack;
	public boolean touchedPlayAgain = false;

	private TextureAtlas _buttonsAtlas;
	private Skin _buttonSkin;
	private MainStage _mainStage;
	private BitmapFont _scoreFont;

	private Sprite sprite = new Sprite(Assets.instance.assetatlas.finish);
	private float alphaModulation = 0;
	
	public EventsButtons(MainStage _mainStage) {
		this._mainStage = _mainStage;
		initFont();
		initButtons();

		sprite.setPosition(Constants.APP_WIDTH/8, Constants.APP_HEIGHT/3);
	}

	@Override public void draw(Batch batch, float delta) {
		super.draw(batch, delta);
		if(_mainStage.globalState == GLOBAL_STATE.GAMEOVER)
			batch.draw(Assets.instance.assetatlas.gameOver, Constants.APP_WIDTH/8, Constants.APP_HEIGHT/3, 3*Constants.APP_WIDTH/4, Constants.APP_HEIGHT/3);
		if(_mainStage.globalState == GLOBAL_STATE.CONGRATULATION){
			batch.draw(Assets.instance.assetatlas.background, 0, 0, Constants.APP_WIDTH, Constants.APP_HEIGHT);

			if(alphaModulation < 1f)
				alphaModulation += Gdx.graphics.getDeltaTime()/2;
			sprite.draw(batch, alphaModulation);
		}
	}
	private void initButtons(){
		_buttonsAtlas = new TextureAtlas("images/buttons.pack");
		_buttonSkin = new Skin();
		_buttonSkin.addRegions(_buttonsAtlas);

		btnPlay =initButton("button_play");
		btnPlayAgain = initButton("button_playagain");
		btnBack = initButton("button_back");

		activeButtonPlay(Constants.APP_HEIGHT/2);
		activeButtonPlayAgain(Constants.APP_HEIGHT/3 + Constants.BARIE_WIDTH);
		activeButtonBack(Constants.APP_HEIGHT/2 );
	}


	private TextButton initButton(String name){
		TextButtonStyle style = new TextButtonStyle();
		style.up = _buttonSkin.getDrawable(name);
		style.down = _buttonSkin.getDrawable(name);
		style.font = _scoreFont;
		return new TextButton("", style);
	}

	private void activeButtonPlay(float y){
		float btnWidth = (float)_buttonsAtlas.findRegion("button_play").getRegionWidth();
		float btnHeight = (float)_buttonsAtlas.findRegion("button_play").getRegionHeight();
		btnPlay.setSize(btnWidth, btnHeight);
		btnPlay.setPosition(Constants.APP_WIDTH/2-btnWidth/2, y);

		btnPlay.setBounds(Constants.APP_WIDTH/2-btnWidth/2, y, btnPlay.getWidth(), btnPlay.getHeight());
		btnPlay.addListener(new ClickListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				btnPlay.remove();
				_mainStage.globalState = GLOBAL_STATE.RUNNING;
				Audios.audio.play(Audios.audio.play_ball);
				System.out.println(_mainStage.globalState);
				return true;
			}
		});
	}

	private void activeButtonBack(float y){
		float btnWidth = (float)_buttonsAtlas.findRegion("button_play").getRegionWidth();
		float btnHeight = (float)_buttonsAtlas.findRegion("button_play").getRegionHeight();
		btnBack.setSize(btnWidth, btnHeight);
		btnBack.setPosition(Constants.APP_WIDTH/2-btnWidth/2, y);

		btnBack.setBounds(Constants.APP_WIDTH/2-btnWidth/2, y, btnBack.getWidth(), btnBack.getHeight());
		btnBack.addListener(new ClickListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				btnBack.remove();
				_mainStage.globalState = GLOBAL_STATE.START;
				Audios.audio.play(Audios.audio.click_grid);
				System.out.println(_mainStage.globalState);
				return true;
			}
		});
	}

	private void activeButtonPlayAgain(float y){
		float btnWidth = (float)_buttonsAtlas.findRegion("button_playagain").getRegionWidth()*0.75f;
		float btnHeight = (float)_buttonsAtlas.findRegion("button_playagain").getRegionHeight()*0.75f;
		btnPlayAgain.setSize(btnWidth, btnHeight);
		btnPlayAgain.setPosition(Constants.APP_WIDTH/2-btnWidth/2, y);

		btnPlayAgain.setBounds(Constants.APP_WIDTH/2-btnWidth/2, y, btnPlayAgain.getWidth(), btnPlayAgain.getHeight());
		btnPlayAgain.addListener(new ClickListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				touchedPlayAgain = true;
				btnPlayAgain.remove();
				Audios.audio.play(Audios.audio.click_grid);
				System.out.println(_mainStage.globalState);
				return true;
			}
		});
	}

	private void initFont(){
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/avenir_game.ttf"));
		_scoreFont = new BitmapFont();
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 20;
		parameter.characters = "0123456789";

		_scoreFont = generator.generateFont(parameter);
		generator.dispose();
		_scoreFont.setColor(Color.BLUE);
	}
}

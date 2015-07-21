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
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bluebirdaward.dynaball.stages.MainStage;
import com.bluebirdaward.dynaball.utils.Constants;
import com.bluebirdaward.dynaball.utils.Constants.GLOBAL_STATE;

public class EventsButtons extends Actor {
	public Button btnPlay;
	public Button btnPlayAgain;
	public Button btnNext;
	
	public Button btnBackRunning;
	
	private TextureAtlas _buttonsAtlas;
	private Skin _buttonSkin;
	private MainStage _mainStage;
	private BitmapFont _scoreFont;

	public EventsButtons(MainStage _mainStage) {
		this._mainStage = _mainStage;
		initFont();
		initButtons();
	}

	@Override public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		btnPlay.draw(batch, parentAlpha);
	}
	private void initButtons(){
		_buttonsAtlas = new TextureAtlas("images/buttons.pack");
		_buttonSkin = new Skin();
		_buttonSkin.addRegions(_buttonsAtlas);

		btnPlay =initButton("button_play");
		btnPlayAgain = initButton("button_playagain");
		btnNext = initButton("button_play");
		btnBackRunning = initButton("button_back");

		activeButtonPlay(Constants.APP_HEIGHT/2);
		activeButtonPlayAgain(Constants.APP_HEIGHT/2);
		activeButtonNext(Constants.APP_HEIGHT/2 );
		activeButtonBackRunning(0, 0);
	}
	
	private void activeButtonBackRunning(float x, float y){
		float btnWidth = (float)_buttonsAtlas.findRegion("button_back").getRegionWidth();
		float btnHeight = (float)_buttonsAtlas.findRegion("button_back").getRegionHeight();
		btnBackRunning.setSize(btnWidth, btnHeight);
		btnBackRunning.setPosition(x, y);

		btnBackRunning.setBounds(x, y, btnBackRunning.getWidth(), btnBackRunning.getHeight());
		btnBackRunning.addListener(new ClickListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				btnBackRunning.remove();
				
//				_mainStage._worldLogic._world.destroyBody(_mainStage._worldLogic.player.getBody());
//				for(GameLogic logic :_mainStage._worldLogic.enemyLevel.getArr()){
//					_mainStage._worldLogic._world.destroyBody(logic.getBody());
//				}
				_mainStage._background.remove();
				_mainStage._level.remove();
				_mainStage._player.remove();
				_mainStage._timer.remove();
				for(Actor actor : _mainStage._enemyRender.getActor())
					actor.remove();
				
				_mainStage._gridLevel.initGridLevel();
				_mainStage.globalState = GLOBAL_STATE.GRID_LEVEL;
				System.out.println(_mainStage.globalState);
				return true;
			}
		});
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
				System.out.println(_mainStage.globalState);
				return true;
			}
		});
	}

	private void activeButtonNext(float y){
		float btnWidth = (float)_buttonsAtlas.findRegion("button_play").getRegionWidth();
		float btnHeight = (float)_buttonsAtlas.findRegion("button_play").getRegionHeight();
		btnNext.setSize(btnWidth, btnHeight);
		btnNext.setPosition(Constants.APP_WIDTH/2-btnWidth/2, y);

		btnNext.setBounds(Constants.APP_WIDTH/2-btnWidth/2, y, btnNext.getWidth(), btnNext.getHeight());
		btnNext.addListener(new ClickListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				btnNext.remove();
				_mainStage._gridLevel.allowActiveButton[_mainStage._level.level] = true;
				_mainStage._gridLevel._displayGridLevel[_mainStage._level.level] = true;
				_mainStage.globalState = GLOBAL_STATE.RUNNING;
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
				btnPlayAgain.remove();
				_mainStage.globalState = GLOBAL_STATE.RUNNING;
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

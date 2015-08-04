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
import com.badlogic.gdx.utils.Disposable;
import com.bluebirdaward.dangerball.logic.Assets;
import com.bluebirdaward.dangerball.utils.Audios;
import com.bluebirdaward.dangerball.utils.Constants;
import com.bluebirdaward.dangerball.utils.Constants.GLOBAL_STATE;

public class EventsButtons extends Actor implements Disposable {
	public Button btnPlay;
	public Button btnPlayAgain;
	public Button btnBack;
	public boolean touchedPlayAgain = false;

	private TextureAtlas _buttonsAtlas;
	private Skin _buttonSkin;
	private BitmapFont _scoreFont;

	private Sprite _sprite = new Sprite(Assets.instance.assetatlas.finish);
	private float _alphaModulation = 0;

	public EventsButtons() {
		initFont();
		initButtons();

		_sprite.setPosition(Constants.APP_WIDTH/8, Constants.APP_HEIGHT/3);
	}

	@Override public void draw(Batch batch, float delta) {
		super.draw(batch, delta);
		if(Constants.globalState == GLOBAL_STATE.GAMEOVER)
			batch.draw(Assets.instance.assetatlas.gameOver, Constants.APP_WIDTH/8, Constants.APP_HEIGHT/3, 3*Constants.APP_WIDTH/4, Constants.APP_HEIGHT/3);
		if(Constants.globalState == GLOBAL_STATE.CONGRATULATION){
			batch.draw(Assets.instance.assetatlas.background, 0, 0, Constants.APP_WIDTH, Constants.APP_HEIGHT);

			if(_alphaModulation < 1f)
				_alphaModulation += Gdx.graphics.getDeltaTime()/2;
			_sprite.draw(batch, _alphaModulation);
		}
	}
	private void initButtons(){
		_buttonsAtlas = new TextureAtlas("images/buttons.pack");
		_buttonSkin = new Skin();
		_buttonSkin.addRegions(_buttonsAtlas);
		_buttonSkin.getAtlas();

		btnPlay =initButton("button_play");
		btnPlayAgain = initButton("button_playagain");
		btnBack = initButton("button_back");

		activeButton(btnPlay,"button_play",Constants.APP_HEIGHT/2);
		activeButton(btnPlayAgain,"button_playagain",Constants.APP_HEIGHT/2 - (float)_buttonsAtlas.findRegion("button_playagain").getRegionHeight()*0.375f );
		activeButton(btnBack,"button_back",Constants.APP_HEIGHT/2 );
	}


	private TextButton initButton(String name){
		TextButtonStyle style = new TextButtonStyle();
		style.up = _buttonSkin.getDrawable(name);
		style.down = _buttonSkin.getDrawable(name);
		style.font = _scoreFont;
		return new TextButton("", style);
	}

	private void activeButton(final Button buttons,String name,float y){
		float btnWidth = (float)_buttonsAtlas.findRegion(name).getRegionWidth();
		float btnHeight = (float)_buttonsAtlas.findRegion(name).getRegionHeight();
		buttons.setSize(btnWidth, btnHeight);
		buttons.setPosition(Constants.APP_WIDTH/2-btnWidth/2, y);

		if(buttons == btnPlayAgain)
			buttons.setBounds(Constants.APP_WIDTH/2-btnWidth*0.75f/2, y, buttons.getWidth()*0.75f, buttons.getHeight()*0.75f);
		else 
			buttons.setBounds(Constants.APP_WIDTH/2-btnWidth/2, y, buttons.getWidth(), buttons.getHeight());
		buttons.addListener(new ClickListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				buttons.remove();
				if(buttons == btnPlay){
					Constants.globalState = GLOBAL_STATE.RUNNING;
					Audios.audio.play(Audios.audio.play_ball);
					System.out.println(Constants.globalState);
				}
				else if(buttons == btnPlayAgain){
					touchedPlayAgain = true;
					Audios.audio.play(Audios.audio.click);
					System.out.println(Constants.globalState);
				}
				else if(buttons == btnBack){
					Constants.globalState = GLOBAL_STATE.START;
				}
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

	@Override
	public void dispose() {
		_buttonsAtlas.dispose();
		_buttonSkin.dispose();
		_scoreFont.dispose();	
	}
}

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
	public boolean touchedBack = false;
	private TextureAtlas _buttonsAtlas;
	private Skin _buttonSkin;
	private BitmapFont _scoreFont;

	private Sprite _sprite = new Sprite(Assets.instance.assetatlas.set("finish").get());
	private float _alphaModulation = 0;
	private float width = Constants.APP_WIDTH;
	private float height = Constants.APP_HEIGHT;

	public EventsButtons() {
		initFont();
		initButtons();

		_sprite.setPosition(width/8, height/3);
	}

	@Override public void draw(Batch batch, float delta) {
		super.draw(batch, delta);
		if(Constants.globalState == GLOBAL_STATE.GAMEOVER)
			batch.draw(Assets.instance.assetatlas.set("game_over").get(), width/8, height/3, 3*width/4, height/3);
		if(Constants.globalState == GLOBAL_STATE.CONGRATULATION){
			batch.draw(Assets.instance.assetatlas.set("background").get(), 0, 0, width, height);

			if(_alphaModulation < 1f)
				_alphaModulation += Gdx.graphics.getDeltaTime()/2;
			else _alphaModulation = 1;
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

		activeButton(btnPlay,"button_play",height/2);
		activeButton(btnPlayAgain,"button_playagain",height/2 - (float)_buttonsAtlas.findRegion("button_playagain").getRegionHeight()*0.575f );
		activeButton(btnBack,"button_back",height/2 );
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
		buttons.setPosition(width/2-btnWidth/2, y);

		if(buttons == btnPlayAgain)
			buttons.setBounds(width/2-btnWidth*0.75f/2, y, buttons.getWidth()*0.75f, buttons.getHeight()*0.75f);
		else 
			buttons.setBounds(width/2-btnWidth/2, y, buttons.getWidth(), buttons.getHeight());
		buttons.addListener(new ClickListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				buttons.remove();
				if(buttons == btnPlay){
					Constants.globalState = GLOBAL_STATE.RUNNING;
					Audios.audio.play(Audios.audio.play_ball);
				}
				else if(buttons == btnPlayAgain){
					touchedPlayAgain = true;
					Audios.audio.play(Audios.audio.click);
				}
				else if(buttons == btnBack){
					touchedBack = true;
					Audios.audio.play(Audios.audio.click);
					btnBack.remove();
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

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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Disposable;
import com.bluebirdaward.dangerball.logic.Assets;
import com.bluebirdaward.dangerball.utils.Audios;
import com.bluebirdaward.dangerball.utils.Constants;
import com.bluebirdaward.dangerball.utils.Constants.GLOBAL_STATE;
public class StartView extends Actor implements Disposable {
	private TextureAtlas _buttonsAtlas;

	private Skin _buttonSkin;
	private BitmapFont _scoreFont;

	public TextButton btnStart, btnGuide;

	public StartView(){
		initFont();
		initButtons();
	}

	@Override public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(Assets.instance.assetatlas.background, 0, 0, Constants.APP_WIDTH, Constants.APP_HEIGHT);
	}

	private void initButtons(){
		_buttonsAtlas = new TextureAtlas("images/buttons.pack");
		_buttonSkin = new Skin();
		_buttonSkin.addRegions(_buttonsAtlas);

		btnStart = initButton("button_start");
		btnGuide = initButton("button_guide");

		activeButton(btnStart,"button_start",Constants.APP_HEIGHT/2 + 55);
		activeButton(btnGuide,"button_guide",Constants.APP_HEIGHT/2 - 55 );

	}

	private TextButton initButton(String name){
		TextButtonStyle style = new TextButtonStyle();
		style.up = _buttonSkin.getDrawable(name);
		style.down = _buttonSkin.getDrawable(name);
		style.font = _scoreFont;
		return new TextButton("", style);
	}

	private void activeButton(final TextButton textbutton,String name,float y){
		float btnWidth = (float)_buttonsAtlas.findRegion(name).getRegionWidth();
		float btnHeight = (float)_buttonsAtlas.findRegion(name).getRegionHeight();
		textbutton.setSize(btnWidth, btnHeight);
		textbutton.setPosition(Constants.APP_WIDTH/2-btnWidth/2, y);

		textbutton.setBounds(Constants.APP_WIDTH/2-btnWidth/2, y,textbutton.getWidth(), btnStart.getHeight());
		textbutton.addListener(new ClickListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if(textbutton == btnStart){
					Constants.globalState = GLOBAL_STATE.GRID_LEVEL;
					Audios.audio.play(Audios.audio.click);
					System.out.println(Constants.globalState);
				}
				else {
					Constants.globalState = GLOBAL_STATE.GUIDE;
					Audios.audio.play(Audios.audio.click);
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

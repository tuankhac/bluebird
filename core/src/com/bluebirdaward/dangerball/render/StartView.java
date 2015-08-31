package com.bluebirdaward.dangerball.render;
/*
 *  created by tuankhac 
 *  group losers
 *  update 31/7/2015
 * */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bluebirdaward.dangerball.logic.Assets;
import com.bluebirdaward.dangerball.utils.Audios;
import com.bluebirdaward.dangerball.utils.Constants;
import com.bluebirdaward.dangerball.utils.Constants.GLOBAL_STATE;
public class StartView extends Buttons {

<<<<<<< HEAD
	private Skin _buttonSkin;
	private BitmapFont _scoreFont;
	
	private float app_width = Constants.APP_WIDTH;
	private float app_height = Constants.APP_HEIGHT;

	public TextButton btnStart, btnGuide;
=======
	public Button btnStart, btnGuide;
>>>>>>> 5c9816ea6841b230c2e1df31d3711646ff55e128

	public StartView(){
		initFont();
		initButtons();
	}

	@Override public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(Assets.instance.assetatlas.set("background").get(), 0, 0, app_width, app_height);
		if(Constants.globalState == GLOBAL_STATE.GUIDE)
			batch.draw(new Texture(Gdx.files.internal("images/guide.jpg")), 0, 0, app_width, app_height );
	}

	private void initButtons(){
<<<<<<< HEAD
		_buttonsAtlas = new TextureAtlas("images/buttons.pack");
		_buttonSkin = new Skin();
		_buttonSkin.addRegions(_buttonsAtlas);

		btnStart = initButton("button_start");
		btnGuide = initButton("button_guide");

		activeButton(btnStart,"button_start",app_height/2 + 55);
		activeButton(btnGuide,"button_guide",app_height/2 - 105 );

	}

	private TextButton initButton(String name){
		TextButtonStyle style = new TextButtonStyle();
		style.up = _buttonSkin.getDrawable(name);
		style.down = _buttonSkin.getDrawable(name);
		style.font = _scoreFont;
		return new TextButton("", style);
=======
		setSkin().getSkin().addRegions(setAtlas("images/buttons.pack").getAtlas());
		btnStart = setButtons("button_start").getButton();
		btnGuide = setButtons("button_guide").getButton();
		
		activeButton(btnStart,"button_start",Constants.APP_HEIGHT/2 + 55);
		activeButton(btnGuide,"button_guide",Constants.APP_HEIGHT/2 - 55 );
>>>>>>> 5c9816ea6841b230c2e1df31d3711646ff55e128
	}

	private void activeButton(final Button textbutton,String name,float y){
		float btnWidth = (float)getAtlas().findRegion(name).getRegionWidth();
		float btnHeight = (float)getAtlas().findRegion(name).getRegionHeight();
		textbutton.setSize(btnWidth, btnHeight);
		textbutton.setPosition(app_width/2-btnWidth/2, y);

		textbutton.setBounds(app_width/2-btnWidth/2, y,textbutton.getWidth(), btnStart.getHeight());
		textbutton.addListener(new ClickListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if(textbutton == btnStart){
					Constants.globalState = GLOBAL_STATE.GRID_LEVEL;
					Audios.audio.play(Audios.audio.click);
				}
				else {
					Constants.globalState = GLOBAL_STATE.GUIDE;
					Audios.audio.play(Audios.audio.click);
				}
				return true;
			}
		});
	}

}

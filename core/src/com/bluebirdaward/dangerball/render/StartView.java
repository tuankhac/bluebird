package com.bluebirdaward.dangerball.render;
/*
 *  created by tuankhac 
 *  group losers
 *  update 31/7/2015
 * */
<<<<<<< HEAD
=======
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
>>>>>>> b2fcd0be945d07866a34cff9663536fab150b88e
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bluebirdaward.dangerball.logic.Assets;
import com.bluebirdaward.dangerball.utils.Audios;
import com.bluebirdaward.dangerball.utils.Constants;
import com.bluebirdaward.dangerball.utils.Constants.GLOBAL_STATE;
public class StartView extends Buttons {

	public Button btnStart, btnGuide;

	public StartView(){
		initFont();
		initButtons();
	}

	@Override public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(Assets.instance.assetatlas.set("background").get(), 0, 0, Constants.APP_WIDTH, Constants.APP_HEIGHT);
<<<<<<< HEAD
=======
		if(Constants.globalState == GLOBAL_STATE.GUIDE)
			batch.draw(new Texture(Gdx.files.internal("images/guide.png")), 0, 0, Constants.APP_WIDTH, Constants.APP_HEIGHT );
>>>>>>> b2fcd0be945d07866a34cff9663536fab150b88e
	}

	private void initButtons(){
		setSkin().getSkin().addRegions(setAtlas("images/buttons.pack").getAtlas());
		btnStart = setButtons("button_start").getButton();
		btnGuide = setButtons("button_guide").getButton();
		
		activeButton(btnStart,"button_start",Constants.APP_HEIGHT/2 + 55);
		activeButton(btnGuide,"button_guide",Constants.APP_HEIGHT/2 - 55 );
	}

	private void activeButton(final Button textbutton,String name,float y){
		float btnWidth = (float)getAtlas().findRegion(name).getRegionWidth();
		float btnHeight = (float)getAtlas().findRegion(name).getRegionHeight();
		textbutton.setSize(btnWidth, btnHeight);
		textbutton.setPosition(Constants.APP_WIDTH/2-btnWidth/2, y);

		textbutton.setBounds(Constants.APP_WIDTH/2-btnWidth/2, y,textbutton.getWidth(), btnStart.getHeight());
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

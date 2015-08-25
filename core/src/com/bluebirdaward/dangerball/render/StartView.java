package com.bluebirdaward.dangerball.render;
/*
 *  created by tuankhac 
 *  group losers
 *  update 31/7/2015
 * */
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

}

package com.bluebirdaward.dangerball.render;
/*
 *  created by tuankhac 
 *  group losers
 *  update 31/7/2015
 * */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bluebirdaward.dangerball.logic.Assets;
import com.bluebirdaward.dangerball.utils.Audios;
import com.bluebirdaward.dangerball.utils.Constants;
import com.bluebirdaward.dangerball.utils.Constants.GLOBAL_STATE;

public class EventsButtons extends Buttons {
	public Button btn;
	public Button btnPlayAgain;
	public Button btnBack;
	public boolean touchedPlayAgain = false;

	private Sprite _sprite = new Sprite(Assets.instance.assetatlas.set("finish").get());
	private float _alphaModulation = 0;

	public EventsButtons() {
		initFont();
		initButtons();

		_sprite.setPosition(Constants.APP_WIDTH/8, Constants.APP_HEIGHT/3);
	}
	
	@Override public void draw(Batch batch, float delta) {
		super.draw(batch, delta);
		if(Constants.globalState == GLOBAL_STATE.GAMEOVER)
			batch.draw(Assets.instance.assetatlas.set("game_over").get(), Constants.APP_WIDTH/8, Constants.APP_HEIGHT/3, 3*Constants.APP_WIDTH/4, Constants.APP_HEIGHT/3);
		if(Constants.globalState == GLOBAL_STATE.CONGRATULATION){
			batch.draw(Assets.instance.assetatlas.textureRegion, 0, 0, Constants.APP_WIDTH, Constants.APP_HEIGHT);

			if(_alphaModulation < 1f)
				_alphaModulation += Gdx.graphics.getDeltaTime()/2;
			_sprite.draw(batch, _alphaModulation);
		}
	}
	private void initButtons(){
		setSkin().getSkin().addRegions(setAtlas("images/buttons.pack").getAtlas());

		btn =setButtons("button_play").getButton();
		btnPlayAgain = setButtons("button_playagain").getButton();
		btnBack = setButtons("button_back").getButton();

		activeButton(btn,"button_play",Constants.APP_HEIGHT/2);
		activeButton(btnPlayAgain,"button_playagain",Constants.APP_HEIGHT/2 - (float)getAtlas().findRegion("button_playagain").getRegionHeight()*0.375f );
		activeButton(btnBack,"button_back",Constants.APP_HEIGHT/2 );
	}

	private void activeButton(final Button buttons,String name,float y){
		float btnWidth = (float)getAtlas().findRegion(name).getRegionWidth();
		float btnHeight = (float)getAtlas().findRegion(name).getRegionHeight();
		buttons.setSize(btnWidth, btnHeight);
		buttons.setPosition(Constants.APP_WIDTH/2-btnWidth/2, y);

		if(buttons == btnPlayAgain)
			buttons.setBounds(Constants.APP_WIDTH/2-btnWidth*0.75f/2, y, buttons.getWidth()*0.75f, buttons.getHeight()*0.75f);
		else 
			buttons.setBounds(Constants.APP_WIDTH/2-btnWidth/2, y, buttons.getWidth(), buttons.getHeight());
		buttons.addListener(new ClickListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				buttons.remove();
				if(buttons == btn){
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

}

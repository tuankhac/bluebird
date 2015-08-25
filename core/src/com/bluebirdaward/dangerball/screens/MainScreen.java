package com.bluebirdaward.dangerball.screens;
/*
 *  created by tuankhac 
 *  group losers
 *  update 31/7/2015
 * */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bluebirdaward.dangerball.stages.MainStage;
import com.bluebirdaward.dangerball.utils.Constants;
import com.bluebirdaward.dangerball.utils.Constants.GLOBAL_STATE;

public class MainScreen implements Screen {
	private MainStage _stage;
	private Texture _texture ;
	private Sprite _sprite ;
	private SpriteBatch _batch;
	private float _deltaTime = 0;

	public static byte  exit = 0;
	public static  float timer = 0;

	public MainScreen(IActivityRequestHandler iActHandler) { 
		_batch = new SpriteBatch();
		_texture = new Texture(Gdx.files.internal("images/bluebird_logo.png"));
		_sprite = new Sprite(_texture);
		_sprite.setSize(Gdx.graphics.getWidth(), (float) _texture.getHeight()/1.8125f);
		_sprite.setPosition(Gdx.graphics.getWidth()/2 - _sprite.getWidth()/2,
				Gdx.graphics.getHeight()/2 - (float) _texture.getHeight()/3.625f);

		_stage = new MainStage(iActHandler); 
		
	}

	@Override public void render(float delta) {
		//Clear the screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//update timer to handle when onBackpressed to exit game
		timer += Gdx.graphics.getDeltaTime();
		if(timer > 1.5f){
			exit = 0;
			timer = 0;
		}
		//update _deltaTime to move to main stage screen when overtime
//		if(_deltaTime < 3){
//			_deltaTime += Gdx.graphics.getDeltaTime();
//			Gdx.gl.glClearColor(1, 1, 1, 1);
//			_batch.begin();
//			_sprite.draw(_batch);
//			_batch.end();
//		}
//		else{
			//Update the _stage
			_stage.draw();
			_stage.act(delta);
//		}
	}

	@Override public void show() { }


	@Override public void resize(int width, int height) { }

	@Override public void pause() { }

	@Override public void resume() { }

	@Override public void hide() { Constants.globalState = GLOBAL_STATE.START; }

	@Override public void dispose() { }
}

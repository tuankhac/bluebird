package com.bluebirdaward.dangerball.screens;
/*
 *  created by tuankhac 
 *  group losers
 *  update 31/7/2015
 * */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.bluebirdaward.dangerball.stages.MainStage;
import com.bluebirdaward.dangerball.utils.Constants;
import com.bluebirdaward.dangerball.utils.Constants.GLOBAL_STATE;

public class MainScreen implements Screen {
	private MainStage _stage;

	public MainScreen(IActivityRequestHandler iActHandler) { 
		_stage = new MainStage(iActHandler); 
	}

	@Override public void render(float delta) {
		//Clear the screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(1, 1, 1, 1);
			//Update the _stage
			_stage.draw();
			_stage.act(delta);
	}

	@Override public void show() { }

	@Override public void resize(int width, int height) { }

	@Override public void pause() { }

	@Override public void resume() { }

	@Override public void hide() { Constants.globalState = GLOBAL_STATE.START; }

	@Override public void dispose() {}
}

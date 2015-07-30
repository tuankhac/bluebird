package com.bluebirdaward.dynaball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bluebirdaward.dynaball.stages.MainStage;
import com.bluebirdaward.dynaball.utils.Constants;

public class MainScreen implements Screen {
	private MainStage stage;
	private Texture texture ;
	private Sprite sprite ;
	SpriteBatch batch;
	float deltaTime = 0;
	public MainScreen() { 
		batch = new SpriteBatch();
		texture = new Texture(Gdx.files.internal("images/bluebird_logo.png"));
		sprite = new Sprite(texture);
		sprite.setSize(Constants.APP_WIDTH, (float) texture.getHeight()/1.8125f);
		sprite.setPosition(0, Constants.APP_HEIGHT/2 - (float) texture.getHeight()/3.625f);
		stage = new MainStage(); 
	}

	@Override public void render(float delta) {
		//Clear the screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(deltaTime < 3){
			deltaTime += Gdx.graphics.getDeltaTime();
			Gdx.gl.glClearColor(1, 1, 1, 1);
			batch.begin();
			sprite.draw(batch);
			batch.end();
		}
		else{
			//Update the stage
			stage.draw();
			stage.act(delta);
		}

	}

	@Override public void show() { }

	@Override public void resize(int width, int height) { }

	@Override public void pause() { }

	@Override public void resume() { }

	@Override public void hide() { }

	@Override public void dispose() { }
}

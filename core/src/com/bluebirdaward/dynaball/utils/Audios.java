package com.bluebirdaward.dynaball.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Audios {
	public  Sound enter_game ;
	public  Sound life ;
	public  Sound move;
	public  Sound shoot;
	public  Sound bum;
	public  Sound bum_tank ;
	public  Sound level_completed ;
	public  boolean canPlay = true;

	public static Audios audio = new Audios();
	// khởi tạo các âm thanh sử dụng trong chương trình.
	private Audios() {}

	public void initAudio(){
		enter_game = Gdx.audio.newSound(Gdx.files	.internal("sounds/enter_game.wav"));
		life = Gdx.audio.newSound(Gdx.files.internal("sounds/life.wav"));
		move = Gdx.audio.newSound(Gdx.files.internal("sounds/move.wav"));
		shoot = Gdx.audio.newSound(Gdx.files.internal("sounds/shoot.wav"));
		bum = Gdx.audio.newSound(Gdx.files.internal("sounds/bum.wav"));
		bum_tank = Gdx.audio.newSound(Gdx.files.internal("sounds/bum_tank.wav"));
		level_completed = Gdx.audio.newSound(Gdx.files.internal("sounds/level_completed.wav"));
	}
	public  void play(final Sound sound) {
		if (canPlay) {
			sound.play();
			if (sound == move)
				move.loop();
		}
	}

	public  void stopAll() {
		enter_game.stop();
		life.stop();
		move.stop();
		shoot.stop();
		bum.stop();
		bum_tank.stop();
		level_completed.stop();
	}
	public void disposeAll(){
		enter_game.dispose();
		life.dispose();
		shoot.dispose();
		move.dispose();
		bum.dispose();
		bum_tank.dispose();
		level_completed.dispose();
	}
}

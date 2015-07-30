package com.bluebirdaward.dynaball.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Audios {
	public  Sound play_ball ;
	public  Sound ball_restitution ;
	public  Sound click_grid;
	public  Sound player_hit_enemy;
	public  Sound game_over;
//	public  Sound bum_tank ;
//	public  Sound level_completed ;
	public  boolean canPlay = true;

	public static Audios audio = new Audios();
	// khởi tạo các âm thanh sử dụng trong chương trình.
	private Audios() {}

	public void initAudio(){
		play_ball = Gdx.audio.newSound(Gdx.files.internal("sounds/play_ball.mp3"));
		ball_restitution = Gdx.audio.newSound(Gdx.files.internal("sounds/ball_restitution.mp3"));
		click_grid = Gdx.audio.newSound(Gdx.files.internal("sounds/click01.mp3"));
		player_hit_enemy = Gdx.audio.newSound(Gdx.files.internal("sounds/ball_hit_enemy.mp3"));
		game_over = Gdx.audio.newSound(Gdx.files.internal("sounds/gameOver07.mp3"));
//		bum_tank = Gdx.audio.newSound(Gdx.files.internal("sounds/bum_tank.wav"));
//		level_completed = Gdx.audio.newSound(Gdx.files.internal("sounds/level_completed.wav"));
	}
	public  void play(final Sound sound) {
		if (canPlay) {
			sound.play();
			if (sound == click_grid)
				click_grid.loop();
		}
	}

	public  void stopAll() {
		play_ball.stop();
		ball_restitution.stop();
		click_grid.stop();
		player_hit_enemy.stop();
		game_over.stop();
//		bum_tank.stop();
//		level_completed.stop();
	}
	public void disposeAll(){
		play_ball.dispose();
		ball_restitution.dispose();
		player_hit_enemy.dispose();
		click_grid.dispose();
		game_over.dispose();
//		bum_tank.dispose();
//		level_completed.dispose();
	}
}

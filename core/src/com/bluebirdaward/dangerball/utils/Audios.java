package com.bluebirdaward.dangerball.utils;
/*
 *  created by tuankhac 
 *  group losers
 *  update 31/7/2015
 * */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Disposable;

public class Audios implements Disposable {
	public Sound play_ball ;
	public Sound ball_restitution ;
	public Sound click;
	public Sound player_hit_enemy;
	public Sound game_over;
	public Sound next;
	public  boolean canPlay = true;

	public static Audios audio = new Audios();
	// initial sound use in game
	private Audios() {}

	public void initAudio(){
		play_ball = Gdx.audio.newSound(Gdx.files.internal("sounds/play_ball.mp3"));
		ball_restitution = Gdx.audio.newSound(Gdx.files.internal("sounds/ball_restitution.mp3"));
		click = Gdx.audio.newSound(Gdx.files.internal("sounds/click.mp3"));
		player_hit_enemy = Gdx.audio.newSound(Gdx.files.internal("sounds/ball_hit_enemy.mp3"));
		game_over = Gdx.audio.newSound(Gdx.files.internal("sounds/gameOver07.mp3"));
		next = Gdx.audio.newSound(Gdx.files.internal("sounds/next_game.mp3"));
	}

	public  void play(final Sound sound) {
		if (canPlay) {
			if (sound == ball_restitution)
				ball_restitution.play(0.5f);
			else
				sound.play();
		}
	}

	private void disposeAll(){
		play_ball.dispose();
		ball_restitution.dispose();
		player_hit_enemy.dispose();
		click.dispose();
		game_over.dispose();
	}

	@Override
	public void dispose() {
		disposeAll();
	}
}

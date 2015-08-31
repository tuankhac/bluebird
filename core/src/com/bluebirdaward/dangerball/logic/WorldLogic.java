package com.bluebirdaward.dangerball.logic;
/*
 *  created by tuankhac 
 *  group losers
 *  update 31/7/2015
 * */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.bluebirdaward.dangerball.render.Level;
import com.bluebirdaward.dangerball.utils.Audios;
import com.bluebirdaward.dangerball.utils.Constants;
import com.bluebirdaward.dangerball.utils.Constants.GLOBAL_STATE;
import com.bluebirdaward.dangerball.utils.Constants.SETTIMER;
import com.bluebirdaward.dangerball.utils.Constants.TIMER;
import com.bluebirdaward.dangerball.utils.Constants.VELOCITY;

public class WorldLogic {
	public PlayerLogic player;
	public boolean allowPlayerHandle = true;
	public EnemyLevel enemyLevel;
	public byte gameOver;
	public byte level = 1;
	public byte countPressed = 0;

	private boolean _resetPlayer = false;
	private World _world;
	private byte _getScore; 
	private Level _level;

	private float _elapse = 0;
	private float setTimer ;
	private float vXBarieHorizontal = 0f;
	private float vYBarieHorizontal = 0f;
	private float vXBarieVertical = 0f;
	private float vYBarieVertical = 0f;
	private float vXBalloon = 0f ;
	private float vYBalloon = 0f;

	public WorldLogic(Level level) {

		this._level = level;

		_world = new World(Constants.WORLD_GRAVITY, true);
		_world.setContactListener(new ContactListener() {
			@Override public void preSolve(Contact contact, Manifold oldManifold) {}
			@Override public void postSolve(Contact contact, ContactImpulse impulse) {}
			@Override public void endContact(Contact contact) {}
			@Override public void beginContact(Contact contact) {
				Body _a = contact.getFixtureA().getBody();
				Body _b = contact.getFixtureB().getBody();

				if (_a.getUserData() != Constants.USERDATA_LIMITED ) {
					_resetPlayer = true;
					allowPlayerHandle = true;
					if((_a.getUserData() == Constants.USERDATA_BARIE|| _b.getUserData() == Constants.USERDATA_BARIE)){
						Audios.audio.play(Audios.audio.ball_restitution);
						for(GameLogic logic: enemyLevel.getArr())
							if((logic.getBody() == _b || logic.getBody() == _a) && logic.allowHit){
								_resetPlayer= false;
								allowPlayerHandle = false;
								break;
							}
					}

					if((_a.getUserData() == Constants.USERDATA_ENEMY || _b.getUserData() == Constants.USERDATA_ENEMY)){
						for(GameLogic logic: enemyLevel.getArr())
							if(logic.getBody() == _b || logic.getBody() == _a){
								Audios.audio.play(Audios.audio.player_hit_enemy);
								logic.hit();
							}
					}
				}
			}
		});

		player = new PlayerLogic(_world);
		enemyLevel = new EnemyLevel(_world);
		new LimitedLogic(_world);
	}

	/*init new level when have new event*/
	public void initNewLevel(){
		if(countPressed >0){
			level = _level.level;
			changeForEachMap();
			enemyLevel.setArrSence("level/map"+ level);	
			countPressed = 0;
		}
		//		else {
		//			_level.level = level;
		//			enemyLevel.setArrSence("level/map"+ level);	
		//			setTimer = SETTIMER.valueOf("LEVEL"+level).getValue();
		//			vYBarieHorizontal =  VELOCITY.valueOf("LEVEL"+level).getVX();
		//			vYBarieVertical =  VELOCITY.valueOf("LEVEL"+level).getVY();
		//		}
	}

	public void update(){
		_world.step(1/60f, 8, 3);
		player.update();
		catchRunning();
	}
	
	public void resetLevel(){
		switchLevel();
		player.reset();
		allowPlayerHandle = true;
		_elapse = 0;

		changeForEachMap();

		removeAllBodyLogic();
		enemyLevel.setArrSence("level/map"+level);
	}

	public void nextLevel(){
		vXBarieHorizontal = 0;
		vYBarieHorizontal = 0;
		vXBarieVertical = 0;
		vYBarieVertical = 0;
		vXBalloon = 0;
		vYBalloon = 0;
		_elapse = 0;
		removeAllBodyLogic();
		initNewLevel();
		switchLevel();
	}

	/* Handle to remove object*/
	
	private void changeForEachMap(){
		if(level == 6)  vXBarieHorizontal = VELOCITY.valueOf("LEVEL"+level).getVX(); 
		else if(level ==7)  vYBarieVertical = VELOCITY.valueOf("LEVEL"+level).getVY();
		else if(level == 8) vXBarieHorizontal = VELOCITY.valueOf("LEVEL"+level).getVX();
		else if(level < 10)	vYBarieVertical = VELOCITY.valueOf("LEVEL"+level).getVY();
		else if(level < 14){
			vXBarieHorizontal = VELOCITY.valueOf("LEVEL"+level).getVX();
			vYBarieVertical = VELOCITY.valueOf("LEVEL"+level).getVY();
		}
		else if(level < 16) 
		{
			vYBarieHorizontal =  VELOCITY.valueOf("LEVEL"+level).getVX();
			vYBarieVertical =  VELOCITY.valueOf("LEVEL"+level).getVY();
		}
		else if(level < 17) vXBarieHorizontal = VELOCITY.valueOf("LEVEL"+level).getVY();
		else if(level < 18) {
			vYBarieVertical = VELOCITY.valueOf("LEVEL"+level).getVY();
		}
		else if(level == 18) vYBarieVertical = VELOCITY.valueOf("LEVEL"+level).getVY();
		else if(level ==19) vXBarieHorizontal = VELOCITY.valueOf("LEVEL"+level).getVX();
		else if(level ==20) vXBarieHorizontal = VELOCITY.valueOf("LEVEL"+level).getVX();
		
		setTimer = SETTIMER.valueOf("LEVEL"+level).getValue();
	}
	void remove(GameLogic object){ enemyLevel.getArr().remove(enemyLevel.getArr().indexOf(object));}

	private void catchRunning(){
		for(int i= enemyLevel.getArr().size() -1 ;i>=0;i--)
			if(enemyLevel.getArr().get(i).isHit() == true){
				_getScore ++;
				break;
			}
		if(level>5){
			_elapse = _elapse +Gdx.graphics.getDeltaTime();
			calculateMotion();
			for(int i=0; i< enemyLevel.getArr().size(); i++){
				if(enemyLevel.getArr().get(i).getBody().getUserData() == Constants.USERDATA_ENEMY){
					enemyLevel.getArr().get(i).mAllowMotion(vXBalloon, vYBalloon);
				}

				if(enemyLevel.getArr().get(i).allowMotionHorizontal == true )
					enemyLevel.getArr().get(i).mAllowMotion(vXBarieHorizontal, vYBarieHorizontal);
				if(enemyLevel.getArr().get(i).allowMotionVertical == true)
					enemyLevel.getArr().get(i).mAllowMotion(vXBarieVertical, vYBarieVertical);
			}
		}
		if (_resetPlayer) {
			player.reset();
			_resetPlayer = false;
		}

		//update timer and score
		_level.updateDeltaTime();

		// reset level when over timer
		if(_level.timer == 0){
			Audios.audio.play(Audios.audio.game_over);
			Constants.globalState = GLOBAL_STATE.GAMEOVER;
			gameOver = -1;
		}

		if(_getScore == enemyLevel.countEnemy()){
			Audios.audio.play(Audios.audio.next);
			Constants.globalState = GLOBAL_STATE.GRID_LEVEL;
			gameOver = 1;
		}
	}

	private void calculateMotion(){
		if(_elapse >= setTimer){
			if(vXBarieHorizontal != 0) vXBarieHorizontal *= -1;
			if(vYBarieVertical != 0) vYBarieVertical *= -1;
			if(vYBarieHorizontal != 0 ) vYBarieHorizontal *= -1;
			if(vXBalloon != 0) vXBalloon *= -1;
			if(vYBalloon != 0) vYBalloon *= -1;
			_elapse = 0;
			setTimer = 2*SETTIMER.valueOf("LEVEL"+level).getValue();
		}
	}

	private void switchLevel(){	_level.timer = (byte)TIMER.valueOf("LEVEL"+level).getValue();	}

	private void removeAllBodyLogic(){
		_getScore = 0;
		for(int i=0;i < enemyLevel.getArr().size();i++ )
			_world.destroyBody(enemyLevel.getArr().get(i).getBody());
		enemyLevel.getArr().clear();
	}
}

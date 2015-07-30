package com.bluebirdaward.dynaball.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.bluebirdaward.dynaball.render.Level;
import com.bluebirdaward.dynaball.stages.MainStage;
import com.bluebirdaward.dynaball.utils.Audios;
import com.bluebirdaward.dynaball.utils.Constants;
import com.bluebirdaward.dynaball.utils.Constants.GLOBAL_STATE;

public class WorldLogic {
	public PlayerLogic player;
	public boolean allowPlayerHandle = true;
	public EnemyLevel enemyLevel;
	public  int gameOver;
	public int level = 2;

	public int countPressed = 0;
	private boolean _resetPlayer = false;
	public World _world;
	private int _getScore; 
	private Body _a,_b;
	private Level _level;
	private MainStage _mainStage;

	private float _elapse = 0;
	float vXBarie = 3f, vYBarie = 3f;
	float vXBalloon = 3f , vYBalloon = 3f;

	public WorldLogic(MainStage _mainStage,Level level) {

		this._level = level;
		this._mainStage = _mainStage;

		_world = new World(Constants.WORLD_GRAVITY, true);
		_world.setContactListener(new ContactListener() {
			@Override public void preSolve(Contact contact, Manifold oldManifold) {}
			@Override public void postSolve(Contact contact, ContactImpulse impulse) {}
			@Override public void endContact(Contact contact) {}
			@Override public void beginContact(Contact contact) {
				_a = contact.getFixtureA().getBody();
				_b = contact.getFixtureB().getBody();

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
//		initNewLevel();
		new LimitedLogic(_world);
	}

	public void update(){
		_world.step(1/60f, 8, 3);
		player.update();
		catchRunning();
	}
	/*init new level when have new event*/
	public void initNewLevel(){
		if(countPressed >1){
			level = _level.level;
			enemyLevel.setArrSence("level/map"+ level);	
			countPressed = 1;
		}
		else {
			_level.level = level;
			enemyLevel.setArrSence("level/map"+ level);	
		}
	}

	// unused
	boolean isResetPlayer(){ return _resetPlayer;}
	void setResetPlayer(boolean reset){_resetPlayer = reset;}

	/** Used to Debug logic */
	public World getWorldLogic() { return _world; }

	/* Handle to remove object*/
	void remove(GameLogic object){ enemyLevel.getArr().removeIndex(enemyLevel.getArr().indexOf(object, true));}

	private void catchRunning(){
		for(int i= enemyLevel.getArr().size -1 ;i>=0;i--){
			if(enemyLevel.getArr().get(i).isHit() == true){
				//				enemyLevel.getArr().get(i).getBody().setTransform(Constants.VP_WIDTH -3*Constants.BALL_RADIUS,
				//						Constants.VP_HEIGHT - 2*Constants.BALL_RADIUS,0);
//								enemyLevel.getArr().get(i).reset();
				_getScore ++;
				break;
			}
		}
		_elapse = _elapse +Gdx.graphics.getDeltaTime();
		for(int i=0; i< enemyLevel.getArr().size; i++){
			switch (level) {
			case 6: break;
			case 7:
				vYBarie = 0;
				if(_elapse >= 2){
					_elapse = 0;
					vXBarie *= -1;
				}
				break;
			case 8:
				vXBarie = 0;
				if(_elapse >= 2){
					_elapse = 0;
					vYBarie *= -1;
				}
				break;
			case 9:
				vXBarie = 0;
				if(_elapse >= 2){
					_elapse = 0;
					vYBarie *= -1;
				}

				break;
			case 10:
				vXBarie = 0;
				vYBarie = 0;
				break;
			case 11:
			case 12:
				vXBalloon = 0;
				if(_elapse >= 2){
					_elapse = 0;
					vYBalloon *= -1;
				}
				break;
			case 13:
			case 14:
			case 15:
				vYBarie = 0;
				if(_elapse >= 2){
					_elapse = 0;
					vXBarie *= -1;
				}
				break;
			case 16:
				vYBarie = 0;
				if(_elapse >= 2){
					_elapse = 0;
					vXBarie *= -1;
				}
				break;
			case 17:
				vXBarie = 0;
				if(_elapse >= 2){
					_elapse = 0;
					vYBarie *= -1;
				}
				break;
			case 18:
				vYBarie = 0;
				if(_elapse >= 2){
					_elapse = 0;
					vXBarie *= -1;
				}
				break;
			case 19:
				vYBarie = 0;
				if(_elapse >= 2){
					_elapse = 0;
					vXBarie *= -1;
				}
				break;
			case 20:
				vYBarie = 0;
				if(_elapse >= 2){
					_elapse = 0;
					vXBarie *= -1;
				}
				break;
			default:
				break;
			}
			//						if(enemyLevel.getArr().get(i).getBody().getUserData() == Constants.USERDATA_ENEMY){
			//							enemyLevel.getArr().get(i).mAllowMotion(vXBalloon, vYBalloon);
			//						}
			//			
			if(enemyLevel.getArr().get(i).allowMotionHorizontal == true ||
					enemyLevel.getArr().get(i).allowMotionVertical == true){
				enemyLevel.getArr().get(i).mAllowMotion(vXBarie, vYBarie);
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
			gameOver = -1;
			_mainStage.globalState = GLOBAL_STATE.GAMEOVER;
		}

		if(_getScore == enemyLevel.countEnemy()){
			_mainStage.globalState = GLOBAL_STATE.GRID_LEVEL;
			gameOver = 1;
		}
	}

	public void resetLevel(){
		player.reset();
		allowPlayerHandle = true;
		switch (level) {
		case 1:
		case 2:
			_level.timer = 10;
			break;
		case 3:
		case 4:
			_level.timer = 15;
			break;
		case 5:
			_level.timer = 15;
			break;
		case 6:
			_level.timer = 20;
			break;
		case 7:
			_level.timer = 20;
			break;
		case 8:
			_level.timer = 20;
			break;
		case 9:
			_level.timer = 20;
			break;
		case 10:
			_level.timer = 20;
		case 11:
			_level.timer = 20;
			break;
		case 12:
			_level.timer = 20;
			break;
		case 13:
			_level.timer = 20;
			break;
		case 14:
			_level.timer = 20;
			break;
		case 15:
			_level.timer = 20;
			break;
		case 16:
			_level.timer = 20;
			break;
		case 17:
			_level.timer = 20;
			break;
		case 18:
			_level.timer = 20;
			break;
		case 19:
			_level.timer = 20;
			break;
		case 20:
			_level.timer = 20;
			break;
		default:
			break;
		}
		_getScore = 0;
		vXBarie = 3f; vYBarie = 3f;
		vXBalloon = 3f ; vYBalloon = 3f;
		for(int i=0;i<enemyLevel.getArr().size;i++ )
			_world.destroyBody(enemyLevel.getArr().get(i).getBody());
		enemyLevel.getArr().clear();
		enemyLevel.setArrSence("level/map"+level);
	}

	public void nextLevel(){
		switch (level) {
		case 1:
			_level.timer = 15;
			break;
		case 2:
			_level.timer = 15;
			break;
		case 3:
			_level.timer = 15;
			break;
		case 4:
			_level.timer = 15;
			break;
		case 5:
			_level.timer = 15;
			break;
		case 6:
			_level.timer = 20;
			break;
		case 7:
			_level.timer = 20;
			break;
		case 8:
			_level.timer = 20;
			break;
		case 9:
			_level.timer = 20;
			break;
		case 10:
			_level.timer = 20;
		case 11:
			_level.timer = 20;
			break;
		case 12:
			_level.timer = 20;
			break;
		case 13:
			_level.timer = 20;
			break;
		case 14:
			_level.timer = 20;
			break;
		case 15:
			_level.timer = 20;
			break;
		case 16:
			_level.timer = 20;
			break;
		case 17:
			_level.timer = 20;
			break;
		case 18:
			_level.timer = 20;
			break;
		case 19:
			_level.timer = 20;
			break;
		case 20:
			_level.timer = 20;
			break;
		default:
			break;
		}
		_getScore = 0;
		vXBarie = 3f; vYBarie = 3f;
		vXBalloon = 3f ; vYBalloon = 3f;
		for(int i=0;i < enemyLevel.getArr().size;i++ )
			_world.destroyBody(enemyLevel.getArr().get(i).getBody());
		enemyLevel.getArr().clear();
		level++;
		initNewLevel();
	}
}

package com.bluebirdaward.dynaball.logic;
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
import com.bluebirdaward.dynaball.render.Level;
import com.bluebirdaward.dynaball.stages.MainStage;
import com.bluebirdaward.dynaball.utils.Audios;
import com.bluebirdaward.dynaball.utils.Constants;
import com.bluebirdaward.dynaball.utils.Constants.GLOBAL_STATE;
import com.bluebirdaward.dynaball.utils.Constants.TIMER;

public class WorldLogic {
	public PlayerLogic player;
	public boolean allowPlayerHandle = true;
	public EnemyLevel enemyLevel;
	public int gameOver;
	public int level = 1;
	public int countPressed = 0;

	private boolean _resetPlayer = false;
	private World _world;
	private int _getScore; 
	private Body _a,_b;
	private Level _level;
	private MainStage _mainStage;

	private float _elapse = 0;
	private float vXBarie = 0f, vYBarie = 0f;
	private float vXBalloon = 0f , vYBalloon = 0f;

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
		new LimitedLogic(_world);
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

	public void update(){
		_world.step(1/60f, 8, 3);
		player.update();
		catchRunning();
	}

	/** Used to Debug logic */
	public World getWorldLogic() { return _world; }

	/* Handle to remove object*/
	void remove(GameLogic object){ enemyLevel.getArr().removeIndex(enemyLevel.getArr().indexOf(object, true));}

	private void catchRunning(){
		for(int i= enemyLevel.getArr().size -1 ;i>=0;i--)
			if(enemyLevel.getArr().get(i).isHit() == true){
				_getScore ++;
				break;
			}

		_elapse = _elapse +Gdx.graphics.getDeltaTime();
		for(int i=0; i< enemyLevel.getArr().size; i++){
			if(enemyLevel.getArr().get(i).getBody().getUserData() == Constants.USERDATA_ENEMY){
				enemyLevel.getArr().get(i).mAllowMotion(vXBalloon, vYBalloon);
			}
						
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
			_mainStage.globalState = GLOBAL_STATE.GAMEOVER;
			gameOver = -1;
		}

		if(_getScore == enemyLevel.countEnemy()){
			Audios.audio.play(Audios.audio.next);
			_mainStage.globalState = GLOBAL_STATE.GRID_LEVEL;
			gameOver = 1;
		}
	}

	public void resetLevel(){
		switchLevel();
		player.reset();
		allowPlayerHandle = true;
		moveAllBodyLogic();
		enemyLevel.setArrSence("level/map"+level);
	}

	public void nextLevel(){
		switchLevel();
		moveAllBodyLogic();
		initNewLevel();
	}
	
	private void calculateMotionBarie(float vX,float vY){
		vXBarie = vX; vYBarie = vY;
		if(_elapse >= 2){
			
		}
	}
	
	private void switchLevel(){
		_level.timer = TIMER.valueOf("LEVEL"+level).getValue();
	}
	private void moveAllBodyLogic(){
		_getScore = 0;
		for(int i=0;i < enemyLevel.getArr().size;i++ )
			_world.destroyBody(enemyLevel.getArr().get(i).getBody());
		enemyLevel.getArr().clear();
	}
}

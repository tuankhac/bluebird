package com.bluebirdaward.dynaball.logic;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.bluebirdaward.dynaball.render.Level;
import com.bluebirdaward.dynaball.render.Timer;
import com.bluebirdaward.dynaball.stages.MainStage;
import com.bluebirdaward.dynaball.utils.Constants;
import com.bluebirdaward.dynaball.utils.Constants.GLOBAL_STATE;

public class WorldLogic {
	public PlayerLogic player;
	public boolean allowHandle = true;
	public EnemyLevel enemyLevel;
	public  int gameOver;
	public int level = 0 ;
	public int countPressed = 0;
	private boolean _resetPlayer = false;
	private World _world;
	private int _getScore; 
	private Body _a,_b;
	private Timer _timer;
	private Level _level;
	private MainStage _mainStage;
	

	public WorldLogic(MainStage _mainStage, Timer _timer,Level level) {

		this._timer = _timer;
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
//				System.out.println("Begin contact a/b: " + _a.getUserData() + "/" + _b.getUserData());
				if (_a.getUserData() != Constants.USERDATA_LIMITED) {
					_resetPlayer = true;
					allowHandle = true;
					if((_a.getUserData() == Constants.USERDATA_ENEMY || _b.getUserData() == Constants.USERDATA_ENEMY)){
						for(GameLogic logic: enemyLevel.getArr()){
							if(logic.getBody() == _b || logic.getBody() == _a)
								logic.hit();
						}
					}
				}
			}
		});

		player = new PlayerLogic(_world);
		enemyLevel = new EnemyLevel(_world);
		initNewLevel();
		new LimitedLogic(_world);
	}

	public void update(){
		catchRunning();
	}
	/*init new level when have new event*/
	public void initNewLevel(){
		if(countPressed > 1){
			 level = _level.level;
			enemyLevel.setArrSence("level/map"+ level);	
			countPressed = 1;
		}
		else{
			level ++;
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
		_world.step(1/60f, 8, 3);
		player.update();
		for(int i= enemyLevel.getArr().size -1 ;i>=0;i--){
			if(enemyLevel.getArr().get(i).isHit() == true){
				enemyLevel.getArr().get(i).getBody().setTransform(Constants.VP_WIDTH -3*Constants.BALL_RADIUS,
						Constants.VP_HEIGHT - 2*Constants.BALL_RADIUS,0);
//				enemyLevel.getArr().get(i).reset();
				_getScore ++;
				break;
			}
		}
		if (_resetPlayer) {
			player.reset();
			_resetPlayer = false;
		}

		//update timer and score
		_timer.updateDeltaTime();
		
		// reset level when over timer
		if(_timer.timer == 0){
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
		allowHandle = true;
		_timer.timer = 10;
		_getScore = 0;
		for(int i=0;i<enemyLevel.getArr().size;i++ )
			_world.destroyBody(enemyLevel.getArr().get(i).getBody());
		enemyLevel.getArr().clear();
		enemyLevel.setArrSence("level/map"+level);
	}
	
	public void nextLevel(){
		_timer.timer = 10;
		_getScore = 0;
		for(int i=0;i < enemyLevel.getArr().size;i++ )
			_world.destroyBody(enemyLevel.getArr().get(i).getBody());
		enemyLevel.getArr().clear();
		initNewLevel();
	}
}

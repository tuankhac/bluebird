package com.bluebirdaward.dynaball.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.bluebirdaward.dynaball.logic.Assets;
import com.bluebirdaward.dynaball.logic.WorldLogic;
import com.bluebirdaward.dynaball.render.Background;
import com.bluebirdaward.dynaball.render.EnemyRender;
import com.bluebirdaward.dynaball.render.EventsButtons;
import com.bluebirdaward.dynaball.render.GridLevel;
import com.bluebirdaward.dynaball.render.Level;
import com.bluebirdaward.dynaball.render.Player;
import com.bluebirdaward.dynaball.render.StartView;
import com.bluebirdaward.dynaball.render.Timer;
import com.bluebirdaward.dynaball.utils.Constants;
import com.bluebirdaward.dynaball.utils.Constants.GLOBAL_STATE;

public class MainStage extends Stage {
	
	public GLOBAL_STATE globalState = GLOBAL_STATE.START;

	private static float VP_HEIGHT = Constants.APP_HEIGHT;
	private static float VP_WIDTH = Constants.APP_WIDTH;
	private OrthographicCamera _gUICam;
	private Box2DDebugRenderer _debugRenderer;


	private Rectangle _screenTopSide;
	private Rectangle _screenBottomSide;
	private Vector3 _touchPoint;

	public WorldLogic _worldLogic;
	
	public Level _level;
	public Timer _timer;
	public Player _player ;
	public Background _background;
	public EnemyRender _enemyRender;

	public GridLevel _gridLevel;
	private StartView _front;

	private EventsButtons _eventButtons;

	public MainStage() {
		super(new ScalingViewport(Scaling.stretch, VP_WIDTH, VP_HEIGHT,
				new OrthographicCamera(VP_WIDTH, VP_HEIGHT)));
		setupCamera();
		setupTouchControlAreas();

		initGame();

		Gdx.input.setInputProcessor(this);
	}

	private void initGame(){
		_debugRenderer = new Box2DDebugRenderer();

		_front = new StartView(this);
		_level = new Level();
		_gridLevel = new GridLevel(this, _level);
		
		_timer = new Timer();
		_background =new Background();
		_worldLogic = new WorldLogic(this,_timer,_level);
		_player = new Player(_worldLogic.player);
		_enemyRender = new EnemyRender(_worldLogic);

		_eventButtons = new EventsButtons(this);
	}

	private void touchUpEventPlayButton(){
		addActor(_eventButtons.btnPlay);
	}

	private void touchUpEventPlayAgainButton(){
		addActor(_eventButtons.btnPlayAgain);
	}
	private void touchUpEventNextButton(){
		addActor(_eventButtons.btnNext);
	}
	
	private void setupNewStart(){
		addActor(_front.btnStart);
		addActor(_front.btnGuide);
	}

	private void setupGridView(){
		addActor(_gridLevel);
	}

	private void setupNewRunning(){
		addActor(_background);
		addActor(_timer);
		addActor(_level);
		addActor(_player);
		for(Actor actor: _enemyRender.getActor()){
			addActor(actor);
		}
	}

	private void setupCamera() {
		_gUICam = new OrthographicCamera(VP_WIDTH, VP_HEIGHT);
		_gUICam.position.set(_gUICam.viewportWidth / 2, _gUICam.viewportHeight / 2, 0f);
		_gUICam.update();
	}

	private void setupTouchControlAreas() {
		_touchPoint = new Vector3();
		float vpHeight = getCamera().viewportHeight / 3;
		_screenBottomSide = new Rectangle(0, 0, getCamera().viewportWidth, vpHeight);
		_screenTopSide = new Rectangle(0, vpHeight, getCamera().viewportWidth, vpHeight * 2);
	}

	private void removeAllActorEnemy(){
		for(Actor actor: _enemyRender.getActor()){
			actor.remove();
		}
		_enemyRender.getActor().clear();
	}

	private void addAllActorEnemy(){
		_enemyRender.init();
		for(Actor actor: _enemyRender.getActor()){
			addActor(actor);
		}
	}

	private void touchUpRunning(int screenX, int screenY){
		float tempX = _gUICam.getPickRay(screenX, screenY).origin.x;
		float tempY = _gUICam.getPickRay(screenX, screenY).origin.y;
		Vector2 velocityOrigin = new Vector2(tempX, tempY);
		getCamera().unproject(_touchPoint.set(screenX, screenY, 0));

		if (bottomSideTouched(_touchPoint.x, _touchPoint.y)) {
			if (_worldLogic.allowHandle){
				_worldLogic.player.setVelocity(velocityOrigin.sub(Constants.AXES_ORIGIN).scl(0.35f));
				_worldLogic.allowHandle = false;
			}

		}
		else if (topSideTouched(_touchPoint.x, _touchPoint.y)) {}
	}

	private void touchUpGridLevel(){
		for (int i = 0; i < _gridLevel.buttons.length; i++) {
			if(_gridLevel.allowActiveButton[i])
				addActor(_gridLevel.buttons[i]);
		}
	}

	private boolean topSideTouched(float x, float y) { return _screenTopSide.contains(x, y); }

	private boolean bottomSideTouched(float x, float y) { return _screenBottomSide.contains(x, y); }

	@Override public void dispose(){
		Assets.instance.dispose();
	}

	/** Logic */
	@Override public void act(float delta) {
		super.act(delta);
		switch (globalState) {
		case START:
			setupNewStart();
			break;

		case GRID_LEVEL:
			_front.remove();
			_front.btnStart.remove();
			_front.btnGuide.remove();
			setupGridView();
			touchUpGridLevel();
			break;

		case PLAY:
			_gridLevel.remove();
			for (int i = 0; i < _gridLevel.buttons.length; i++) {
				_gridLevel.buttons[i].remove();
			}
			setupNewRunning();
			touchUpEventPlayButton();
			break;

		case RUNNING:
			_debugRenderer.render(_worldLogic.getWorldLogic(), _gUICam.combined);
			if(_worldLogic.gameOver == 1){
				removeAllActorEnemy();
			}
			else if(_worldLogic.gameOver == -1){
				removeAllActorEnemy();
			}
			
			_worldLogic.update();
			
			if(_worldLogic.gameOver == 1){
				addAllActorEnemy();
				_worldLogic.gameOver = 0;
			}
			else if(_worldLogic.gameOver == -1){
				addAllActorEnemy();
				_worldLogic.gameOver = 0;
			}
			addActor(_eventButtons.btnBackRunning);
			break;

		case GAMEOVER:
			touchUpEventPlayAgainButton();
			_worldLogic.gameOver = -1;
			break;
		case NEXT:
			touchUpEventNextButton();
			_worldLogic.gameOver = 1;
			break;
		default:
			break;
		}
	}

	@Override public boolean touchUp(int screenX, int screenY, int pointer, int button) {

		switch (globalState) {
		case RUNNING:
			touchUpRunning(screenX,screenY);
			break;

		default:
			break;
		}

		return super.touchUp(screenX, screenY, pointer, button);
	}

	@Override public boolean touchDragged(int screenX, int screenY, int pointer) {

		return super.touchDragged(screenX, screenY, pointer);
	}
	@Override public boolean touchDown(int x, int y, int pointer, int button) {
		// Need to get the actual coordinates
		getCamera().unproject(_touchPoint.set(x, y, 0));

		return super.touchDown(x, y, pointer, button);
	}
}

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
import com.bluebirdaward.dynaball.render.EnemyRender;
import com.bluebirdaward.dynaball.render.EventsButtons;
import com.bluebirdaward.dynaball.render.GridLevel;
import com.bluebirdaward.dynaball.render.Level;
import com.bluebirdaward.dynaball.render.Player;
import com.bluebirdaward.dynaball.render.StartView;
import com.bluebirdaward.dynaball.utils.Constants;
import com.bluebirdaward.dynaball.utils.Constants.GLOBAL_STATE;

public class MainStage extends Stage {

	public GLOBAL_STATE globalState = GLOBAL_STATE.RUNNING;

	private static float VP_HEIGHT = Constants.APP_HEIGHT;
	private static float VP_WIDTH = Constants.APP_WIDTH;
	private OrthographicCamera _gUICam;
	private Box2DDebugRenderer _debugRenderer;

	private Rectangle _screenTopSide;
	private Rectangle _screenBottomSide;
	private Vector3 _touchPoint;


	private	 Player _player ;
	private EnemyRender _enemyRender;

	private GridLevel _gridLevel;
	private StartView _front;
	private EventsButtons _eventButtons;

	private int vitri = 0;

	public WorldLogic _worldLogic;
	public Level _level;
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
		_gridLevel = new GridLevel(this);

		_level = new Level();
		_worldLogic = new WorldLogic(this,_level);
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

	private void setupNewStart(){
		addActor(_front.btnStart);
		addActor(_front.btnGuide);
	}

	private void setupGridView(){
		addActor(_gridLevel);
	}

	public void setupNewRunning(){
		_enemyRender.init();
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

	public void removeAllActorEnemy(){
		for (int i = 0; i < _gridLevel.buttons.length; i++) {
			_gridLevel.buttons[i].remove();
		}
		_gridLevel.remove();
		
		_level.remove();
		_player.remove();
		for(Actor actor: _enemyRender.getActor()){
			actor.remove();
		}
		_enemyRender.getActor().clear();
	}

	//	public void addAllActorEnemy(){
	//		_enemyRender.init();
	//		for(Actor actor: _enemyRender.getActor()){
	//			addActor(actor);
	//		}
	//	}

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
		_front._buttonsAtlas.dispose();
		_gridLevel.buttonsAtlas.dispose();
	}

	/** Logic */
	@Override public void act(float delta) {
		super.act(delta);
		System.out.println(Gdx.app.getJavaHeap());
		switch (globalState) {
		case START:
			setupNewStart();
			break;

		case GRID_LEVEL:
			_front.remove();
			_front.btnStart.remove();
			_front.btnGuide.remove();
			if(_worldLogic.gameOver == 1){
//				_level.remove();
//				_player.remove();
//				for (int i = 0; i < _gridLevel.buttons.length; i++) {
//					_gridLevel.buttons[i].remove();
//				}
//				_gridLevel.remove();
				removeAllActorEnemy();
				if(vitri < _worldLogic.level){
					vitri = _worldLogic.level;
					_gridLevel._displayGridLevel[_worldLogic.level - 1] = 1;
					_gridLevel._displayGridLevel[_worldLogic.level] = 0; 
					_gridLevel.allowActiveButton[_worldLogic.level] = true;
				}
				_gridLevel.initGridLevel();
			}

			setupGridView();
			touchUpGridLevel();
			break;

		case PLAY:

			for (int i = 0; i < _gridLevel.buttons.length; i++) {
				_gridLevel.buttons[i].remove();
			}
			_gridLevel.remove();
			if(_worldLogic.gameOver == 1){
				
				_worldLogic.nextLevel();

				_worldLogic.gameOver = 0;
			}

			setupNewRunning();
			touchUpEventPlayButton();
			break;

		case RUNNING:
			_debugRenderer.render(_worldLogic.getWorldLogic(), _gUICam.combined);
			_worldLogic.update();
			for(int i= _worldLogic.enemyLevel.getArr().size -1 ;i >= 0;i--){
				if(_worldLogic.enemyLevel.getArr().get(i).isHit() == true){
					_enemyRender.getActor().get(i).setVisible(false);
					_worldLogic.enemyLevel.getArr().get(i).reset();
					break;
				}
			}

			break;

		case GAMEOVER:
			_level.remove();
			_player.remove();
			removeAllActorEnemy();
			_worldLogic.resetLevel();
			_worldLogic.gameOver = 0;
			touchUpEventPlayAgainButton();
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

package com.bluebirdaward.dangerball.stages;
/*
 *  created by tuankhac 
 *  group losers
 *  update 31/7/2015
 * */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.bluebirdaward.dangerball.SaveScore;
import com.bluebirdaward.dangerball.logic.Assets;
import com.bluebirdaward.dangerball.logic.WorldLogic;
import com.bluebirdaward.dangerball.render.EnemyRender;
import com.bluebirdaward.dangerball.render.EventsButtons;
import com.bluebirdaward.dangerball.render.GridLevel;
import com.bluebirdaward.dangerball.render.Level;
import com.bluebirdaward.dangerball.render.Player;
import com.bluebirdaward.dangerball.render.StartView;
import com.bluebirdaward.dangerball.screens.IActivityRequestHandler;
import com.bluebirdaward.dangerball.utils.Constants;
import com.bluebirdaward.dangerball.utils.Constants.GLOBAL_STATE;

public class MainStage extends Stage {

	private static float VP_HEIGHT = Constants.APP_HEIGHT;
	private static float VP_WIDTH = Constants.APP_WIDTH;
	private OrthographicCamera _gUICam;

	private Rectangle _screenTopSide;
	private Rectangle _screenBottomSide;
	private Vector3 _touchPoint;

	private Level _level;
	private	Player _player ;
	private EnemyRender _enemyRender;

	private GridLevel _gridLevel;
	private StartView _front;
	private EventsButtons _eventButtons;

	private byte vitri = 0;
	private boolean addedPlay = true;
	private boolean addedGrid = true;
	private boolean addedStart = true;
	private boolean addedPlayAgain = true;
	private boolean addedBack =true;
	private WorldLogic _worldLogic;

	private boolean isShowAd = true;
	private IActivityRequestHandler myRqstHandler;

	public MainStage(IActivityRequestHandler iActHandler) {
		super(new ScalingViewport(Scaling.stretch, VP_WIDTH, VP_HEIGHT,	new OrthographicCamera(VP_WIDTH, VP_HEIGHT)));
		this.myRqstHandler = iActHandler;
		setupCamera();
		setupTouchControlAreas();

		initGame();

		Gdx.input.setInputProcessor(this);
	}

	private void initGame(){
		_front = new StartView();
		_gridLevel = new GridLevel();

		_level = new Level();
		_worldLogic = new WorldLogic(_level);
		_player = new Player(_worldLogic.player);
		_enemyRender = new EnemyRender(_worldLogic);

		_eventButtons = new EventsButtons();
		
	}
	
	private void setupCamera() {
		_gUICam = new OrthographicCamera(VP_WIDTH, VP_HEIGHT);
		_gUICam.position.set(14f*Constants.BALL_RADIUS, _gUICam.viewportHeight / 2, 0f);
		_gUICam.update();
	}

	private void setupTouchControlAreas() {
		_touchPoint = new Vector3();
		float vpHeight = getCamera().viewportHeight / 3;
		_screenBottomSide = new Rectangle(0, 0, getCamera().viewportWidth, vpHeight);
		_screenTopSide = new Rectangle(0, vpHeight, getCamera().viewportWidth, vpHeight * 2);
	}
	
	private void setupNewStart(){
		addActor(_front);
		addActor(_front.btnStart);
		addActor(_front.btnGuide);
		
	}

	private void setupNewRunning(){
		_enemyRender.init();
		addActor(_level);
		addActor(_player);
		for(Actor actor: _enemyRender.getActor()){
			addActor(actor);
		}
		addActor(_eventButtons);
	}

	private void removeGridView(){
		_gridLevel.remove();
		for (int i = 0; i < _gridLevel.buttons.length; i++) {
			_gridLevel.buttons[i].remove();
		}
	}
	
	private void removeAllActor(){
		removeGridView();
		_level.remove();
		_player.remove();
		for(Actor actor: _enemyRender.getActor()){
			actor.remove();
		}
		_enemyRender.getActor().clear();
	}

	private void touchUpEventPlayButton(){
		addActor(_eventButtons.btnPlay);
	}

	private void touchUpEventPlayAgainButton(){

		addActor(_eventButtons.btnPlayAgain);
	}

	private void touchGridButton(){
		_worldLogic.countPressed ++;
		_level.level = _gridLevel.display;
		_worldLogic.nextLevel();
		
		Constants.globalState = GLOBAL_STATE.PLAY;
	}
	
	private void touchPlayAgain(){
		removeAllActor();
		_worldLogic.resetLevel();
		_worldLogic.gameOver = 0;
		
		setupNewRunning();
		Constants.globalState = GLOBAL_STATE.RUNNING;
		addedPlayAgain = true;
	}
	
	private void touchUpGridLevel(){
		addActor(_gridLevel);
		for (int i = 0; i < _gridLevel.buttons.length; i++) {
			if(_gridLevel.allowActiveButton[i])
				addActor(_gridLevel.buttons[i]);
		}
	}

	private boolean topSideTouched(float x, float y) { return _screenTopSide.contains(x, y); }

	private boolean bottomSideTouched(float x, float y) { return _screenBottomSide.contains(x, y); }

	@Override public void dispose(){
		addedStart = true;
		isShowAd = true;
		Assets.instance.dispose();
	}

	/** Logic */
	@Override public void act(float delta) {
		super.act(delta);
		switch (Constants.globalState) {
		case START:
			if(addedStart){
				setupNewStart();
				addedStart = false;
			}
//			if (isShowAd)  {
//				myRqstHandler.showAds(true);
//				isShowAd = false;
//			}
			break;
		case GUIDE:
			if(addedBack) {
				
				_front.btnStart.remove();
				_front.btnGuide.remove();
				addActor(_eventButtons.btnBack);
				addedBack = false;
			}
			if(_eventButtons.touchedBack){
				addedStart = true;
				addedBack = true;
				_eventButtons.touchedBack = false;
				_front.remove();
				Constants.globalState = GLOBAL_STATE.START;
			}
			break;
		case GRID_LEVEL:
			_front.remove();
			_front.btnStart.remove();
			_front.btnGuide.remove();
			if(_worldLogic.gameOver == 1){
				removeAllActor();
				if(SaveScore.getDisplayGridLevel() < _worldLogic.level){
					vitri = _worldLogic.level;
					_gridLevel.displayGridLevel[_worldLogic.level - 1] = 1;
					if(_worldLogic.level< 20){
						_gridLevel.displayGridLevel[_worldLogic.level] = 0; 
						_gridLevel.allowActiveButton[_worldLogic.level] = true;
					}
					else{
						Constants.globalState = GLOBAL_STATE.CONGRATULATION;

					}
					SaveScore.saveData(vitri);
				}
				addedGrid = true;
			}
			if(Constants.globalState !=GLOBAL_STATE.CONGRATULATION){
				if(addedGrid){
					_gridLevel.initGridLevel();
					touchUpGridLevel();
					addedGrid = false;
				}
				if(_gridLevel.touchedGridButton){
					touchGridButton();
					_gridLevel.touchedGridButton = false;
				}
			}
			break;

		case PLAY:
			removeGridView();

			if(_worldLogic.gameOver == 1){
				_worldLogic.gameOver = 0;
				addedPlay = true;
				addedGrid = true;
				addedPlayAgain = true;
			}
			if(addedPlay){
				setupNewRunning();
				touchUpEventPlayButton();
				addedPlay = false;
			}
			break;

		case RUNNING:
//			if (!isShowAd ) {
//				myRqstHandler.showAds(false);
//				isShowAd = true;
//			}
			_worldLogic.update();
			for(int i= _worldLogic.enemyLevel.getArr().size -1 ;i >= 0;i--){
				if(_worldLogic.enemyLevel.getArr().get(i).isHit() == true){
					_enemyRender.getActor().get(i).remove();
					_enemyRender.getActor().get(i).clear();
					_worldLogic.enemyLevel.getArr().get(i).getBody().setTransform(Constants.VP_WIDTH -3*Constants.BALL_RADIUS,
							Constants.VP_HEIGHT - 2*Constants.BALL_RADIUS,0);
					_worldLogic.enemyLevel.getArr().get(i).reset();
					break;
				}
			}
			
			break;

		case GAMEOVER:
			if(addedPlayAgain){
				touchUpEventPlayAgainButton();
				addedPlayAgain = false;
			}
			if(_eventButtons.touchedPlayAgain){
				touchPlayAgain();
				_eventButtons.touchedPlayAgain = false;
			}
			break;
		default:
			break;
		}
	}

	private void touchUpRunning(int screenX, int screenY){
		
		float tempX = _gUICam.getPickRay(screenX, screenY).origin.x;
		float tempY = _gUICam.getPickRay(screenX, screenY).origin.y;
		Vector2 velocityOrigin = new Vector2(tempX, tempY);
		getCamera().unproject(_touchPoint.set(screenX, screenY, 0));
		if (bottomSideTouched(_touchPoint.x, _touchPoint.y)) {
			if (_worldLogic.allowPlayerHandle){
				_worldLogic.player.setVelocity(velocityOrigin.scl(0.25f));
				_worldLogic.allowPlayerHandle = false;
			}

		}
		else if (topSideTouched(_touchPoint.x, _touchPoint.y)) {}
	}

	private void touchDragRunning(int screenX, int screenY){
		if (bottomSideTouched(_touchPoint.x, _touchPoint.y)) {
			if (_worldLogic.allowPlayerHandle){
				float tempX  = _gUICam.getPickRay(screenX, screenY).origin.x;
				float tempY  = _gUICam.getPickRay(screenX, screenY).origin.y;
					_level.rotation = -(float)Math.toDegrees(Math.atan2(tempX,tempY)) ;
			}
		}else if (topSideTouched(_touchPoint.x, _touchPoint.y)) ;
	}

	@Override public boolean touchUp(int screenX, int screenY, int pointer, int button) {

		switch (Constants.globalState) {
		case RUNNING:
			touchUpRunning(screenX,screenY);
			break;

		default:
			break;
		}
		return super.touchUp(screenX, screenY, pointer, button);
	}

	@Override public boolean touchDragged(int screenX, int screenY, int pointer) {

		switch (Constants.globalState) {
		case RUNNING:
			touchDragRunning(screenX, screenY);
			break;

		default:
			break;
		}
		return super.touchDragged(screenX, screenY, pointer);
	}

	@Override public boolean touchDown(int x, int y, int pointer, int button) {
		// Need to get the actual coordinates
		getCamera().unproject(_touchPoint.set(x, y, 0));
		switch (Constants.globalState) {
		case RUNNING:
			touchDragRunning(x, y);
			break;
		default:
			break;
		}
		return super.touchDown(x, y, pointer, button);
	}
}

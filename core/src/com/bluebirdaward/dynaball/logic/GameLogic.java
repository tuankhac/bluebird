package com.bluebirdaward.dynaball.logic;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.bluebirdaward.dynaball.utils.Constants;

public class GameLogic {
	private Body _gameObjBody;
	private float _width;
	private float _height;
	
	protected Vector2 position;
	protected boolean hit;
	
	public boolean allowMotionHorizontal = false ;
	public boolean allowMotionVertical = false ;
	public boolean allowHit = false;
	
	public GameLogic() {
		this.position = new Vector2();
		hit = false;
		_width = _height = Constants.BALL_RADIUS;
	}
	
	public GameLogic(World world) {
		this.position = new Vector2();
		hit = false;
		_width = _height = Constants.BALL_RADIUS;
		initKinematicLimited(world, Constants.USERDATA_BARIE,
				Constants.BALL_RADIUS*2, Constants.BALL_RADIUS);
	}
	protected void gameOver(Vector2 currentPosition) {
		setVelocity(0, 0);
		setPosition(currentPosition);
	}
	
//	protected void reset() { hit = false; }
	public void reset() { hit = false; }
	
	public void hit() { hit = true; }
	
	public boolean isHit() { return hit; }
	
	protected void initDynamicBall(World world, Object userData) {
		createBall(world, userData, BodyType.DynamicBody);
	}
	
	protected void initStaticBall(World world, Object userData) {
		createBall(world, userData, BodyType.StaticBody);
	}
	
	protected void initKinematicBall(World world, Object userData) {
		createBall(world, userData, BodyType.KinematicBody);
	}
	
	private void createBall (World world, Object userData, BodyType bdtype){
		BodyDef bdef = new BodyDef();
		bdef.type = bdtype;
		bdef.fixedRotation = true;
		_gameObjBody = world.createBody(bdef);
		CircleShape circle = new CircleShape();
        circle.setRadius(Constants.BALL_RADIUS);
		FixtureDef fd  = new FixtureDef();
		fd.shape = circle;
		fd.density = 0.9f;
		fd.friction = 0f;
		fd.restitution = 1;
		_gameObjBody.createFixture(fd);
		_gameObjBody.setUserData(userData);
		circle.dispose();
	}
	
	protected void initDynamicLimited(World world, Object userData, float hWidth, float hHeight) {
		createBarie(world, userData, BodyType.DynamicBody, hWidth, hHeight);
	}
	
	protected void initStaticLimited(World world, Object userData, float hWidth, float hHeight, float x, float y) {
		createBarie(world, userData, BodyType.StaticBody, hWidth, hHeight);
		setPosition(x, y);
	}
	
	protected void initKinematicLimited(World world, Object userData, float width, float height) {
		createBarie(world, userData, BodyType.KinematicBody, width, height);
	}
	
	private void createBarie (World world, Object userData, BodyType bdtype, float hWidth, float hHeight){
		BodyDef bdef = new BodyDef();
		bdef.type = bdtype;
		bdef.fixedRotation = true;
		_gameObjBody = world.createBody(bdef);
		PolygonShape shape = new PolygonShape();
        shape.setAsBox(hWidth/2, hHeight/2);
		FixtureDef fd  = new FixtureDef();
		fd.shape = shape;
		fd.density = 0.9f;
		fd.friction = 0.8f;
		fd.restitution = 1;
		_gameObjBody.createFixture(fd);
		_gameObjBody.resetMassData();
		_gameObjBody.setUserData(userData);
		shape.dispose();
		_width = hWidth;
		_height = hHeight;
	}
	
	/**
	 * update position for Sprite via Body */
	public void update() {
		position = _gameObjBody.getPosition();
	}
	
	public void mAllowMotion(float vX, float vY){
		setVelocity(vX,vY);
	}
	
	// Get/set functions
	public Vector2 getPosition() { return position; }
	
	public void setPosition(float x, float y) { _gameObjBody.setTransform(x, y, 0); }
	
	public void setPosition(Vector2 pos) { _gameObjBody.setTransform(pos.x, pos.y, 0); }
	
	public void setVelocity(float x, float y) { _gameObjBody.setLinearVelocity(x,y); }
	
	public void setVelocity(Vector2 velocity) { _gameObjBody.setLinearVelocity(velocity.x, velocity.y);}
	
	public Vector2 getVelocity(){ return _gameObjBody.getLinearVelocity();}
	
	public float getWidth(){ return _width;}
	
	public float getHeight() { return _height; }
	
	public Body getBody(){ return _gameObjBody;}
}

package com.bluebirdaward.dangerball.render;
/*
 *  created by tuankhac 
 *  group losers
 *  update 31/7/2015
 * */
import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bluebirdaward.dangerball.logic.WorldLogic;
import com.bluebirdaward.dangerball.utils.Constants;

<<<<<<< HEAD
public class EnemyRender {
	private Array<Actor> _enemy = new Array<Actor>();

	private WorldLogic _worldLogic;

	public EnemyRender(WorldLogic worldLogic) {	this._worldLogic = worldLogic;	}

	public void init(){
		for(int i=0 ;i< _worldLogic.enemyLevel.getArr().size;i++){
			if (_worldLogic.enemyLevel.getArr().get(i).getBody().getUserData() == Constants.USERDATA_ENEMY) 
				_enemy.add(new Balloon((EnemyLogic)_worldLogic.enemyLevel.getArr().get(i)));
			else
				_enemy.add(new Bar((BarieLogic)_worldLogic.enemyLevel.getArr().get(i)));
		}
	}

	public Array<Actor> getActor(){	return _enemy; }
=======
public class EnemyRender extends Actor {
	private ArrayList<RenderActor> _enemy = new ArrayList<RenderActor>();

	private WorldLogic _worldLogic;

	public EnemyRender(WorldLogic worldLogic) {
		this._worldLogic = worldLogic;
	}

	@Override public void act(float delta){
		super.act(delta);
		for(int i=0 ;i< getActor().size();i++)
			_enemy.get(i).act();
	}

	@Override public void draw(Batch batch, float parentAlpha ){
		for(int i=0 ;i< getActor().size();i++)
				_enemy.get(i).draw(batch);
		super.draw(batch, parentAlpha);
	}

	public void init(){
		for(int i=0 ;i< _worldLogic.enemyLevel.getArr().size();i++)
			if (_worldLogic.enemyLevel.getArr().get(i).getBody().getUserData() == Constants.USERDATA_ENEMY) 
				_enemy.add(new Balloon(_worldLogic.enemyLevel.getArr().get(i)));
			else
				_enemy.add(new Bar(_worldLogic.enemyLevel.getArr().get(i)));
	}

	public ArrayList<RenderActor> getActor(){
		return _enemy;
	}
>>>>>>> 5c9816ea6841b230c2e1df31d3711646ff55e128
}

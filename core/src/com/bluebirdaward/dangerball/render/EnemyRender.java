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
}

package com.bluebirdaward.dangerball.logic;
/*
 *  created by tuankhac 
 *  group losers
 *  update 31/7/2015
 * */
import java.io.BufferedReader;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.bluebirdaward.dangerball.utils.Constants;

public class EnemyLevel {
	private byte _countEnemy;
	private World _world; 

	private EnemyLogic _enemyLogic;
	private BarieLogic _barieLogic;
	private float width = Constants.VP_WIDTH;
	private float height = Constants.VP_HEIGHT;
	private float ball_radius = Constants.BALL_RADIUS;
	private Array<GameLogic> _listLogic = new Array<GameLogic>();

	EnemyLevel(World world) {	this._world = world; }

	byte countEnemy(){ return _countEnemy; }

	public Array<GameLogic> getArr(){ return _listLogic; }

	//load map from text
	void setArrSence(String path ){
		_countEnemy = 0;
		FileHandle fileHandle = Gdx.files.internal(path);
		BufferedReader in = new BufferedReader(fileHandle.reader());
		String content;
		int j = 0;
		try {
			while((content=in.readLine())!=null){
				for(int i=0;i<content.length();i++){
					int index=Integer.parseInt(content.charAt(i)+"");
					if(index > 0){
						switch (index) {
						case 1:
							_enemyLogic = new EnemyLogic(this._world, Constants.USERDATA_ENEMY);
							_enemyLogic.setPosition((i+1) * width/15 - ball_radius , (25-j)*height/25 - ball_radius);
							_listLogic.add(_enemyLogic);
							_countEnemy ++;
							break;
						case 2:
							_barieLogic = new BarieLogic(this._world,false,false,false);
							_barieLogic.setPosition((i+1) * width/15 - ball_radius, (25-j)*height/25 - ball_radius);
							_listLogic.add(_barieLogic);
							break;
						case 3:
							_barieLogic = new BarieLogic(this._world,true,false,false);
							_barieLogic.setPosition((i+1) * width/15 - ball_radius, (25-j)*height/25 - ball_radius);
							_listLogic.add(_barieLogic);
							break;
						case 4:
							_barieLogic = new BarieLogic(this._world,false,true,false);
							_barieLogic.setPosition((i+1) * width/15 - ball_radius, (25-j)*height/25 - ball_radius);
							_listLogic.add(_barieLogic);
							break;
						case 5:
							_barieLogic = new BarieLogic(this._world,false,false, true);
							_barieLogic.setPosition((i+1) * width/15 - ball_radius, (25-j)*height/25 - ball_radius);
							_listLogic.add(_barieLogic);
							break;
						default:
							break;
						}
					}
				}
				j++;
			}
		} catch (IOException ie) {
			ie.printStackTrace();
		}

	}
}

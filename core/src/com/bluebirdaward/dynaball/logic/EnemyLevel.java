package com.bluebirdaward.dynaball.logic;

import java.io.BufferedReader;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.bluebirdaward.dynaball.utils.Constants;

public class EnemyLevel {
	private int _countEnemy;
	private World _world; 
	
	public EnemyLogic enemyLogic;
	public BarieLogic barieLogic;
	private Array<GameLogic> _listLogic = new Array<GameLogic>();

	public EnemyLevel(World world) {
		this._world = world;
	}

	int countEnemy(){ return _countEnemy;}

	public Array<GameLogic> getArr(){ return _listLogic; }

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
							enemyLogic = new EnemyLogic(this._world, Constants.USERDATA_ENEMY);
							enemyLogic.setPosition((i+1) * Constants.VP_WIDTH/15 - Constants.BALL_RADIUS , (25-j)*Constants.VP_HEIGHT/25 - Constants.BALL_RADIUS);
							_listLogic.add(enemyLogic);
							_countEnemy ++;
							break;
						case 2:
							barieLogic = new BarieLogic(this._world,false,false,false);
							barieLogic.setPosition((i+1) * Constants.VP_WIDTH/15 - Constants.BALL_RADIUS, (25-j)*Constants.VP_HEIGHT/25 - Constants.BALL_RADIUS);
							_listLogic.add(barieLogic);
							break;
						case 3:
							barieLogic = new BarieLogic(this._world,true,false,false);
							barieLogic.setPosition((i+1) * Constants.VP_WIDTH/15 - Constants.BALL_RADIUS, (25-j)*Constants.VP_HEIGHT/25 - Constants.BALL_RADIUS);
							_listLogic.add(barieLogic);
							break;
						case 4:
							barieLogic = new BarieLogic(this._world,false,true,false);
							barieLogic.setPosition((i+1) * Constants.VP_WIDTH/15 - Constants.BALL_RADIUS, (25-j)*Constants.VP_HEIGHT/25 - Constants.BALL_RADIUS);
							_listLogic.add(barieLogic);
							break;
						case 5:
							barieLogic = new BarieLogic(this._world,false,false, true);
							barieLogic.setPosition((i+1) * Constants.VP_WIDTH/15 - Constants.BALL_RADIUS, (25-j)*Constants.VP_HEIGHT/25 - Constants.BALL_RADIUS);
							_listLogic.add(barieLogic);
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

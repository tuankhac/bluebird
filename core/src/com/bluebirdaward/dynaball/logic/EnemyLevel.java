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
	private EnemyLogic _enemyLogic;
	private GameLogic _barieLogic;
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
							_enemyLogic = new EnemyLogic(this._world, Constants.USERDATA_ENEMY);
							_enemyLogic.setPosition((i+1) * Constants.VP_WIDTH/12 , (20-j)*Constants.VP_HEIGHT/20 );
							_listLogic.add(_enemyLogic);
							_countEnemy ++;
							break;
						case 2:
							_barieLogic = new BarieLogic(this._world);
							_barieLogic.setPosition((i+1) * Constants.VP_WIDTH/12, (20-j)*Constants.VP_HEIGHT/20 );
							_listLogic.add(_barieLogic);
							break;
						case 3:

							break;
						case 4:

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

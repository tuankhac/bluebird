
package com.bluebirdaward.dangerball.logic;
/*
 *  created by tuankhac 
 *  group losers
 *  update 31/7/2015
 * */
import com.badlogic.gdx.physics.box2d.World;

public class EnemyLogic extends GameLogic {

	 EnemyLogic(World world, Object userdata) {
		super();
		initKinematicBall(world, userdata);
	}
}

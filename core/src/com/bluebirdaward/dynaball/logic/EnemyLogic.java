package com.bluebirdaward.dynaball.logic;

import com.badlogic.gdx.physics.box2d.World;

public class EnemyLogic extends GameLogic {

	public EnemyLogic(World world, Object userdata) {
		super();
//		initDynamicBall(world, userdata);
		initKinematicBall(world, userdata);
	}
}

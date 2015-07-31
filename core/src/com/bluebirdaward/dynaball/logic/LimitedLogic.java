package com.bluebirdaward.dynaball.logic;
/*
 *  created by tuankhac 
 *  group losers
 *  update 31/7/2015
 * */
import com.badlogic.gdx.physics.box2d.World;
import com.bluebirdaward.dynaball.utils.Constants;

class LimitedLogic extends GameLogic {

	public LimitedLogic(World world) {
		super();
		initStaticLimited(world, Constants.USERDATA_GROUND,					// GROUND
				Constants.VP_WIDTH*1.5f, Constants.GROUND_HEIGHT/2,
				Constants.VP_WIDTH/2, Constants.GROUND_HEIGHT/2);
		initStaticLimited(world, Constants.USERDATA_LIMITED,				// LEFT WALL
				Constants.WALL_WIDTH/2, Constants.VP_HEIGHT*1.5f,
				0, Constants.VP_HEIGHT/2);
		initStaticLimited(world, Constants.USERDATA_LIMITED,				// RIGHT WALL
				Constants.WALL_WIDTH/2, Constants.VP_HEIGHT*1.5f,
				Constants.VP_WIDTH, Constants.VP_HEIGHT/2);
		initStaticLimited(world, Constants.USERDATA_LIMITED,				// BARE
				Constants.VP_WIDTH*1.5f, Constants.GROUND_HEIGHT * 15,
				Constants.VP_WIDTH/2, Constants.VP_HEIGHT-Constants.GROUND_HEIGHT/2);
	}
}

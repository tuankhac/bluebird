package com.bluebirdaward.dynaball.logic;

import com.badlogic.gdx.physics.box2d.World;
import com.bluebirdaward.dynaball.utils.Constants;

public class PlayerLogic extends GameLogic{

	public PlayerLogic (World world){
		super();
		initDynamicBall(world, Constants.USERDATA_PLAYER);
		reset();
	}
	
	 public void reset() {
		setPosition(Constants.VP_WIDTH/2, Constants.PLAYER_Y0  + Constants.BALL_RADIUS);
		setVelocity(0, 0);
	}
}

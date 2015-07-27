package com.bluebirdaward.dynaball.logic;

import com.badlogic.gdx.physics.box2d.World;
import com.bluebirdaward.dynaball.utils.Constants;

public class BarieLogic extends GameLogic {

	public BarieLogic(World world,boolean horizontal, boolean vertical, boolean allowHit ) {
		super();
		this.allowMotionHorizontal = horizontal;
		this.allowMotionVertical = vertical;
		this.allowHit = allowHit;
		initKinematicLimited(world, Constants.USERDATA_BARIE,
				Constants.BALL_RADIUS*2f, 2f*Constants.BALL_RADIUS);
	}
}

package com.bluebirdaward.dynaball.utils;

import com.badlogic.gdx.math.Vector2;

public class Constants {
	public static String TEXTURE_ATLAS_OBJECTS = "images/dynaball.atlas";
	public static final float VP_WIDTH = 30;
	public static final float VP_HEIGHT = 50;
	public static final int APP_WIDTH = 480;
	public static final int APP_HEIGHT = 800;
	public static final float LOGIC_TO_RENDER = 16;

	public static final Vector2 WORLD_GRAVITY = new Vector2(0, 0);
	public static final float GROUND_HEIGHT = 0.4f;

	public static final float BALL_RADIUS = 1f;
	public static final float PLAYER_Y0 = GROUND_HEIGHT + BALL_RADIUS;

	public static final float WALL_WIDTH = 0.4f;
	public static final float BARIE_WIDTH = 10f;
	public static final float BARIE_HEIGHT = 1.6f;
	public static final float LIZARD_GRAVITY_SCALE = 6f;

	public static final String USERDATA_PLAYER = "PLAYER";
	public static final String USERDATA_ENEMY = "ENEMY";
	public static final String USERDATA_BARIE = "BARIE";
	public static final String USERDATA_GROUND = "GROUND";
	public static final String USERDATA_LIMITED = "LIMITED";

	public static final Vector2 AXES_ORIGIN = new Vector2(APP_WIDTH/2, PLAYER_Y0);

	public enum GLOBAL_STATE { RUNNING,GAMEOVER,START,PLAY,GUIDE,GRID_LEVEL,NEXT }
}

package com.bluebirdaward.dangerball.utils;
/*
 *  created by tuankhac 
 *  group losers
 *  update 31/7/2015
 * */
import com.badlogic.gdx.math.Vector2;

public class Constants {
	public static String TEXTURE_ATLAS_OBJECTS = "images/dynaball.atlas";
	public static final float VP_WIDTH = 30;
	public static final float VP_HEIGHT = 50;
	public static final float APP_WIDTH = 480;
	public static final float APP_HEIGHT = 800;
	public static final float LOGIC_TO_RENDER = 16;

	public static final Vector2 WORLD_GRAVITY = new Vector2(0, 0);
	public static final float GROUND_HEIGHT = 0.4f;

	public static final float BALL_RADIUS = 1f;
	public static final float PLAYER_Y0 = GROUND_HEIGHT + BALL_RADIUS;

	public static final float WALL_WIDTH = 0.4f;
	public static final float BARIE_WIDTH = 10f;

	public static final String USERDATA_PLAYER = "PLAYER";
	public static final String USERDATA_ENEMY = "ENEMY";
	public static final String USERDATA_BARIE = "BARIE";
	public static final String USERDATA_GROUND = "GROUND";
	public static final String USERDATA_LIMITED = "LIMITED";

	public static final Vector2 AXES_ORIGIN = new Vector2(APP_WIDTH/2, PLAYER_Y0);

	public enum GLOBAL_STATE { RUNNING,GAMEOVER,START,PLAY,GUIDE,GRID_LEVEL,NEXT,CONGRATULATION }
	public static GLOBAL_STATE globalState = GLOBAL_STATE.START;

	public enum TIMER{
		LEVEL1(15),LEVEL2(15), LEVEL3(20), LEVEL4(20),LEVEL5(20), LEVEL6(20), LEVEL7(20),LEVEL8(20), LEVEL9(20),LEVEL10(10),
		LEVEL11(20),LEVEL12(20), LEVEL13(20), LEVEL14(20),LEVEL15(20), LEVEL16(20), LEVEL17(20),LEVEL18(20), LEVEL19(20), LEVEL20(20);

		private final int value;

		private TIMER(final int newValue) {
			value = newValue;
		}

		public int getValue() { return value; }
	}

	public enum VELOCITY{
		LEVEL1(0f,0f), LEVEL2(0f,0f), LEVEL3(0f,0f), LEVEL4(0f,0f),LEVEL5(0f,0f),
		LEVEL6(2f,0f), LEVEL7(2f,0f), LEVEL8(0f,2f), LEVEL9(0,3f),LEVEL10(2f,2f), LEVEL11(0f,0f),LEVEL12(0f,0f), 
		LEVEL13(4f,4f), LEVEL14(-5f,3f),LEVEL15(0f,0f), LEVEL16(2f,2f), LEVEL17(2,2),LEVEL18(2,2), LEVEL19(2,2), LEVEL20(2,2);

		private float  vX;
		private float vY;
		private VELOCITY(final float vX,final float vY) {
			this.vX = vX;
			this.vY = vY;
		}

		public float getVX() { return vX; }
		public float getVY() { return vY; }
	}
	
	public enum SETTIMER{
		LEVEL1(0),LEVEL2(0), LEVEL3(0), LEVEL4(0),LEVEL5(0), LEVEL6(2), LEVEL7(1),LEVEL8(2), LEVEL9(1),LEVEL10(2),
		LEVEL11(2),LEVEL12(2), LEVEL13(2), LEVEL14(1f),LEVEL15(2), LEVEL16(2), LEVEL17(2),LEVEL18(2), LEVEL19(2), LEVEL20(2);

		private final float value;

		private SETTIMER(final float newValue) {
			value = newValue;
		}

		public float getValue() { return value; }
	}
}

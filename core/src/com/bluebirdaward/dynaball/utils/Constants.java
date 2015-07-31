package com.bluebirdaward.dynaball.utils;
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
	
	public enum TIMER{
        LEVEL1(10),LEVEL2(15), LEVEL3(15), LEVEL4(15),LEVEL5(15), LEVEL6(20), LEVEL7(20),LEVEL8(20), LEVEL9(20),LEVEL10(20),
        LEVEL11(20),LEVEL12(20), LEVEL13(20), LEVEL14(20),LEVEL15(20), LEVEL16(20), LEVEL17(20),LEVEL18(20), LEVEL19(20), LEVEL20(20);
        
        private final int value;

        private TIMER(final int newValue) {
            value = newValue;
        }

        public int getValue() { return value; }
    }
}

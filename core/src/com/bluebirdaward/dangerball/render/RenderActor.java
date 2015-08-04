package com.bluebirdaward.dangerball.render;
/*
 *  created by tuankhac 
 *  group losers
 *  update 31/7/2015
 * */
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bluebirdaward.dangerball.logic.GameLogic;
import com.bluebirdaward.dangerball.utils.Constants;

public class RenderActor extends Actor {

	protected GameLogic gameLogic;
	protected Rectangle screenRectangle;
	public RenderActor() { }

    public RenderActor(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        screenRectangle = new Rectangle();
    }
    
    protected float transformToScreen(float n) { return Constants.LOGIC_TO_RENDER * n; }
    
}

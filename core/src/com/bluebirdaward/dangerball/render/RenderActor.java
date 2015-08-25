package com.bluebirdaward.dangerball.render;
/*
 *  created by tuankhac 
 *  group losers
 *  update 31/7/2015
 * */
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.bluebirdaward.dangerball.logic.GameLogic;
import com.bluebirdaward.dangerball.utils.Constants;

public abstract class RenderActor  {

	protected GameLogic gameLogic;
	protected Rectangle screenRectangle;
	public RenderActor() { }

    public RenderActor(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        screenRectangle = new Rectangle();
    }
    
    protected float transformToScreen(float n) { return Constants.LOGIC_TO_RENDER * n; }
    
    public abstract void draw(Batch batch);
   
    public abstract void act();
    
}

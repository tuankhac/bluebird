package com.bluebirdaward.dynaball.render;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bluebirdaward.dynaball.logic.GameLogic;
import com.bluebirdaward.dynaball.utils.Constants;

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

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
    
//    @Override public void act(float delta) {
//        super.act(delta);
//        if (gameLogic.getBody() != null) {
//            updateRectangle();
//        } else {
//            // This means the world destroyed the body (enemy or runner went out of bounds)
//            remove();
//        }
//    }
    
//    private void updateRectangle() {
//        screenRectangle.x = transformToScreen(gameLogic.getPosition().x);
//        screenRectangle.y = transformToScreen(gameLogic.getPosition().y);
//        screenRectangle.width = transformToScreen(gameLogic.getWidth());
//        screenRectangle.height = transformToScreen(gameLogic.getHeight());
//    }

    protected float transformToScreen(float n) { return Constants.LOGIC_TO_RENDER * n; }
    
}

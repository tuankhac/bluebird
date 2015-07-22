package com.bluebirdaward.dynaball.render;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bluebirdaward.dynaball.stages.MainStage;
import com.bluebirdaward.dynaball.utils.Constants.GLOBAL_STATE;

public class GridLevel extends Actor {
	public boolean[] _displayGridLevel;
	private MainStage _mainStage;
	private TextureAtlas _buttonsAtlas;

	public TextButton[] buttons;
	public boolean [] allowActiveButton;
	private Level level;
 
	public GridLevel(MainStage _mainStage, Level level){
		this._mainStage = _mainStage;
		this.level = level;
		_displayGridLevel = new boolean[20];
		allowActiveButton = new boolean[20];
		for (int i = 0; i < _displayGridLevel.length; i++) {
			if (i != 0) {
				_displayGridLevel[i] = false;
				allowActiveButton[i] = false;
			} else 
			{
				_displayGridLevel[i] = true;
				allowActiveButton[i] = true;
			}
		}
		initFont();
		buttons = new TextButton[20];
		initButtons();
	}

	@Override public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
//		System.out.println("GridLevel STATE: "+ _mainStage.globalState);
		for (int i = 0; i < _displayGridLevel.length; i++) {
			buttons[i].draw(batch, parentAlpha);
		}
	}

	private void initButtons(){
		_buttonsAtlas = new TextureAtlas("images/buttons.pack");
		buttonSkin = new Skin();
		buttonSkin.addRegions(_buttonsAtlas);

		initGridLevel();
	}
	
	public void initGridLevel() {
		float y = 600;
		float x = 10;
		for (int i = 0; i < buttons.length; i++) {

			if (_displayGridLevel[i]) {
				buttons[i] = initButton("grid_level_item_passed", ""+(i+1));
				activeButton(buttons[i], "grid_level_item_passed", (i+1), x, y);
			} else {
				buttons[i] = initButton("grid_level_item_lock", ""+(i+1));
				activeButton(buttons[i], "grid_level_item_lock", (i+1), x, y);
			}
			x += 110;
			if (x > 370) {
				y -= 110;
				x = 10;
			}
		}
	}

	private Skin buttonSkin;
	private TextButton initButton(String name, String display){
		TextButtonStyle style = new TextButtonStyle();
		style.up = buttonSkin.getDrawable(name);
		style.down = buttonSkin.getDrawable(name);
		style.font = scoreFont;
		return new TextButton(display, style);
	}

	private void activeButton(TextButton button, String name, final int display, float x, float y){
		float btnWidth = (float)_buttonsAtlas.findRegion(name).getRegionWidth();
		float btnHeight = (float)_buttonsAtlas.findRegion(name).getRegionHeight();
		button.setSize(btnWidth, btnHeight);

		button.setPosition(x, y);
		button.setBounds(x, y, button.getWidth(), button.getHeight());
		button.addListener(new ClickListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					System.out.println(""+ display + " | GAMESTATE: " + _mainStage.globalState);
					level.level = display;
					_mainStage._worldLogic.initNewLevel();
					_mainStage.globalState = GLOBAL_STATE.PLAY;
				return true;
			}
		});
	}

	BitmapFont scoreFont;
	private void initFont(){
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/avenir_game.ttf"));
		scoreFont = new BitmapFont();
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 60;
		parameter.characters = "0123456789";

		scoreFont = generator.generateFont(parameter);
		generator.dispose();
		scoreFont.setColor(Color.BLUE);
	}
}


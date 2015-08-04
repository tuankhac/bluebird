package com.bluebirdaward.dangerball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class SaveScore {
	//get a preferences instance
		static Preferences prefs;
		
		public static void saveData(int target) {
			prefs = Gdx.app.getPreferences("My Preferences");
			//put some Integer
			prefs.putInteger("data", target);
			//persist preferences
			prefs.flush();
		}

		public static int getDisplayGridLevel(){
			prefs = Gdx.app.getPreferences("My Preferences");
			return prefs.getInteger("data");
		}
}

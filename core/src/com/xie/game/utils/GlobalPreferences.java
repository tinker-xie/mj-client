package com.xie.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * @Author xie
 * @Date 17/2/13 下午3:14.
 */
public class GlobalPreferences {

    private static Preferences preferences;

    public static Preferences getInstance() {
        if (null == preferences) {
            preferences = Gdx.app.getPreferences("My Preferences");
            return preferences;
        } else {
            return preferences;
        }
    }

    private GlobalPreferences() {
    }
}

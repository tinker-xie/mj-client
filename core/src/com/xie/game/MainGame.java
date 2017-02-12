package com.xie.game;

import com.badlogic.gdx.Game;

/**
 * @Author xie
 * @Date 17/2/12 下午4:12.
 */
public class MainGame extends Game {

    @Override
    public void create() {
        setScreen(new MainScreen(this));
    }
}

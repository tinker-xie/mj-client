
package com.xie.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public abstract class BaseScreen implements Screen {

	// 视口世界的宽高统使用 480 * 800, 并统一使用伸展视口（StretchViewport）
	public static final float WORLD_WIDTH = 480;
	public static final float WORLD_HEIGHT = 800;
	protected Game game;

	public BaseScreen(Game game) {
		this.game = game;
	}


}

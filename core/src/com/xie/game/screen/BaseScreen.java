
package com.xie.game.screen;

import com.badlogic.gdx.Screen;
import com.xie.game.Application;
import com.xie.game.mina.msg.MinaMessage;

public abstract class BaseScreen implements Screen, MinaMessageProcessor {

	// 视口世界的宽高统使用 480 * 800, 并统一使用伸展视口（StretchViewport）
    public static final float WORLD_WIDTH = 800;
    public static final float WORLD_HEIGHT = 480;
    protected Application application;

    public BaseScreen(Application application) {
        this.application = application;
    }

    @Override
    public boolean canHandle(MinaMessage.Type type) {
        return false;
    }

    @Override
    public boolean onCMD(int id, String data) {
        return false;
    }

    @Override
    public boolean onMSG(int id, String data) {
        return false;
    }

    @Override
    public boolean onINDICATION(int id, String data) {
        return false;
    }

    @Override
    public boolean onOTHER(int id, String data) {
        return false;
    }
}

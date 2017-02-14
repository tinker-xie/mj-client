package com.xie.game.screen;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.xie.game.PAI;
import com.xie.game.PTexture;
import com.xie.game.actor.PActor;

/**
 * @Author xie
 * @Date 17/2/12 下午4:13.
 */
public class MainScreen extends BaseScreen {

    private static final String TAG = MainScreen.class.getSimpleName();

    private SpriteBatch batch;
    private Stage stage;
    private PActor actor;

    public MainScreen(Game game) {
        super(game);
//        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        // 创建一个伸展视口, 为了方便查看效果, 世界的宽高设置为 256 * 512 （图片刚好在视口下半部分）
        Viewport stretchViewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT);

        // 使用指定的视口创建舞台, 舞台的宽高为视口世界的宽高
        stage = new Stage(stretchViewport);

        batch = new SpriteBatch();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f, 1, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        float w = PTexture.getInstance().getTextureByCode(PAI.B1).getWidth();
        float h = PTexture.getInstance().getTextureByCode(PAI.B1).getHeight();
        float scale = 2;
        for (int k = 0; k < 5; k++) {
            batch.draw(PTexture.getInstance().getTextureByCode(k + 9), 10 + k * w / scale, 10, w / scale, h / scale);
        }
        batch.end();

        stage.act();
        stage.draw();


//        actor.draw(batch, 1.0F);
    }

    private void testMoveToAction() {
        actor.setPosition(0, 0);
        MoveToAction action = Actions.moveTo(150, 300, 3.0F);
        actor.addAction(action);
    }

    /**
     * 2. 移动动作（相对）
     */
    private void testMoveByAction() {
        // 演员初始化位置设置显示到舞台中心
        actor.setPosition(
                actor.getStage().getWidth() / 2 - actor.getWidth() / 2,
                actor.getStage().getHeight() / 2 - actor.getHeight() / 2
        );
        MoveByAction action = Actions.moveBy(100, -200, 2.0F);
        actor.addAction(action);
    }


    @Override
    public void dispose() {
        batch.dispose();
        if (stage != null) {
            stage.dispose();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}

package com.xie.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.xie.game.actor.PActor;

/**
 * @Author xie
 * @Date 17/2/12 下午4:13.
 */
public class MainScreen extends AbstractScreen {

    private static final String TAG = MainScreen.class.getSimpleName();

    // 视口世界的宽高统使用 480 * 800, 并统一使用伸展视口（StretchViewport）
    public static final float WORLD_WIDTH = 480;
    public static final float WORLD_HEIGHT = 800;

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

        actor = new PActor(new TextureRegion(PTexture.getInstance().getTextureByCode(PAI.B4)));

        actor.setPosition(50, 100);     // 或者 setX(), setY() 分开设置

        // 设置演员 旋转和缩放支点 为演员的左下角
        actor.setOrigin(0, 0);

        // 设置演员缩放比, X 轴方向缩小到 0.5 倍, Y 轴方向保持不变
        actor.setScale(0.2F, 0.4F);


        stage.addActor(actor);

        testMoveByAction();
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
//        for (int k = 0; k < 13; k++) {
//            batch.draw(PTexture.getInstance().getTextureByCode(PAI.W1), 10 + k * 48, 10, 48, 64);
//        }
        batch.end();
        stage.act();
        stage.draw();



//        actor.draw(batch, 1.0F);
    }

    private void testMoveToAction() {
        // 设置演员初始化位置
        actor.setPosition(0, 0);

        // 获取一个 MoveTo 动作, 3 秒内移动到 (150, 300) 的位置
        MoveToAction action = Actions.moveTo(150, 300, 3.0F);

        // 将动作附加在演员身上, 执行动作
        actor.addAction(action);

        /*
         * 动作执行原理（查看 Actor 和相应 Action 的源码）:
         *
         * 实际上动作添加到演员身上的后, 动作被存放到一个数组中, 然后在更新演员逻辑的 actor.act()方法中遍历存放动作的数组,
         * 对每一个动作根据时间步 delta 改变演员相应的状态属性值。然后在绘制演员的 actor.draw() 方法中绘制演员时使用新的
         * 状态属性值绘制, 和上一帧相比, 就显的演员被“动”起来了。
         */
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

        // 获取一个 MoveBy 动作
        // 2 秒内, 在演员在原位置基础上, 水平方向移动 100, 竖直方向移动 -200
        MoveByAction action = Actions.moveBy(100, -200, 2.0F);

        // 将动作附近在演员身上, 执行动作
        actor.addAction(action);
    }

    @Override
    public void dispose() {
        batch.dispose();
        if (stage != null) {
            stage.dispose();
        }
        super.dispose();
    }
}

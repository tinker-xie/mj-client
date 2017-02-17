package com.xie.game.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.xie.game.Application;
import com.xie.game.PTexture;
import com.xie.game.utils.AssetManager;

/**
 * @Author xie
 * @Date 17/2/12 下午4:13.
 */
public class MainScreen extends BaseScreen {

    private static final String TAG = MainScreen.class.getSimpleName();

    private SpriteBatch batch;
    private Stage stage;
    private Image image;

    private AssetManager assetManager = new AssetManager();


    public MainScreen(Application game) {
        super(game);

        Viewport stretchViewport = new FillViewport(WORLD_WIDTH, WORLD_HEIGHT);

        // 使用指定的视口创建舞台, 舞台的宽高为视口世界的宽高
        stage = new Stage(stretchViewport);


        stage.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("x==   " + x);
                return true;
            }
        });

        batch = new SpriteBatch();


    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f, 1, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        Texture texture_desk = new Texture(Gdx.files.internal("desk.png"));
        batch.draw(texture_desk, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);
        float scale = 20;
        float w = WORLD_WIDTH / scale;
        float h = w * 1.5f;
        float left = getCenter(WORLD_WIDTH, w * 11);
        for (int k = 0; k < 11; k++) {
            Texture texture = PTexture.getInstance().getTextureByCode(k + 9);
            batch.draw(texture, left + k * w, 64, w, h);
        }

        Texture texture = new Texture(Gdx.files.internal("left.png"));
        float scale2 = 20;
        float h2 = WORLD_HEIGHT / scale;
        float w2 = h2 / 1.5f;
        float bottom = getCenter(WORLD_HEIGHT, h2 * 11);
        for (int k = 0; k < 11; k++) {

            batch.draw(texture, 120, bottom + k * h2, w2, h2);
        }

        TextureRegion textureRegion = new TextureRegion(texture);
        textureRegion.flip(true, false);
        for (int k = 0; k < 11; k++) {

            batch.draw(textureRegion, 680, bottom + k * h2, w2, h2);
        }
        batch.end();

        stage.act();
        stage.draw();


//        actor.draw(batch, 1.0F);
    }

    private float getCenter(float center, float width) {
        return center / 2 - width / 2;
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

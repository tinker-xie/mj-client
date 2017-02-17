package com.xie.game.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.xie.game.Application;
import com.xie.game.PTexture;
import com.xie.game.mina.msg.BaseResponse;
import com.xie.game.mina.msg.GameFrame;
import com.xie.game.mina.msg.MinaMessage;
import com.xie.game.utils.AssetManager;
import com.xie.game.utils.NetManager;

/**
 * @Author xie
 * @Date 17/2/12 下午4:13.
 */
public class MainScreen extends BaseScreen {

    private static final String TAG = MainScreen.class.getSimpleName();

    private SpriteBatch batch;
    private Stage stage;
    private Image image;
    private Skin skin;
    private Array array;
    private Table table;
    private TextButton btn_throw;
    private TextButton btn_gang;
    private TextButton btn_peng;
    private GameFrame gameFrame;

    private AssetManager assetManager = new AssetManager();


    public MainScreen(Application application) {
        super(application);
        application.messageDispatch.getScreenList().add(this);

        Viewport stretchViewport = new FillViewport(WORLD_WIDTH, WORLD_HEIGHT);
        // 使用指定的视口创建舞台, 舞台的宽高为视口世界的宽高
        table = new Table();
        table.setFillParent(true);
        table.setPosition(100, 100);
        stage = new Stage(stretchViewport);
        stage.addActor(table);
        skin = new Skin(Gdx.files.internal("uiskin.json"));


        stage.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("x==   " + x);
                return true;
            }
        });

        batch = new SpriteBatch();
        btn_throw = new TextButton("throw", skin);
        btn_gang = new TextButton("gang", skin);
        btn_peng = new TextButton("peng", skin);
        table.add(btn_gang).width(40).height(15);
        table.add(btn_peng).width(40).height(15);
        table.add(btn_throw).width(40).height(15);
        table.row();
    }

    @Override
    public boolean canHandle(MinaMessage.Type type) {
        if (type.equals(MinaMessage.Type.COMMAND)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onCOMMAND(int id, String data) {
        BaseResponse response = application.json.fromJson(BaseResponse.class, data);
        switch (id) {
            case NetManager.CMD_BEGIN_CARDS:
                if (response.getCode() == BaseResponse.SUCCESS_CODE) {
                    System.out.println(response.getData());
                    array = application.json.fromJson(Array.class, response.getData());
                } else {
                    new Dialog("Error", skin, "dialog") {
                        protected void result(Object object) {
                        }
                    }.text("login in failed")
                            .button("Ok", true)
                            .key(Input.Keys.ENTER, true)
                            .key(Input.Keys.ESCAPE, false).show(stage);
                }
                break;

            case NetManager.CMD_CREATE_ROOM:
                if (response.getCode() == BaseResponse.SUCCESS_CODE) {

                    gameFrame = application.json.fromJson(GameFrame.class, response.getData());
                    System.out.println(gameFrame);
                } else {
                    new Dialog("Error", skin, "dialog") {
                        protected void result(Object object) {
                        }
                    }.text("login in failed")
                            .button("Ok", true)
                            .key(Input.Keys.ENTER, true)
                            .key(Input.Keys.ESCAPE, false).show(stage);
                }
                break;
        }
        return true;
    }

    @Override
    public void show() {

        Gdx.input.setInputProcessor(stage);
        MinaMessage.Message.Builder builder = MinaMessage.Message.newBuilder();
        builder.setType(MinaMessage.Type.REQUEST);
        builder.setId(NetManager.CMD_CREATE_ROOM);
        builder.setData(application.json.toJson(application.getUser()));
        application.myNioSocketConnector.send(builder.build());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f, 1, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        Texture texture_desk = new Texture(Gdx.files.internal("desk-w.png"));
        batch.draw(texture_desk, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);
        float scale = 40;
        float w = WORLD_WIDTH / scale;
        float h = w * 1.5f;
        float left = getCenter(WORLD_WIDTH, w * 11);
        if (null != array) {
            for (int k = 0; k < array.size; k++) {
                Texture texture = PTexture.getInstance().getTextureByCode(((Float) array.get(k)).intValue());
                batch.draw(texture, left + k * w, 88, w, h);
            }
        }


        Texture texture = new Texture(Gdx.files.internal("left.png"));
        float scale2 = 25;
        float h2 = WORLD_HEIGHT / scale2;
        float w2 = h2 / 1.5f;
        float bottom = getCenter(WORLD_HEIGHT, h2 * 11);
        for (int k = 0; k < 11; k++) {

            batch.draw(texture, 250, bottom + k * h2, w2, h2);
        }

        TextureRegion textureRegion = new TextureRegion(texture);
        textureRegion.flip(true, false);
        for (int k = 0; k < 11; k++) {

            batch.draw(textureRegion, 550, bottom + k * h2, w2, h2);
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

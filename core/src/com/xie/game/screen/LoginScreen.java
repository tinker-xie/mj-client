package com.xie.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.xie.game.utils.GlobalPreferences;

/**
 * @Author xie
 * @Date 17/2/13 下午3:06.
 */
public class LoginScreen extends BaseScreen {
    private static final String KEY_USERNAME = "KEY_USERNAME";
    private Camera camera;

    private Stage stage;

    public LoginScreen(final Game game) {
        super(game);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        final Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

        Label nameLabel = new Label("Name:", skin);
        final TextField nameText = new TextField("", skin);
        nameText.setText(GlobalPreferences.getInstance().getString(KEY_USERNAME));
        Label addressLabel = new Label("Address:", skin);
        TextField addressText = new TextField("", skin);

        Table table = new Table();
        table.setFillParent(true);
        table.center();
        stage.addActor(table);

        table.add(nameLabel);
        table.add(nameText).width(150);
        table.row().pad(10);
        table.add(addressLabel);
        table.add(addressText).width(150);
        table.row().pad(10);

        TextButton register = new TextButton("register", skin);
        register.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                if (nameText.getText().isEmpty()) {
                    new Dialog("Error", skin, "dialog") {
                        protected void result(Object object) {
                            System.out.println("Chosen: " + object);
                        }
                    }.text("insert a name! ")
                            .button("OK", true)
                            .key(Input.Keys.ANY_KEY, true)
                            .show(stage);
                } else {
                    GlobalPreferences.getInstance().putString(KEY_USERNAME, nameText.getText());
                    GlobalPreferences.getInstance().flush();
                    game.setScreen(new MainScreen(game));
                }

                return false;
            }
        });
        table.add(register).width(80).height(30);
        TextButton login = new TextButton("login", skin);
        login.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                new Dialog("Some Dialog", skin, "dialog") {
                    protected void result(Object object) {
                        System.out.println("Chosen: " + object);
                    }
                }.text("Are you enjoying this demo?").button("Yes", true).button("No", false).key(Input.Keys.ENTER, true)
                        .key(Input.Keys.ESCAPE, false).show(stage);

                return false;
            }
        });

        table.add(login).width(80).height(30);
        table.row().pad(10);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void show() {

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

    @Override
    public void dispose() {

    }
}
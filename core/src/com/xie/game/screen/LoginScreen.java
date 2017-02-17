package com.xie.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.xie.game.Application;
import com.xie.game.mina.bean.User;
import com.xie.game.mina.msg.BaseResponse;
import com.xie.game.mina.msg.MinaMessage;
import com.xie.game.utils.GlobalPreferences;
import com.xie.game.utils.NetManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author xie
 * @Date 17/2/13 下午3:06.
 */
public class LoginScreen extends BaseScreen {
    private static final String KEY_USERNAME = "KEY_USERNAME";
    private static final String KEY_PASSWORD = "KEY_PASSWORD";
    private final Logger LOGGER = LoggerFactory.getLogger(LoginScreen.class);
    private Camera camera;
    private Skin skin;

    private Stage stage;

    public LoginScreen(final Application application) {
        super(application);

        application.messageDispatch.getScreenList().add(this);

        stage = new Stage();

        skin = new Skin(Gdx.files.internal("uiskin.json"));

        Label nameLabel = new Label("User:", skin);
        final TextField usernameText = new TextField("", skin);
        usernameText.setText(GlobalPreferences.getInstance().getString(KEY_USERNAME));
        Label passwordLabel = new Label("Password:", skin);
        final TextField passwordText = new TextField("", skin);
        passwordText.setText(GlobalPreferences.getInstance().getString(KEY_PASSWORD));

        Table table = new Table();
        table.setFillParent(true);
        table.center();
        stage.addActor(table);

        table.add(nameLabel);
        table.add(usernameText).width(150);
        table.row().pad(10);
        table.add(passwordLabel);
        table.add(passwordText).width(150);
        table.row().pad(10);

        final TextButton register = new TextButton("register", skin);
        register.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (usernameText.getText().isEmpty() || passwordText.getText().isEmpty()) {
                    new Dialog("Error", skin, "dialog") {
                        protected void result(Object object) {
                            System.out.println("Chosen: " + object);
                        }
                    }.text("Username or password is null.")
                            .button("Yes", true)
                            .key(Input.Keys.ENTER, true)
                            .key(Input.Keys.ESCAPE, false).show(stage);
                    return false;
                }
                GlobalPreferences.getInstance().putString(KEY_USERNAME, usernameText.getText());
                GlobalPreferences.getInstance().putString(KEY_PASSWORD, passwordText.getText());
                GlobalPreferences.getInstance().flush();
                User user = new User();
                user.setName(usernameText.getText());
                user.setPassword(passwordText.getText());
                MinaMessage.Message.Builder builder = MinaMessage.Message.newBuilder();
                builder.setType(MinaMessage.Type.REQUEST);
                builder.setId(NetManager.MSG_USER_REGISTER);
                builder.setData(application.json.toJson(user));
                application.myNioSocketConnector.send(builder.build());
                return false;
            }
        });
        table.add(register).width(80).height(30);
        TextButton login = new TextButton("login", skin);
        login.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (usernameText.getText().isEmpty() || passwordText.getText().isEmpty()) {
                    new Dialog("Error", skin, "dialog") {
                        protected void result(Object object) {
                            System.out.println("Chosen: " + object);
                        }
                    }.text("Username or password is null.")
                            .button("Yes", true)
                            .key(Input.Keys.ENTER, true)
                            .key(Input.Keys.ESCAPE, false).show(stage);
                    return false;
                }
                GlobalPreferences.getInstance().putString(KEY_USERNAME, usernameText.getText());
                GlobalPreferences.getInstance().putString(KEY_PASSWORD, passwordText.getText());
                GlobalPreferences.getInstance().flush();
                User user = new User();
                user.setName(usernameText.getText());
                user.setPassword(passwordText.getText());
                MinaMessage.Message.Builder builder = MinaMessage.Message.newBuilder();
                builder.setType(MinaMessage.Type.REQUEST);
                builder.setId(NetManager.MSG_USER_LOGIN);
                builder.setData(application.json.toJson(user));
                application.setUser(user);
                application.myNioSocketConnector.send(builder.build());
                return false;
            }
        });

        table.add(login).width(80).height(30);
        table.row().pad(10);
    }

    @Override
    public boolean canHandle(MinaMessage.Type type) {
        if (type.equals(MinaMessage.Type.RESPONSE)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onMSG(int id, String data) {
        switch (id) {
            case NetManager.MSG_USER_REGISTER:
                BaseResponse response = application.json.fromJson(BaseResponse.class, data);
                if (response.getCode() == BaseResponse.SUCCESS_CODE) {
                    application.changeScreen(Application.SCREEN_MAIN);
                } else {
                    new Dialog("Error", skin, "dialog") {
                        protected void result(Object object) {
                        }
                    }.text(response.getMsg())
                            .button("Ok", true)
                            .key(Input.Keys.ENTER, true)
                            .key(Input.Keys.ESCAPE, false).show(stage);
                }
                break;
            case NetManager.MSG_USER_LOGIN:
                BaseResponse response_login = application.json.fromJson(BaseResponse.class, data);
                if (response_login.getCode() == BaseResponse.SUCCESS_CODE) {
                    application.changeScreen(Application.SCREEN_MAIN);
                } else {
                    new Dialog("Error", skin, "dialog") {
                        protected void result(Object object) {
                        }
                    }.text(response_login.getMsg())
                            .button("Ok", true)
                            .key(Input.Keys.ENTER, true)
                            .key(Input.Keys.ESCAPE, false).show(stage);
                }
                break;
        }
        return true;
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
        Gdx.input.setInputProcessor(stage);
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

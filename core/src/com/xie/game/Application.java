package com.xie.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.xie.game.mina.net.MessageDispatch;
import com.xie.game.mina.net.MyNioSocketConnector;
import com.xie.game.screen.Base3DScreen;
import com.xie.game.screen.CameraScreen;
import com.xie.game.screen.LoginScreen;
import com.xie.game.screen.MainScreen;
import com.xie.game.utils.LoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author xie
 * @Date 17/2/12 下午4:12.
 */
public class Application extends Game {

    public static final int SCREEN_LOGIN = 1;
    public static final int SCREEN_MAIN = 2;
    public static final int SCREEN_3D = 3;
    public MyNioSocketConnector myNioSocketConnector;
    public MessageDispatch messageDispatch;
    public Json json;
    private CameraScreen cameraScreen;
    private LoginScreen loginScreen;
    private MainScreen mainScreen;
    private Base3DScreen base3DScreen;
    private Logger logger = LoggerFactory.getLogger(Application.class);

    @Override
    public void create() {
        LoggerUtils.load();
        messageDispatch = new MessageDispatch();
        myNioSocketConnector = new MyNioSocketConnector(messageDispatch);
        cameraScreen = new CameraScreen(this);
        loginScreen = new LoginScreen(this);
        mainScreen = new MainScreen(this);
        base3DScreen = new Base3DScreen(this);
        setScreen(loginScreen);
        initJson();
    }

    public void changeScreen(int target) {
        switch (target) {
            case SCREEN_LOGIN:
                setScreen(loginScreen);
                break;
            case SCREEN_MAIN:
                setScreen(mainScreen);
                break;
            case SCREEN_3D:
                setScreen(base3DScreen);
                break;
            default:
                setScreen(loginScreen);
        }
    }

    private void initJson() {
        json = new Json();
        json.setTypeName(null);
        json.setUsePrototypes(false);
        json.setIgnoreUnknownFields(true);
        json.setOutputType(JsonWriter.OutputType.json);
    }

    @Override
    public void dispose() {
        if (null != myNioSocketConnector) {
            myNioSocketConnector.dispose();
        }
        super.dispose();
    }
}

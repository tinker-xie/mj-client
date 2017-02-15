package com.xie.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
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

    public CameraScreen cameraScreen;
    public LoginScreen loginScreen;
    public MainScreen mainScreen;
    public Base3DScreen base3DScreen;
    public LoginScreen minaScreen;
    public MyNioSocketConnector myNioSocketConnector;
    public Json json;
    private Logger logger = LoggerFactory.getLogger(Application.class);

    @Override
    public void create() {
        LoggerUtils.load();
        myNioSocketConnector = new MyNioSocketConnector();
        cameraScreen = new CameraScreen(this);
        loginScreen = new LoginScreen(this);
        mainScreen = new MainScreen(this);
        base3DScreen = new Base3DScreen(this);
        minaScreen = new LoginScreen(this);
        setScreen(minaScreen);

        initJson();

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

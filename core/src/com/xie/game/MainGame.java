package com.xie.game;

import com.badlogic.gdx.Game;
import com.xie.game.mina.net.MyNioSocketConnector;
import com.xie.game.screen.*;
import com.xie.game.utils.LoggerUtils;

/**
 * @Author xie
 * @Date 17/2/12 下午4:12.
 */
public class MainGame extends Game {

    public CameraScreen cameraScreen;
    public LoginScreen loginScreen;
    public MainScreen mainScreen;
    public Base3DScreen base3DScreen;
    public MinaScreen minaScreen;

    public MyNioSocketConnector myNioSocketConnector;

    @Override
    public void create() {
        LoggerUtils.load();
        myNioSocketConnector = new MyNioSocketConnector();
        cameraScreen = new CameraScreen(this);
        loginScreen = new LoginScreen(this);
        mainScreen = new MainScreen(this);
        base3DScreen = new Base3DScreen(this);
        minaScreen = new MinaScreen(this);
        setScreen(minaScreen);
    }

    @Override
    public void dispose() {
        if (null != myNioSocketConnector) {
            myNioSocketConnector.dispose();
        }
        super.dispose();
    }
}

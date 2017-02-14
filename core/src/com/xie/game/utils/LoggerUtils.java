package com.xie.game.utils;

import com.badlogic.gdx.Gdx;
import org.apache.log4j.PropertyConfigurator;

/**
 * @Author xie
 * @Date 17/2/14 下午12:39.
 */
public class LoggerUtils {

    /**
     * 装载log4j配置文件
     */
    public static void load() {
        String path = Gdx.files.internal("log4j.properties").path();

        // 间隔特定时间，检测文件是否修改，自动重新读取配置
        PropertyConfigurator.configureAndWatch(path,1000);
    }
}

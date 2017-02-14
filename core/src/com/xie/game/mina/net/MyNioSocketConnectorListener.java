package com.xie.game.mina.net;

import org.apache.mina.core.session.IoSession;

/**
 * @Author xie
 * @Date 17/2/14 下午12:46.
 */
public interface MyNioSocketConnectorListener {
    void onSuccess(String message);
    void onError(String message);
}

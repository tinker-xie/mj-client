package com.xie.game.mina.net;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author xie
 * @Date 17/2/14 下午4:30.
 */
public class MessageDispatch {

    private ConcurrentHashMap<Integer, MyNioSocketConnectorListener> messageHandlers;

    public MessageDispatch() {
        messageHandlers = new ConcurrentHashMap<Integer, MyNioSocketConnectorListener>();
    }

    public boolean add(Integer id, MyNioSocketConnectorListener listener) {
        if (messageHandlers.containsKey(id)) {
            return false;
        } else {
            messageHandlers.put(id, listener);
            return true;
        }
    }

    public boolean remove(Integer id) {
        if (messageHandlers.containsKey(id)) {
            messageHandlers.remove(id);
            return true;
        }
        return false;
    }

    public void dispatch(Integer id, String data) {
        if (messageHandlers.containsKey(id)) {
            messageHandlers.get(id).onSuccess(data);
        }else{
            System.out.println("message not dispatch!");
        }
    }
}

package com.xie.game.mina.net;

import com.xie.game.mina.msg.MinaMessage;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author xie
 * @Date 17/1/24 上午8:35.
 */
public class MyNioSocketConnectorHandler extends IoHandlerAdapter {
    private final Logger LOGGER = LoggerFactory.getLogger(MyNioSocketConnectorHandler.class);
    private MessageDispatch messageDispatch;

    public MyNioSocketConnectorHandler(MessageDispatch messageDispatch) {
        this.messageDispatch = messageDispatch;
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        session.closeOnFlush();
        LOGGER.warn("error" + cause.getMessage());
    }

    @Override
    public void messageReceived(IoSession session, Object message)
            throws Exception {
        MinaMessage.Message msg = (MinaMessage.Message) message;
        messageDispatch.dispatch(msg);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        // TODO Auto-generated method stub
        super.sessionClosed(session);
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status)
            throws Exception {
        // TODO Auto-generated method stub
        super.sessionIdle(session, status);
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
    }

}
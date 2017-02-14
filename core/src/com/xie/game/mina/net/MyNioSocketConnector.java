package com.xie.game.mina.net;

import com.xie.game.mina.coder.MinaProtobufDecoder;
import com.xie.game.mina.coder.MinaProtobufEncoder;
import com.xie.game.mina.msg.MinaMessage;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;

/**
 * @Author xie
 * @Date 17/2/14 下午12:45.
 */
public class MyNioSocketConnector {

    private NioSocketConnector connector;
    private IoSession ioSession;
    private MessageDispatch messageDispatch;


    public MyNioSocketConnector() {
        connector = new NioSocketConnector();
        messageDispatch = new MessageDispatch();
//        connector.getFilterChain().addLast("logger", new LoggingFilter());
        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MinaProtobufEncoder(), new MinaProtobufDecoder())); //设置编码过滤器
        connector.getFilterChain().addLast("keepAliveFilter", new KeepAliveFilter(new MyKeepAliveMessageFactory()));
        connector.setHandler(new MyNioSocketConnectorHandler(messageDispatch));//设置事件处理器
        ConnectFuture cf = connector.connect(new InetSocketAddress("127.0.0.1", 9001));//建立连接
        cf.awaitUninterruptibly();//等待连接创建完成
        ioSession = cf.getSession();

    }

    public void send(MinaMessage.Message message, MyNioSocketConnectorListener myNioSocketConnectorListener) {
        if (null != myNioSocketConnectorListener) {
            messageDispatch.add(message.getId(), myNioSocketConnectorListener);
        }
        ioSession.write(message);
    }


    public void close() {
        ioSession.closeOnFlush();
        ioSession.getCloseFuture().awaitUninterruptibly();//等待连接断开
    }

    public void dispose() {
        close();
        if (null != connector) {
            connector.dispose();
        }
    }
}

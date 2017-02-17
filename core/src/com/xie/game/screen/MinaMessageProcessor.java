package com.xie.game.screen;

import com.xie.game.mina.msg.MinaMessage;

/**
 * @Author xie
 * @Date 17/2/17 上午8:22.
 */
public interface MinaMessageProcessor {

    boolean canHandle(MinaMessage.Type type);

    boolean onCMD(int id, String data);

    boolean onMSG(int id, String data);

    boolean onINDICATION(int id, String data);

    boolean onOTHER(int id, String data);
}

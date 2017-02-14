package com.xie.game.mina.coder;

import com.xie.game.mina.msg.MinaMessage;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

/**
 * @Author xie
 * @Date 17/2/9 下午2:28.
 */
public class MinaProtobufDecoder extends CumulativeProtocolDecoder {

    @Override
    protected boolean doDecode(IoSession session, IoBuffer ioBuffer, ProtocolDecoderOutput out) throws Exception {

        // 如果没有接收完Header部分（4字节），直接返回false
        if (ioBuffer.remaining() < 4) {
            return false;
        } else {

            // 标记开始位置，如果一条消息没传输完成则返回到这个位置
            ioBuffer.mark();

            // 读取header部分，获取body长度
            int bodyLength = ioBuffer.getInt();

            // 如果body没有接收完整，直接返回false
            if (ioBuffer.remaining() < bodyLength) {
                ioBuffer.reset(); // IoBuffer position回到原来标记的地方
                return false;
            } else {
                byte[] bodyBytes = new byte[bodyLength];
                ioBuffer.get(bodyBytes); // 读取body部分
                MinaMessage.Message message = MinaMessage.Message.parseFrom(bodyBytes); // 将body中protobuf字节码转成Student对象
                out.write(message); // 解析出一条消息
                return true;
            }
        }
    }
}
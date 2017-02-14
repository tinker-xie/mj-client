package com.xie.game.mina.coder;

import com.xie.game.mina.msg.MinaMessage;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/**
 * @Author xie
 * @Date 17/2/9 下午2:29.
 */
public class MinaProtobufEncoder extends ProtocolEncoderAdapter {

    @Override
    public void encode(IoSession session, Object message,
                       ProtocolEncoderOutput out) throws Exception {

        MinaMessage.Message student = (MinaMessage.Message) message;
        byte[] bytes = student.toByteArray(); // Student对象转为protobuf字节码
        int length = bytes.length;

        IoBuffer buffer = IoBuffer.allocate(length + 4);
        buffer.putInt(length); // write header
        buffer.put(bytes); // write body
        buffer.flip();
        out.write(buffer);
    }
}

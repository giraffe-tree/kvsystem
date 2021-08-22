package me.giraffetree.kvsystem.network.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import lombok.extern.slf4j.Slf4j;
import me.giraffetree.kvsystem.common.Operation;
import me.giraffetree.kvsystem.common.OperationType;
import me.giraffetree.kvsystem.common.RequestMessage;
import me.giraffetree.kvsystem.common.constant.ServerAttr;

import java.util.List;

/**
 * @author GiraffeTree
 * @date 2021/8/8
 */
@Slf4j
public class MessageDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        RequestMessage requestMessage = new RequestMessage();
        OperationType opType = requestMessage.decode(msg);
        ctx.channel().attr(ServerAttr.OPERATION_TYPE.getKey()).set(opType);
        out.add(requestMessage);
    }
}

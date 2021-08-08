package me.giraffetree.kvsystem.network.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import lombok.extern.slf4j.Slf4j;
import me.giraffetree.kvsystem.common.RequestMessage;

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
        requestMessage.decode(msg);
        log.info("decode to request msg:{}", requestMessage);
        out.add(requestMessage);
    }
}

package me.giraffetree.kvsystem.client.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import lombok.extern.slf4j.Slf4j;
import me.giraffetree.kvsystem.common.RequestMessage;

import java.util.List;

/**
 * @author GiraffeTree
 * @date 2021/8/8
 */
@Slf4j
public class RequestMessageEncoder extends MessageToMessageEncoder<RequestMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, RequestMessage msg, List<Object> out) throws Exception {
        ByteBuf buffer = ctx.alloc().buffer();
        msg.encode(buffer);
        out.add(buffer);
    }
}

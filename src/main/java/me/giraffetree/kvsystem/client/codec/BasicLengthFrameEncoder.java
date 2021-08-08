package me.giraffetree.kvsystem.client.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldPrepender;

import java.util.List;

/**
 * @author GiraffeTree
 * @date 2021/8/7
 */
public class BasicLengthFrameEncoder extends LengthFieldPrepender {
    public BasicLengthFrameEncoder() {
        super(2);
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        super.encode(ctx, msg, out);
    }
}

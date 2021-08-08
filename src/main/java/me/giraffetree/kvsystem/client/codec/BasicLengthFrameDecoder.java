package me.giraffetree.kvsystem.client.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author GiraffeTree
 * @date 2021/8/7
 */
public class BasicLengthFrameDecoder extends LengthFieldBasedFrameDecoder {
    public BasicLengthFrameDecoder() {
        super(Integer.MAX_VALUE, 0, 2, 0, 2);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }
}

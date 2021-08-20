package me.giraffetree.kvsystem.network.handler;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Attribute;
import lombok.extern.slf4j.Slf4j;
import me.giraffetree.kvsystem.common.constant.ServerAttr;
import org.apache.commons.lang3.time.StopWatch;

/**
 * @author GiraffeTree
 * @date 2021/8/14
 */
@Slf4j
public class MetricHandler extends ChannelDuplexHandler {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        long startTimestamp = System.currentTimeMillis();
        Attribute<Object> attr = ctx.channel()
                .attr(ServerAttr.START_TIMESTAMP.getKey());
        attr.set(startTimestamp);

        ctx.fireChannelRead(msg);
    }

    @Override
    public void flush(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}

package me.giraffetree.kvsystem.network.handler;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Attribute;
import lombok.extern.slf4j.Slf4j;
import me.giraffetree.kvsystem.common.RequestMessage;
import me.giraffetree.kvsystem.common.ResponseMessage;
import me.giraffetree.kvsystem.common.constant.ServerAttr;

/**
 * @author GiraffeTree
 * @date 2021/8/14
 */
@Slf4j
public class MetricHandler extends ChannelDuplexHandler {

    private long startMills;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        long startTimestamp = System.currentTimeMillis();
        startMills = startTimestamp;
        Attribute<Object> attr = ctx.channel()
                .attr(ServerAttr.START_TIMESTAMP.getKey());
        attr.set(startTimestamp);

        ctx.fireChannelRead(msg);
    }

    @Override
    public void flush(ChannelHandlerContext ctx) throws Exception {
        super.flush(ctx);
        RequestMessage requestMessage = (RequestMessage) ctx.channel().attr(ServerAttr.REQUEST_CONTENT.getKey()).get();
        ResponseMessage responseMessage = (ResponseMessage) ctx.channel().attr(ServerAttr.RESPONSE_CONTENT.getKey()).get();

        log.info("type:{} process time:{}ms request:{} response:{}",
                requestMessage.getClass().getSimpleName(), System.currentTimeMillis() - startMills,
                requestMessage, responseMessage);
    }
}

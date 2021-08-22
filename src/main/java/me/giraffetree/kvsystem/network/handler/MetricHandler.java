package me.giraffetree.kvsystem.network.handler;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Attribute;
import lombok.extern.slf4j.Slf4j;
import me.giraffetree.kvsystem.common.Operation;
import me.giraffetree.kvsystem.common.OperationType;
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
        OperationType operationType = (OperationType) ctx.channel().attr(ServerAttr.OPERATION_TYPE.getKey()).get();

        Class<? extends Operation> requestClass = operationType.getRequest();
        Object request = ctx.channel().attr(ServerAttr.REQUEST_CONTENT.getKey()).get();
        Object response = ctx.channel().attr(ServerAttr.RESPONSE_CONTENT.getKey()).get();

        log.info("type:{} process time:{}ms request:{} response:{}",
                requestClass.getSimpleName(), System.currentTimeMillis() - startMills,
                request, response);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
    }
}

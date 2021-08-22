package me.giraffetree.kvsystem.network.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;
import me.giraffetree.kvsystem.common.Operation;
import me.giraffetree.kvsystem.common.RequestMessage;
import me.giraffetree.kvsystem.common.ResponseMessage;
import me.giraffetree.kvsystem.common.Result;
import me.giraffetree.kvsystem.common.constant.ServerAttr;

/**
 * @author GiraffeTree
 * @date 2021/8/8
 */
@Slf4j
public class RequestHandler extends SimpleChannelInboundHandler<RequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RequestMessage request) throws Exception {
        Operation operation = request.getMessageBody();
        Result result = operation.execute();

        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessageHeader(request.getMessageHeader());
        responseMessage.setMessageBody(result);

        // 设置请求/响应内容
        ctx.channel().attr(ServerAttr.REQUEST_CONTENT.getKey()).set(request);
        ctx.channel().attr(ServerAttr.RESPONSE_CONTENT.getKey()).set(responseMessage);

        ctx.writeAndFlush(responseMessage);
    }

}

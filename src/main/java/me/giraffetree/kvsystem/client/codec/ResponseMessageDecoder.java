package me.giraffetree.kvsystem.client.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import lombok.extern.slf4j.Slf4j;
import me.giraffetree.kvsystem.common.ResponseMessage;

import java.util.List;

/**
 * @author GiraffeTree
 * @date 2021/8/8
 */
@Slf4j
public class ResponseMessageDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.decode(buf);
        out.add(responseMessage);
        log.info("response get... {}", responseMessage);
    }
}

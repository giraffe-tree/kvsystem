package me.giraffetree.kvsystem.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import me.giraffetree.kvsystem.client.codec.BasicLengthFrameDecoder;
import me.giraffetree.kvsystem.client.codec.BasicLengthFrameEncoder;
import me.giraffetree.kvsystem.client.codec.RequestMessageEncoder;
import me.giraffetree.kvsystem.client.codec.ResponseMessageDecoder;
import me.giraffetree.kvsystem.common.OperationType;
import me.giraffetree.kvsystem.common.RequestMessage;
import me.giraffetree.kvsystem.common.request.HelloRequest;

import java.util.concurrent.ExecutionException;

/**
 * @author GiraffeTree
 * @date 2021/8/8
 */
public class Client {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class);

        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            bootstrap.group(group);

            bootstrap.handler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                    pipeline.addLast(new BasicLengthFrameDecoder());
                    pipeline.addLast(new BasicLengthFrameEncoder());

                    pipeline.addLast(new ResponseMessageDecoder());
                    pipeline.addLast(new RequestMessageEncoder());

                    pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                }
            });

            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 9000);
            channelFuture.sync();
            int count = 1;
            for (int i = 0; i < count; i++) {
                RequestMessage requestMessage = new RequestMessage(
                        OperationType.HELLO, new HelloRequest("Tony")
                );
                channelFuture.channel().writeAndFlush(requestMessage);
            }

            channelFuture.channel().closeFuture().sync();

        } finally {
            group.shutdownGracefully();
        }
    }

}

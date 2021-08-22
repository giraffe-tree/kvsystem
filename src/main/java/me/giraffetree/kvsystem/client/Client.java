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

/**
 * @author GiraffeTree
 * @date 2021/8/8
 */
public class Client {
    private NioEventLoopGroup group;
    private ChannelFuture channelFuture;

    public static void main(String[] args) {
        Client client = new Client();
        try {
            client.build(9000);
            RequestMessage requestMessage = new RequestMessage(
                    OperationType.HELLO, new HelloRequest("Giraffe")
            );
            client.call(requestMessage);
            client.call(requestMessage);
            client.call(requestMessage);

        } finally {
            client.close();
        }

    }

    public void build(int port) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class);

        group = new NioEventLoopGroup();

        bootstrap.group(group);

        bootstrap.handler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new BasicLengthFrameDecoder());
                pipeline.addLast(new BasicLengthFrameEncoder());

                pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                pipeline.addLast(new ResponseMessageDecoder());
                pipeline.addLast(new RequestMessageEncoder());
            }

        });

        channelFuture = bootstrap.connect("127.0.0.1", port);
        try {
            channelFuture.sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void call(RequestMessage requestMessage) {
        channelFuture.channel().writeAndFlush(requestMessage);
        try {
            channelFuture.sync().await();
            channelFuture.channel().close();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        group.shutdownGracefully();
    }


}

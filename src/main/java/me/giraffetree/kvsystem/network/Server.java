package me.giraffetree.kvsystem.network;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import me.giraffetree.kvsystem.network.codec.BasicLengthFrameDecoder;
import me.giraffetree.kvsystem.network.codec.BasicLengthFrameEncoder;
import me.giraffetree.kvsystem.network.codec.MessageDecoder;
import me.giraffetree.kvsystem.network.codec.MessageEncoder;
import me.giraffetree.kvsystem.network.handler.RequestHandler;

import java.util.concurrent.ExecutionException;

public class Server {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.channel(NioServerSocketChannel.class);

        NioEventLoopGroup group = new NioEventLoopGroup(4);
        try {
            serverBootstrap.group(group);

            serverBootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();

                    pipeline.addLast(new BasicLengthFrameDecoder());
                    pipeline.addLast(new BasicLengthFrameEncoder());

                    pipeline.addLast(new MessageDecoder());
                    pipeline.addLast(new MessageEncoder());

                    // 打印 request 和 response
                    pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                    pipeline.addLast(new RequestHandler());
                }
            });

            ChannelFuture channelFuture = serverBootstrap.bind(9000).sync();

            channelFuture.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }

    }

}

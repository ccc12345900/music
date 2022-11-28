package org.example.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TestServer {
    public static void main(String[] args) throws Exception {
        NioEventLoopGroup BossGroup = new NioEventLoopGroup();
        NioEventLoopGroup WorkGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(BossGroup,WorkGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new TestServerInitializer());

            ChannelFuture channelFuture = serverBootstrap.bind(8080).sync();

            channelFuture.channel().closeFuture().sync();
        }finally {
            BossGroup.shutdownGracefully();
            WorkGroup.shutdownGracefully();
        }
    }
}

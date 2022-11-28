package org.example.netty.groupchat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class GroupChatServer {
    private int port;

    public GroupChatServer(int port) {
        this.port = port;
    }

    //编写run方法，处理客户端请求
    public void run() throws Exception{
        //创建两个线程组
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        try{
        ServerBootstrap b = new ServerBootstrap();

        b.group(bossGroup,workGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,128)
                .childOption(ChannelOption.SO_KEEPALIVE,true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        //获取到pipline
                        ChannelPipeline pipeline = ch.pipeline();
                        //向pipline加入解码器
                        pipeline.addLast("decoder",new StringDecoder());
                        //向pipline加入编码器
                        pipeline.addLast("encoder",new StringEncoder());
                        //加入自己的业务
                        pipeline.addLast(new GroupChatServerHandler());
                    }
                });

        System.out.println("netty 服务器启动");
        ChannelFuture channelFuture = b.bind(port).sync();

        //监听关闭
        channelFuture.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws Exception{
        new GroupChatServer(7000).run();
    }
}

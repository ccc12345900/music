package org.example.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.example.netty.groupchat.GroupChatServerHandler;

public class MyServer {
    public static void main(String[] args) throws InterruptedException {
        //创建两个线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap b = new ServerBootstrap();

            b.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //获取到pipline
                            ChannelPipeline pipeline = ch.pipeline();
                            //因为是基于http协议，使用http的编码和解码
                            pipeline.addLast(new HttpServerCodec());
                                    //是以块方式写，添加ChunkedWriteHandler处理器
                              pipeline.addLast(new ChunkedWriteHandler());
                              /*
                              说明
                              1、http数据在传输过程中是分段，httpObjectAggregator可以将多个段聚合
                              2、这就是为什么，当浏览器发送大量数据时，就会发出多次http请求
                               */
                            pipeline.addLast(new HttpObjectAggregator(8192));
                            /*
                            说明
                            1.对应websocket，它的数据是以帧形式传递
                            2、可以看到WebSocketFrame下面有六个子类
                            3、浏览器请求时 ws://localhost:7000/hello表示请求的uri
                            4、WebSocketServerProtocolHandler核心功能是将http协议升级为ws协议，保持长连接
                             */
                            pipeline.addLast(new WebSocketServerProtocolHandler("/hello"));

                            //自定义的handler，处理业务逻辑
                            pipeline.addLast(new MyTextWebSocketFrameHandler());
                        }
                    });

            System.out.println("netty 服务器启动");
            ChannelFuture channelFuture = b.bind(7000).sync();

            //监听关闭
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}

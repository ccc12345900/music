package org.example.netty.codec;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //比如我们这里有一个非常耗时的业务-》异步执行-》提交该channel对应的
        //NIOEventLoop的 taskQueue中

        //解决方案1 用户程序自定义的普通任务
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10 * 1000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端(>^ω^<)喵2",CharsetUtil.UTF_8));
                } catch (InterruptedException e) {
                    System.out.println("发生异常");
                }
            }
        });

        //用户自定义定时任务 -》 该任务是提交到scheduleTaskQueue中
        ctx.channel().eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10 * 1000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端(>^ω^<)喵2",CharsetUtil.UTF_8));
                } catch (InterruptedException e) {
                    System.out.println("发生异常");
                }
            }
        },5, TimeUnit.SECONDS);

//        System.out.println("server ctx = "+ctx);
//        //将msg转换成ByteBuf
//        //ByteBuf是Netty提供的，不是NIO的ByteBuffer
//        ByteBuf buf = (ByteBuf) msg;
//        System.out.println("客户端发送的消息是：" + buf.toString(CharsetUtil.UTF_8));
//        System.out.println("客户端地址是："+ctx.channel().remoteAddress());
    }

    //数据读取完毕
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将数据写入缓冲，并刷新
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端~",CharsetUtil.UTF_8));
    }

    //处理异常

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}

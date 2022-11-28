package org.example.nio;

import io.netty.channel.ServerChannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    public static void main(String[] args) throws IOException {
        //创建ServerSocketChannel -> ServerSocker
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //得到一个selector对象
        Selector selector = Selector.open();

        //绑定一个端口，在服务器端监听
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //把serverSocketChannel注册到selector 关心事件为 OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("注册后的selectionkey 数量=" + selector.keys().size());

        //循环等待客户端连接
        while(true){

            //在这里我们等待1秒，如果没有事件发生，返回
            if(selector.select(1000) == 0){//没有事件发生
                System.out.println("服务器等待了1秒，无连接");
                continue;
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println("selectionKeys 数量 = " + selectionKeys.size());

            //使用迭代器遍历selectionKeys
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();

            while(keyIterator.hasNext()){
                //获取到selectionKey
                SelectionKey key = keyIterator.next();
                //根据key，对应的通道发生的事件做相应的处理
                if(key.isAcceptable()){//如果是 OP_ACCEPT, 有新的客户端连接
                    //该该客户端生成一个 SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("客户端连接成功 生成了一个 socketChannel " + socketChannel.hashCode());
                    //将  SocketChannel 设置为非阻塞
                    socketChannel.configureBlocking(false);
                    //将socketChannel 注册到selector, 关注事件为 OP_READ， 同时给socketChannel
                    //关联一个Buffer
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));

                    System.out.println("客户端连接后 ，注册的selectionkey 数量=" + selector.keys().size()); //2,3,4..
                }
                if(key.isReadable()) {  //发生 OP_READ

                    //通过key 反向获取到对应channel
                    SocketChannel channel = (SocketChannel)key.channel();

                    //获取到该channel关联的buffer
                    ByteBuffer buffer = (ByteBuffer)key.attachment();
                    channel.read(buffer);
                    System.out.println("form 客户端 " + new String(buffer.array()));
                }

                //手动从集合中移动当前的selectionKey, 防止重复操作
                keyIterator.remove();

            }
        }
    }
}

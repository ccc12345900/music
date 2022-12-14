package org.example.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer {
    public static void main(String[] args) throws IOException {

        //线程池机制

        //1、创建一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //2、如果有客户端连接了，就创建一个线程与之连接
        final ServerSocket serverSocket = new ServerSocket(6666);

        System.out.println("服务器启动了");

        while(true){
            System.out.println("等待连接");
            //监听，等待客户端连接
            final Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");

            //就创建一个线程与之通信(单独写一个方法)
            executorService.execute(new Runnable() {
                public void run() {
                    handler(socket);
                }
            });
        }
    }

    //编写一个handler方法，与客户端通信
    public static void handler(Socket socket){
        try {
            System.out.println("线程信息 id:"+ Thread.currentThread().getId() + "名字："+Thread.currentThread().getName());
            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();

            //循环读取
            while(true){
                System.out.println("线程信息 id:"+ Thread.currentThread().getId() + "名字："+Thread.currentThread().getName());
                System.out.println("read....");
                int read = inputStream.read(bytes);
                if(read != -1)
                {
                    System.out.println(new String(bytes,0,read));
                }else{
                    break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("关闭和client的连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

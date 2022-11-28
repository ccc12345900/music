package org.example.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel01 {
    public static void main(String[] args) throws IOException {

        String str = "hello";
        //创建一个输出流
        FileOutputStream fileOutputStream = new FileOutputStream("E:\\file01.txt");

        //通过fileoutputStream获取对应的FileChannel
        //这个fileChannel类型真实类型是fileChannelImpl
        FileChannel fileChannel = fileOutputStream.getChannel();

        //创建一个缓冲区ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //将str放入byteBuffer
        byteBuffer.put(str.getBytes());

        //对byteBuffer进行flip
        byteBuffer.flip();

        //将byteBuffer写入到fileChannel中
        fileChannel.write(byteBuffer);
        fileOutputStream.close();

    }
}

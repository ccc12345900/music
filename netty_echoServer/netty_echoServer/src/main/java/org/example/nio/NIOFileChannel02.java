package org.example.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel02 {
    public static void main(String[] args) throws IOException {
        //创建文件输入流
        File file = new File("e:\\file01.txt");
        FileInputStream fileInputStream = new FileInputStream(file);

        //获得Channel
        FileChannel channel = fileInputStream.getChannel();

        //获取byteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());

        //将通道中的数据读入到byteBuffer
        channel.read(byteBuffer);
        System.out.println(new String(byteBuffer.array()));

        fileInputStream.close();
    }
}

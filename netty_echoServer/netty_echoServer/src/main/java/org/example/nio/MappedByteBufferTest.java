package org.example.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * MappedByteBuffer 文件可以直接在内存修改，不用系统在拷贝一份
 */
public class MappedByteBufferTest {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("E:\\java学习和机器学习\\netty\\netty_echoServer\\netty_echoServer\\1.txt", "rw");
        //获取对应通道
        FileChannel channel = randomAccessFile.getChannel();
        /**
         *第一个参数 FileChannel.MapMode.READ_WRITE读写模式
         * 第二个参数 :可直接修改的起始位置
         * 第三个参数 :映射的文件内容的大小为5个字节
         */
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        map.put(0,(byte) 'H');
        map.put(3,(byte) '9');

        randomAccessFile.close();
        System.out.println("修改成功");
    }
}

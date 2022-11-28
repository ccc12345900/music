package org.example.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class NIOFileChannel04 {
    public static void main(String[] args) throws IOException {
        //创建相关流
        FileInputStream fileInputStream = new FileInputStream("e:\\v2-57ea11e19b2216124d7610c477cc11a0_b.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream("e:\\1.jpg");

        //创建通道
        FileChannel sourceCh = fileInputStream.getChannel();
        FileChannel destCh = fileOutputStream.getChannel();

        //使用transferForm完成拷贝
        destCh.transferFrom(sourceCh,0,sourceCh.size());
        //关闭相关通道和流
        sourceCh.close();
        destCh.close();
        fileOutputStream.close();
        fileInputStream.close();
    }
}

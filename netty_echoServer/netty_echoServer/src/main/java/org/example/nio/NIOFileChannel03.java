package org.example.nio;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel03 {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("E:\\java学习和机器学习\\netty\\netty_echoServer\\netty_echoServer\\src\\main\\java\\org\\example\\nio\\1.txt");
        FileChannel fileChannel01 = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("2.txt");
        FileChannel fileChannel02 = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        while(true) {
            //byteBuffer标志位清零
            byteBuffer.clear();
            int read = fileChannel01.read(byteBuffer);
            if(read == -1)break;

            //反转byteBuffer状态
            byteBuffer.flip();
            fileChannel02.write(byteBuffer);
        }

        fileInputStream.close();
        fileOutputStream.close();
    }
}

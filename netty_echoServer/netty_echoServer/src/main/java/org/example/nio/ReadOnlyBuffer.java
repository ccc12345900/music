package org.example.nio;

import java.nio.ByteBuffer;

public class ReadOnlyBuffer {
    public static void main(String[] args) {
        //创建一个buffer
        ByteBuffer buffer = ByteBuffer.allocate(64);

        for(int i = 0;i < 64;i++){
            buffer.put((byte) i);
        }

        //反转状态
        buffer.flip();

        //得到一个只读的buffer
        ByteBuffer byteBuffer = buffer.asReadOnlyBuffer();

        //读取
        while(byteBuffer.hasRemaining()){
            System.out.println(byteBuffer.get());
        }

        //如果放置就会报错ReadOnlyBufferException
        byteBuffer.put((byte) 100);
    }
}

package org.example.nio;

import java.nio.ByteBuffer;

public class NIOByteBufferPutGet {
    public static void main(String[] args) {
        //创建一个Buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);

        //类型化方式放入数据
        byteBuffer.putInt(100);
        byteBuffer.putLong(9);
        byteBuffer.putChar('z');
        byteBuffer.putShort((short) 4);

        //反转状态
        byteBuffer.flip();

        System.out.println();

        System.out.println(byteBuffer.getShort());
        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getLong());
        System.out.println(byteBuffer.getChar());
    }
}

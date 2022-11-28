package org.example.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class NettyByteBuf01 {
    public static void main(String[] args) {
        //创建一个ByteBuf
        //说明
        //1、创建对象，该对象包含一个数组arr，是一个byte【10】
        //2、在netty的buffer中，不需要使用flip进行反转
        //底层维护了readerindex和writerIndex
        //3、通过readIndex和writerIndex和capacity，将buffer分为三个区域
        //0-----readerindex 已经读取的区域
        //readerindex -- writerIndex ，可读的区域
        //writerIndex --- capacity 可写的区域
        ByteBuf buffer = Unpooled.buffer(10);

        for(int i = 0;i < 10;i++)
        {
            buffer.writeByte(i);
        }

        System.out.println("capacity" + buffer.capacity());

        //输出
//        for(int i = 0;i < 10;i++)
//        {
//            System.out.println(buffer.getByte(i));
//        }
        for(int i = 0;i < 10;i++)
        {
            System.out.println(buffer.readByte());
        }
    }
}

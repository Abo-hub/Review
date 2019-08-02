package com.sun.nio;


import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * @program: Review
 * @author: SunBo
 * @create: 2019-07-26 11:04
 **/

public class TestBuffer {

    @Test
    public void test1() {
        //1.分配一个指定大小的缓冲区
        System.out.println("-----------allocate()-------------------");
        ByteBuffer buf = ByteBuffer.allocate(1024);
        System.out.println("position: " + buf.position());
        System.out.println("limit: " + buf.limit());
        System.out.println("capacity: " + buf.capacity());

        //2.利用put()存入数据到缓冲区中
        String str = "abced";
        buf.put(str.getBytes());
        System.out.println("--------------put()---------------------");
        System.out.println("position: " + buf.position());
        System.out.println("limit: " + buf.limit());
        System.out.println("capacity: " + buf.capacity());

        //3.切换读取数据模式
        buf.flip();
        System.out.println("--------------flip()---------------------");
        System.out.println("position: " + buf.position());
        System.out.println("limit: " + buf.limit());
        System.out.println("capacity: " + buf.capacity());

        //4. 利用get()读取缓冲区的数据
        byte[] dst = new byte[buf.limit()];
        buf.get(dst);
        System.out.println(new String(dst, 0, dst.length));

        System.out.println("--------------get()---------------------");
        System.out.println("position: " + buf.position());
        System.out.println("limit: " + buf.limit());
        System.out.println("capacity: " + buf.capacity());

        //5. rewind() :重复读数据
        buf.rewind();
        System.out.println("--------------rewind()---------------------");
        System.out.println("position: " + buf.position());
        System.out.println("limit: " + buf.limit());
        System.out.println("capacity: " + buf.capacity());

        //6. clear(): 清空缓冲区,但是缓冲区中的数据依然存在，但是处在“被遗忘”状态
        buf.clear();
        System.out.println("--------------clear ()---------------------");
        System.out.println("position: " + buf.position());
        System.out.println("limit: " + buf.limit());
        System.out.println("capacity: " + buf.capacity());
    }

    @Test
    public void testMark() {
        String str = "abcde";
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.put(str.getBytes());
        buf.flip();
        byte[] dst = new byte[buf.limit()];
        buf.get(dst, 0, 2);
        System.out.println(new String(dst, 0, 2));
        System.out.println("postion: " + buf.position());

        //标记
        buf.mark();

        buf.get(dst, 2, 2);
        System.out.println(new String(dst, 2, 2));
        System.out.println("postion: " + buf.position());

        //恢复到mark的位置
        buf.reset();
        System.out.println("postion: " + buf.position());

        //判断缓冲区中是否还有剩余数据
        if (buf.hasRemaining()){
            //如果有获取缓冲区可操作数量
            System.out.println(buf.remaining());
        }
    }

    @Test
    public void test2(){
        ByteBuffer buf = ByteBuffer.allocateDirect(1024);
        ByteBuffer buf1 = ByteBuffer.allocate(1024);
    }
}

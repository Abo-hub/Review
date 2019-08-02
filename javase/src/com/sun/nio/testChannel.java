package com.sun.nio;

import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 * @program: Review
 * @author: SunBo
 * @create: 2019-07-27 17:28
 **/

/**
 * 利用通道完成文件的复制
 */
public class testChannel {
    @Test
    public void testChannel(){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            fis = new FileInputStream("hello.txt");
            fos = new FileOutputStream("hello2.txt");

            //1. 获取通道
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();

            //2.分配指定大小的缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);

            //3. 将通道中的数存入缓冲区中
            while ((inChannel.read(buf)) != -1) {
                //切换读取数据的模式
                buf.flip();
                //4 将缓冲区中的数据写入通道
                outChannel.write(buf);
                //清空缓冲区
                buf.clear();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outChannel != null){
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inChannel != null){
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }





        }

    }

    @Test
    public void  testScatter() throws IOException {
        RandomAccessFile raf1 = new RandomAccessFile("hello.txt", "rw");

        //1. 获取通道
        FileChannel channel1 = raf1.getChannel();

        //2. 分配指定大小的缓冲区
        ByteBuffer buf1 = ByteBuffer.allocate(100);
        ByteBuffer buf2 = ByteBuffer.allocate(1022);

        //3. 分散读取
        ByteBuffer[] bufs = {buf1,buf2};
        channel1.read(bufs);

        for (ByteBuffer byteBuffer : bufs) {
            byteBuffer.flip();
        }

        System.out.println(new String(bufs[0].array(),0,bufs[0].limit()));
        System.out.println("--------------------");
        System.out.println(new String(bufs[1].array(),0,bufs[0].limit()));

        //聚集写入
        RandomAccessFile raf2 = new RandomAccessFile("hello2.txt", "rw");
        FileChannel channel2 = raf2.getChannel();

        channel2.write(bufs);

    }

    @Test
    public void test2(){
        //字符集
        SortedMap<String, Charset> map = Charset.availableCharsets();
        Set<Map.Entry<String, Charset>> set = map.entrySet();

        for (Map.Entry<String, Charset> entry : set) {
            System.out.println(entry.getKey()+"="+entry.getValue());
        }
    }

    @Test
    public void test3() throws IOException {
        Charset cs1 = Charset.forName("GBK");

        //获取编码器
        CharsetEncoder ce = cs1.newEncoder();

        //获取解码器
        CharsetDecoder cd = cs1.newDecoder();


        CharBuffer cbuf = CharBuffer.allocate(1024);
        cbuf.put("你好");
        cbuf.flip();

        //编码
        ByteBuffer bbuf = ce.encode(cbuf);

        for (int i = 0; i < 12; i++) {
            System.out.println(bbuf.get());
        }

        //解码
        bbuf.flip();
        CharBuffer cbuf2 = cd.decode(bbuf);
        System.out.println(cbuf2.toString());


    }
}

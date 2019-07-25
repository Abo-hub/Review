package com.sun.io;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 测试FileInputStream和FileOutPutStream的使用
 *
 * 结论
 * 1. 对于文本文件，使用字符流处理
 * 2. 对于非文本文件，使用字节流处理
 * @program: Review
 * @author: SunBo
 * @create: 2019-07-24 15:06
 **/

public class FileInputOutputStreamTest {

    @Test
    public void testFileInputStream(){
        FileInputStream fis = null;
        try {
            File file = new File("hello.txt");

            fis = new FileInputStream(file);

            byte[] buffer = new byte[5];
            int len;
            while ((len=fis.read(buffer))!=-1){
                String str = new String(buffer,0,len);
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fis != null){
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


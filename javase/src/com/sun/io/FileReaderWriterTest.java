package com.sun.io;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @program: Review
 * @author: SunBo
 * @create: 2019-07-24 11:39
 **/

/**
 * 将hello.txt 文件内容读入程序中，并输出到控制台
 * <p>
 * 说明点：
 * 1. read()的理解，返回读入到的一个字符，如果达到文件末尾，返回-1
 * 2. 异常的处理：为了保证资源一定可以执行关闭操作。需要使用try-catch/finally
 * 3. 读入的文件一定要存在，否则就会包FileNotFoundException。
 */
public class FileReaderWriterTest {
    public static void main(String[] args) {
        File file = new File("hello.txt");
        System.out.println(file.getAbsolutePath());

        File file1 = new File("javase\\hello.txt");
        System.out.println(file1.getAbsolutePath());
    }

    @Test
    public void testFileReader() {
        FileReader fr = null;
        try {
            //1.实例化File类的对象，知名要操作的文件
            File file = new File("hello.txt");
            //2.提供具体的流
            fr = new FileReader(file);

            //3.数据的读入
            //read()：返回读入的一个字符
            //方式一
//        int data = fr.read();
//        while (data != -1){
//            System.out.print((char)data);
//            data = fr.read();
//        }
            //方式二
            int data;
            while ((data = fr.read()) != -1) {
                System.out.println((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.流的关闭
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //对read()操作升级，使用read的重载方法
    @Test
    public void testFileReader1() {

        FileReader fr = null;
        try {
            //1.File类的实例化
            File file = new File("hello.txt");

            //2.FileReader流的实例化
            fr = new FileReader(file);

            //3.读入的操作
            //read(char[] cbuf)： 返回每次读入cbuf数组的个数，如果达到末尾返回-1.
            char[] cbuf = new char[5];
            int len;
            while ((len = fr.read(cbuf)) != -1) {
                //错误的写法
//                for (int i = 0; i < cbuf.length; i++) {
//                    System.out.println(cbuf[i]);
//                }
                for (int i = 0; i < len; i++) {
                    System.out.print(cbuf[i]);
                }
            }

            //4.资源的关闭
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 从内存中写出数据到硬盘的文件里
     * 说明：
     * 1. 输出操作。对应的File可以不存在。并不会报异常
     * 2.
     * File对应的硬盘中的文件如果不存在，在输出的过程中会自动创建此文件
     * File对应的硬盘中的文件如果存在：
     * 如果使用的构造器：FileWriter(file,fale) / FileWriter(file): 对原有文件的覆盖
     * 如果使用的构造器：FileWriter(file,true) :不会对原有文件覆盖，而是在原有文件基础上追加内容
     */
    @Test
    public void testFileWriter() {
        FileWriter fw = null;
        try {
            //1.提供File类的对象，指明写出到的文件
            File file = new File("hello.txt");

            //2.提供FileWriter的对象，用于数据的写出FileWriter fw = new FileWriter(file);

            //3.写出的操作
            fw.write("I have a dream");

            //4.流资源的关闭
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    public void testFileReaderFileWriter() {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            //1.创建File类的对象，知名读入和写出的文件
            File srcFile = new File("hello.txt");
            File destFile = new File("hello2.txt");

            //2.创建输入流和输出流
            fr = new FileReader(srcFile);
            fw = new FileWriter(destFile);

            //3.数据的读入和写出操作
            char[] cbuf = new char[5];
            int len;//记录每次读入到cbuf数组中的字符的个数
            while ((len=fr.read(cbuf))!=-1){
                //每次写出len个字符
                fw.write(cbuf,0,len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.关闭流资源
            try {
                if(fw!=null){
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if(fr!=null){
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

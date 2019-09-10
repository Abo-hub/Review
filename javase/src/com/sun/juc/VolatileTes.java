package com.sun.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: Review
 * @author: SunBo
 * @create: 2019-08-06 15:07
 **/

class MyData {
    // int number = 0;

    volatile int number = 0;

    public void addTo60() {
        this.number = 60;
    }

    public void addPlusPlus() {
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void addAtomic(){
        atomicInteger.getAndIncrement();
    }
}

/**
 * 1.验证volatile的可见性
 * 1.1 假如 int number = 0； number 没有添加volatile关键字
 * 1.2 添加了volatile，可以解决可见性问题
 *
 *
 * 2. 验证volatile不保证原子性
 * 2.1 原子性是的是什么意思？
 *      不可分割，完成性，也即某个线程正在做某个具体业务时，中间不可以被加塞或者被分割，需要整体完整性。
 *      要么同时成功，要么同时失败
 *
 * 2.2  是否可以保证原子性？
 * volatile的可见性
 *
 * 2.3  why
 *
 * 2.4 如何解决原子性
 *      不要用sync
 *      使用juc下AtomicInteger
 */
public class VolatileTes {

    public static void main(String[] args) {
        MyData myData = new MyData();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData.addPlusPlus();
                    myData.addAtomic();
                }
            }, String.valueOf(i)).start();
        }

        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"\t int finally number value: "+myData.number);
        System.out.println(Thread.currentThread().getName()+"\t AtomicInteger finally  value: "+myData.atomicInteger);
    }

    //volatile 可以保证可见性，即使通知其他线程，主物理内存的值已经被修改。
    public static void seeOkByVoaltile() {
        MyData myData = new MyData();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t coming in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName() + "\t update nmuber value:" + myData.number);
        }, "AAAA").start();

        while (myData.number == 0) {
            //main线程就一直在这里等待循环，直到number值不在等于0
        }
        System.out.println(Thread.currentThread().getName() + "\t misstion is over " + "main get number value:" + myData.number);
    }
}

package com.sun.juc;

/**
 * @program: Review
 * @author: SunBo
 * @create: 2019-08-06 23:00
 **/

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1. CAS 是什么？ ---> CompareAndSet 比较并交换
 *
 *
 */
public class CASDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5, 2019));
    }
}

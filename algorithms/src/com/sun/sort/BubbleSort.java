package com.sun.sort;

import org.junit.Test;

/**
 * @program: Review
 * @author: SunBo
 * @create: 2019-07-24 20:11
 **/

public class BubbleSort {

    public void bubbleSort(int[] arr){
        if(arr == null || arr.length<2){
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if(arr[j]>arr[j+1]){
                    swap(arr,j,j+1);
                }
            }
        }
    }

    public void swap(int[] arr,int i,int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    @Test
    public void testBubbleSort(){
        int[] arr = { 6, 4, 1, 5, 2, 3 };
        System.out.println("最终排序：");
        BubbleSort test = new BubbleSort();
        test.bubbleSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+",");
        }
    }
}

package com.sun.sort;

import org.junit.Test;

/**
 * @program: Review
 * @author: SunBo
 * @create: 2019-07-24 20:23
 **/

public class InsertionSort {

    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
                swap(arr, j, j - 1);
            }
            //    System.out.println("第"+i+"次：");
            //     for (int k = 0; k < arr.length; k++) {

            //         System.out.print(arr[k]+",");
            //     }
            //     System.out.println();
        }
    }

    public static void swap(int[] arr, int i, int j) {
//            int tmp = arr[i];
//            arr[i] = arr[j];
//            arr[j] = tmp;

        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    @Test
    public void testInsertSort() {
        int []arr = {6, 4, 1, 5, 2, 3};
        insertionSort(arr);
        System.out.println("最终排序：");
        for (int value : arr) {
            System.out.print(value + ",");
        }
    }
}

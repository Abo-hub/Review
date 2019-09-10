package com.sun.sort;

import org.junit.Test;

/**
 * @program: Review
 * @author: SunBo
 * @create: 2019-08-07 21:43
 **/

public class ShellSort {

    public static void sheelSort(int[] arr) {
        int N = arr.length;
        int h = 1;

        if (arr == null || N <= 2) {
            return;
        }
        while (h < N / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && arr[j] < arr[j - h]; j -= h) {
                    swap(arr, j, h);
                }
            }
            h = h / 3;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    @Test
    public void testshellSort() {
        int[] arr = {6, 4, 1, 5, 2, 3};
        sheelSort(arr);
        System.out.println("最终排序：");
        for (int value : arr) {
            System.out.print(value + ",");
        }
    }

}



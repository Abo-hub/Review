package com.sun.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @program: Review
 * @author: SunBo
 * @create: 2019-07-24 20:25
 **/

public class MergeSort {

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        sortProcess(arr, 0, arr.length - 1);
    }

    public static void sortProcess(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        // L和R中点的位置  (L + R ) /2
        int mid = L + ((R - L) >> 1);
        sortProcess(arr, L, mid);
        sortProcess(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    public static void merge(int[] arr, int L, int mid, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        //左边数组的游标
        int p1 = L;
        //右边数组的也有表
        int p2 = mid + 1;
        //左右两个数组通过比较下标，较小值放入辅助数组中进行排序。直到一边到头
        while (p1 <= mid && p2 <= R) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 两个必有且只有一个越界
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[L + j] = help[j];
        }

    }

    @Test
    public void testmergeSort() {
        int[] arr = {6, 4, 1, 5, 2, 3, 8, 10, 9, 12, 11, 7};
        mergeSort(arr);
        System.out.println("最终排序：");
        for (int value : arr) {
            System.out.print(value + ",");
        }
    }
}


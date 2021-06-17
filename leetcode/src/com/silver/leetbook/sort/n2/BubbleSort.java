package com.silver.leetbook.sort.n2;

/**
 * 冒泡排序
 * @author csh
 * @date 2021/6/17
 **/
public class BubbleSort {

    public static void main(String[] args) {
        BubbleSort main = new BubbleSort();
        int[] arr = new int[]{1,4,3,5,2};
        main.bubbleSort(arr);
        for (int i : arr) {
            System.out.print(i);
        }
    }

    private void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            // 注意。每冒泡一轮，最后一位元素即最大数，所以外层循环执行len-1次，内层循环每次执行len-1-i次
            for (int j = 0; j < arr.length - 1 - i; j++) {
                // 如果前一位数大于后一位数，swap
                if (arr[j] > arr[j+1])
                    swap(arr, j, j+1);
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

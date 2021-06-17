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
        main.bubbleSort3(arr);
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

    private void bubbleSort2(int[] arr) {
        // 初始时 swapped 为 true，否则排序过程无法启动
        boolean swapped = true;
        for (int i = 0; i < arr.length - 1; i++) {
            // 如果没有发生过交换，说明剩余部分已经有序，排序完成
            if (!swapped) break;
            // 设置 swapped 为 false，如果发生交换，则将其置为 true
            swapped = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j+1]) {
                    // 如果左边的数大于右边的数，则交换，保证右边的数字最大
                    swap(arr, j, j+1);
                    // 表示发生了交换
                    swapped = true;
                }
            }
        }
    }

    private void bubbleSort3(int[] arr) {
        boolean swapped = true;
        // 最后一个没有经过排序的元素的下标
        int lastUnsortedElementIndex = arr.length - 1;
        // 上次发生交换的位置
        int swappedIndex = -1;
        while (swapped) {
            swapped = false;
            for (int i = 0; i < lastUnsortedElementIndex; i++) {
                if (arr[i] > arr[i+1]) {
                    // 如果左边的数大于右边的数，则交换，保证右边的数字最大
                    swap(arr, i, i+1);
                    // 表示发生了交换
                    swapped = true;
                    // 更新交换位置
                    swappedIndex = i;
                }
            }
            // 最后一个没有发生交换的元素的下标就是下一次发生交换的位置
            lastUnsortedElementIndex = swappedIndex;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

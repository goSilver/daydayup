package com.silver.leetbook.sort.n2;

/**
 * 选择排序
 *
 * @author csh
 * @date 2021/6/18
 **/
public class SelectionSort {
    public static void main(String[] args) {
        SelectionSort main = new SelectionSort();
        int[] arr = new int[]{1, 4, 3, 5, 2};
        main.selectionSort(arr);
        for (int i : arr) {
            System.out.print(i);
        }
    }

    private void selectionSort(int[] arr) {
        int minIndex;
        for (int i = 0; i < arr.length; i++) {
            minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    private void test(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }

        for (int i = 0; i < arr.length - 1; i++) {
            System.out.print(arr[i]);
        }
    }
}

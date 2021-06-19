package com.silver.leetbook.sort.n2.InsertSort;

/**
 * 插入排序
 * @author csh
 * @date 2021/6/18
 **/
public class InsertSort {
    public static void main(String[] args) {
        InsertSort main = new InsertSort();
        int[] arr = new int[]{1,4,3,5,2};
        main.insertSort(arr);
        for (int i : arr) {
            System.out.print(i);
        }
    }
    private void insertSort(int[] arr) {
        // 从第二个数字开始，插入
        for (int i = 1; i < arr.length; i++) {
            // j 记录当前数字下标
            int j = i;
            // 当前数字比前一个数字要小，进行交换
            while (j >= 1 && arr[j] < arr[j-1]) {
                swap(arr, j, j-1);
                // 更新当前数字下标，继续往前比较
                j--;
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

package com.silver.review.leetbook.sort;

/**
 * 快排
 * @author csh
 * @date 2021/6/17
 **/
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[]{6, 2, 1, 3, 5, 4};
        quickSort(arr);
        for (int i : arr) {
            System.out.print(i);
        }
    }
    private static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length-1);
    }

    private static void quickSort(int[] arr, int start, int end) {
        // base case
        if (start >= end) return;
        // 寻找中位点
        int middle = partition(arr, start, end);
        // 对左边部分排序
        quickSort(arr, start, middle-1);
        // 对右边部分排序
        quickSort(arr, middle+1, end);
    }

    private static int partition(int[] arr, int start, int end) {
        // 基准元素
        int pivot = arr[start];
        // 从第二位开始排序
        int left = start +1;
        int right = end;
        // left、right相遇时推出循环。这个while里就可以把所有元素分组。
        while (left < right) {
            // 从左边开始，如果小于基准元素，left++。意思就是找到第一个大于基准的数
            while (left < right && arr[left] < pivot) left++;
            //
            if (left != right) {
                swap(arr, left, right);
                right--;
            }
        }

        // left等于right时，单独处理
        if (left == right && arr[right] > pivot) right--;
        // 将基数与中间数交换
        if (right != start) swap(arr, right, start);

        return right;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

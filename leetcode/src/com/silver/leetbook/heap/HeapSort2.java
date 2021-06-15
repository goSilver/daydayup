package com.silver.leetbook.heap;

/**
 * @author csh
 * @date 2021/6/15
 **/
public class HeapSort2 {

    public static void main(String[] args) {
        HeapSort2 main = new HeapSort2();
        int[] sort = main.sort(new int[]{1, 3, 2, 5, 4});
        for (int i : sort) {
            System.out.print(i);
        }
    }

    private int[] sort(int[] arr) {
        int len = arr.length;
        // 构建大顶堆
        for (int i = (int) Math.floor(len / 2); i >= 0; i--) {
            heapify(arr, i, len);
        }
        // 将堆顶元素依次放到数组尾部
        for (int i = len - 1; i > 0; i--) {
            swap(arr, 0, i);
            len--;
            heapify(arr, 0, len);
        }
        return arr;
    }

    private void heapify(int[] arr, int i, int len) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;
        if (left < len && arr[left] > arr[largest])
            largest = left;

        if (right < len && arr[right] > arr[largest])
            largest = right;

        if (largest != i) {
            swap(arr, largest, i);
            heapify(arr, largest, len);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

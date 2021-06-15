package com.silver.leetbook.heap;

/**
 * 求topK个最大元素
 * 思路：
 * 构造一个最大堆，然后循环k次取出堆顶元素
 *
 * 时间复杂度： O(KlogN)
 * 空间复杂度：O(N)
 *
 * @author csh
 * @date 2021/6/15
 **/
public class TopK1 {
    public static void main(String[] args) {
        TopK1 main = new TopK1();
        int[] sort = main.topK(new int[]{1, 3, 2, 5, 4}, 3);
        for (int i : sort) {
            System.out.print(i);
        }
    }

    private int[] topK(int[] arr, int k) {
        int len = arr.length;
        // 构造大顶堆
        for (int i = (int) Math.floor(len / 2); i >= 0; i--) {
            heapify(arr, i, len);
        }
        // 循环k次，取出k个堆顶
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = arr[0];
            swap(arr, 0, len - 1);
            len--;
            heapify(arr, 0, len);
        }
        return res;
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

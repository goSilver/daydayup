package com.silver.sword4offer.q31_q40;

/**
 * 最小的k个数
 * @author csh
 * @date 2021/6/20
 **/
public class q40_GetLeastNumbers {
    public int[] getLeastNumbers(int[] arr, int k) {

        // 构造一个k个元素的大顶堆
        int[] res = new int[k];
        if (k <= 0) return res;
        System.arraycopy(arr, 0, res, 0, k);
        for (int i = (int) Math.floor(k / 2); i >= 0; i--) {
            heapify(res, i, k);
        }

        // 向这个最大堆插入元素
        for (int i = k; i < arr.length; i++) {
            if (res[0] > arr[i]) {
                res[0] = arr[i];
                heapify(res, 0, k);
            }
        }
        return res;
    }

    private void heapify(int[] arr, int i, int len) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int max = i;

        if (left < len && arr[left] > arr[max])
            max = left;

        if (right < len && arr[right] > arr[max])
            max = right;

        if (max != i) {
            swap(arr, max, i);
            heapify(arr, max, len);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

package com.silver.leetbook.heap;

/**
 * 第k大的数
 * 思路：
 * 构造一个k个元素的最小堆res
 * 对原数组arr从k开始遍历，一边遍历，一边和堆顶元素比较
 * 如果num大于堆顶，则删除堆顶，并堆顶赋值为num
 * 遍历完成后，堆顶元素即是第k大
 *
 * 时间复杂度： O(NlogK)
 * 空间复杂度： O(K)
 *
 * @author csh
 * @date 2021/6/15
 **/
public class TheKth2 {
    public static void main(String[] args) {
        TheKth2 main = new TheKth2();
        int kth = main.kth(new int[]{1, 3, 2, 5, 4}, 4);
        System.out.print(kth);
    }

    private int kth(int[] arr, int k) {
        // 构造一个k个元素的最小堆
        int[] res = new int[k];
        System.arraycopy(arr, 0, res, 0, k);
        for (int i = (int) Math.floor(k / 2); i >= 0; i--) {
            heapify(arr, 0, k);
        }
        // 从k开始遍历并和堆顶元素比较
        for (int i = k; i < arr.length; i++) {
            if (arr[i] > res[0]) {
                res[0] = arr[i];
                heapify(res, 0, k);
            }
        }
        return res[0];
    }

    private void heapify(int[] arr, int i, int len) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int min = i;

        if (left < len && arr[left] < arr[min])
            min = left;

        if (right < len && arr[right] < arr[min])
            min = right;

        if (min != i) {
            swap(arr, min, i);
            heapify(arr, min, len);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

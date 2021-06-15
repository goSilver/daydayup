package com.silver.leetbook.heap;

/**
 * 第k大元素
 * 思路：
 * 先构造成一个最大堆
 * 然后循环k次，依次删除堆顶，k次后堆顶元素就是第k大元素
 *
 * 时间复杂度： O(KlogN)
 * 空间复杂度： O(N)
 *
 * @author csh
 * @date 2021/6/15
 **/
public class TheKth1 {
    public static void main(String[] args) {
        TheKth1 main = new TheKth1();
        int kth = main.hth(new int[]{1, 3, 2, 5, 4}, 3);
        System.out.print(kth);
    }

    private int hth(int[] arr, int k) {
        int len = arr.length;
        // 构造一个最大堆
        for (int i = (int) Math.floor(len / 2); i >= 0; i--) {
            heapify(arr, i, len);
        }

        // 取出第k个堆顶元素
        for (int i = len - 1; i > 0; i--) {
            k -= 1;
            if (k == 0) {
                return arr[0];
            }
            swap(arr, 0, i);
            heapify(arr, 0, i);
        }
        return -1;
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

    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}

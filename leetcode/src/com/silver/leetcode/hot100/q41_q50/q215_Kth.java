package com.silver.leetcode.hot100.q41_q50;

/**
 * @author csh
 * @date 2021/6/27
 **/
public class q215_Kth {
    public int findKthLargest(int[] nums, int k) {
        int[] minHeap = new int[k];
        System.arraycopy(nums, 0, minHeap, 0, k);
        for (int i = (int) Math.floor(k / 2); i >= 0; i--) {
            heapify(minHeap, i, k);
        }

        for (int i = k; i < nums.length; i++) {
            if (nums[i] > minHeap[0]) {
                minHeap[0] = nums[i];
                heapify(minHeap, 0, k);
            }
        }
        return minHeap[0];
    }

    private void heapify(int[] minHeap, int i, int len) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int min = i;
        if (left < len && minHeap[left] < minHeap[min])
            min = left;
        if (right < len && minHeap[right] < minHeap[min])
            min = right;
        if (min != i) {
            swap(minHeap, min, i);
            heapify(minHeap, min, len);
        }
    }

    private void swap(int[] minHeap, int i, int j) {
        int temp = minHeap[i];
        minHeap[i] = minHeap[j];
        minHeap[j] = temp;
    }
}

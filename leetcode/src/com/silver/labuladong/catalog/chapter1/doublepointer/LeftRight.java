package com.silver.labuladong.catalog.chapter1.doublepointer;

/**
 * @author csh
 * @date 2021/5/1
 */
public class LeftRight {
    // 二分查找

    // 反转数组
    private void reverse(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;right--;
        }
    }

    // 滑动窗口算法
}

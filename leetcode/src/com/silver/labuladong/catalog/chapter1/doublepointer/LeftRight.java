package com.silver.labuladong.catalog.chapter1.doublepointer;

/**
 * @author csh
 * @date 2021/5/1
 */
public class LeftRight {
    // 寻找左侧边界的二分查找
    private int leftBound(int[] arr, int target) {
        if (arr.length < 1) return -1;
        int left = 0;
        int right = arr.length;
        while (left < right) {
            int mid = (left + (right - left)) / 2;
            if (arr[mid] == target) {
                right = mid;// 注意
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else if (arr[mid] > target) {
                right = mid;// 注意
            }
        }
        return left;
    }

    // 寻找右侧边界的二分查找
    private int rightBound(int[] arr, int target) {
        if (arr.length < 1) return -1;
        int left = 0;
        int right = arr.length;
        while (left < right) {
            int mid = (left + (right - left)) / 2;
            if (arr[mid] == target) {
                left = mid + 1;// 注意
            } else if (arr[mid] < target) {
                left = mid + 1;// 注意
            } else if (arr[mid] > target) {
                right = mid;
            }
        }
        return left - 1;// 注意
    }

    // 反转数组
    private void reverse(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    // 滑动窗口算法
}

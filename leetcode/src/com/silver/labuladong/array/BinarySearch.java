package com.silver.labuladong.array;

/**
 * 二分查找算法的几种实现
 *
 * @author csh
 * @date 2021/3/30
 */
public class BinarySearch {

    /**
     * 普通二分查找
     *
     * @param arr
     * @param target
     * @return
     */
    public int binarySearch(int[] arr, int target) {
        if (arr.length == 0) return -1;
        int left = 0;
        // 注意
        int right = arr.length -1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                // 注意
                right = mid + 1;
            } else if (arr[mid] < target) {
                // 注意
                left = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 寻找左侧边界的二分查找
     *
     * @param nums
     * @param target
     * @return
     */
    public int left_bound(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int left = 0;
        int right = nums.length; // 注意

        while (left < right) { // 注意
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid; // 注意
            }
        }
        return left;
    }

    /**
     * 寻找右侧边界的二分查找
     *
     * @param nums
     * @param target
     * @return
     */
    public int right_bound(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int left = 0, right = nums.length;

        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                left = mid + 1; // 注意
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            }
        }
        return left - 1; // 注意
    }
}

package com.silver.temp;

/**
 * 二分查找的几种实现
 *
 * @author csh
 * @date 2021/1/28
 */
public class BinarySearch {

    /**
     * 基本的二分搜索
     *
     * @param arr    存放元素的数组
     * @param target 目标元素
     * @return 目标元素在数组中的位置
     */
    private int basicBinarySearch(int[] arr, int target) {
        int left = 0;
        // 注意
        int right = arr.length - 1;

        // <= 表示在[left, right]区间查找
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                // 注意
                right = mid - 1;
            } else if (arr[mid] > target) {
                // 注意
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 寻找左侧边界的二分搜索
     *
     * @param arr    存放元素的数组
     * @param target 目标值
     * @return 目标值所在位置下标
     */
    private int leftBound(int[] arr, int target) {
        if (arr.length == 0) return -1;
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                // 注意
                right = mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else if (arr[mid] > target) {
                // 注意
                right = mid;
            }
        }
        return left;
    }

    /**
     * 寻找左侧边界的二分搜索
     *
     * @param arr    存放元素的数组
     * @param target 目标值
     * @return 目标值所在位置下标
     */
    private int rightBound(int[] arr, int target) {
        if (arr.length == 0) return -1;
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                // 注意
                left = mid + 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else if (arr[mid] > target) {
                right = mid;
            }
        }
        // 注意
        return left - 1;
    }

}

package com.silver.labuladong.array.leftright;

/**
 * 双指针技巧之左右指针
 * 1、二分查找
 * 2、两数之和
 * 3、反转数组
 * 4、滑动窗口
 *
 * @author csh
 * @date 2021/01/30
 **/
public class LeftAndRightPointer {

    /**
     * 二分查找（已知数组有序）
     *
     * @param arr    存放已知元素的数组
     * @param target 目标元素
     * @return 目标元素的下标
     */
    private int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else if (arr[mid] > target) {
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 两数之和
     * 给定一个升序数组和一个目标值target
     * 在数组中寻找两位数的和等于目标值
     * 返回符合条件的两位数的下标
     *
     * @param arr    升序数组
     * @param target 目标值
     * @return 下标数组
     */
    private int[] twoSum(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == target) {
                return new int[]{left, right};
            } else if (sum < target) {
                left += 1;
            } else if (sum > target) {
                right -= 1;
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 反转数组
     *
     * @param arr 原始数组
     * @return 反转后的数组
     */
    private int[] reverseArray(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left ++;
            right --;
        }
        return arr;
    }
}

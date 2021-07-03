package com.silver.leetcode.hot100.review.first;

/**
 * 下一个排列
 *
 * @author csh
 * @date 2021/7/4
 **/
public class q31_nextPermutation {
    public void nextPermutation(int[] nums) {
        // 注意。从尾到头找第一个逆序对
        int index = nums.length - 2;
        while (index >= 0 && nums[index] >= nums[index - 1])
            index--;

        // 如果存在逆序对才进入分支
        if (index >= 0) {
            // 从尾到头找第一个大于逆序数的数，并交换
            int right = nums.length - 1;
            while (right >= 0 && nums[right] < nums[index]) {
                right--;
            }
            swap(nums, index, right);
        }

        // 对逆序处以后元素从降序变升序，变得尽可能的小
        reverse(nums, index);
    }

    private void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

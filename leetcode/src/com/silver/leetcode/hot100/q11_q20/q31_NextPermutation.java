package com.silver.leetcode.hot100.q11_q20;

/**
 * 下一个排列
 *
 * @author csh
 * @date 2021/6/22
 **/
public class q31_NextPermutation {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        // 从尾往首寻找到第一个逆序对
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        // 如果有逆序对才进入分支，没有逆序对则说明已经是最大排列，直接反转
        if (i >= 0) {
            int j = nums.length - 1;
            // 从尾往前找到第一个大于nums[i]的元素并交换
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        // 对i以后的元素做逆序处理
        reverse(nums, i + 1);
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
        int tmp = nums[j];
        nums[j] = nums[i];
        nums[i] = tmp;
    }
}

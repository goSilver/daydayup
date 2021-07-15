package com.silver.leetcode.hot100.review.first;

/**
 * 寻找重复数字
 *
 * @author csh
 * @date 2021/7/15
 **/
public class q287_findDuplicate {
    public int findDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[nums[i]] == nums[i]) return nums[i];
                swap(nums, i, nums[i]);
            }
        }
        return -1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

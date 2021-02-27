package com.silver.leetcode.array.slowfast;

/**
 * 两数之和
 * 给定一个有序数组，判断是否存在两个数相加等于target
 *
 * 解法：
 * 利用双指针
 *
 * @author csh
 * @date 2021/2/27
 **/
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return new int[]{left, right};
            } else if (sum < target) {
                left++; // 让 sum 大一点
            } else if (sum > target) {
                right--; // 让 sum 小一点
            }
        }
        // 不存在这样两个数
        return new int[]{-1, -1};
    }
}

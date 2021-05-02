package com.silver.labuladong.catalog.chapter2;

/**
 * 最大子数组
 *
 * @author csh
 * @date 2021/5/2
 **/
public class MaxSubArray {

    private int maxSubArray(int[] nums) {
        int length = nums.length;
        if (length == 0) return 0;
        // 定义dp数组：以nums[i]为结尾的“最大子数组和”为dp[i]
        int[] dp = new int[length];
        // 第一个元素前面没有子数组
        dp[0] = 0;
        for (int i = 1; i < nums.length; i++) {
            // 要么自成一派，要么和前面的合并
            dp[i] = Math.max(nums[i], nums[i] + dp[i-1]);
        }
        // 得到nums的最大子数组
        int res = Integer.MIN_VALUE;
        for (int num : dp) {
            res = Math.max(res, num);
        }
        return res;
    }
}

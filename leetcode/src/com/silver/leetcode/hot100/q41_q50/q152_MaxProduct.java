package com.silver.leetcode.hot100.q41_q50;

/**
 * 乘积最大子数组
 *
 * @author csh
 * @date 2021/6/27
 **/
public class q152_MaxProduct {
    public int maxProduct(int[] nums) {
        int max = nums[0], min = nums[0], res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int mx = max, mn = min;
            max = Math.max(mx * nums[i], Math.max(nums[i], mn * nums[i]));
            min = Math.min(mn * nums[i], Math.min(nums[i], mx * nums[i]));
            res = Math.max(res, max);
        }
        return res;
    }
}

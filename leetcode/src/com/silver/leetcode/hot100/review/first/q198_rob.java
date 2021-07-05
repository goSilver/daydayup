package com.silver.leetcode.hot100.review.first;

/**
 * @author csh
 * @date 2021/7/5
 **/
public class q198_rob {
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        int first = 0, second = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int newF = Math.max(first, second);
            int newS = Math.max(second, first + nums[i]);
            first = newF;
            second = newS;
        }
        return Math.max(first, second);
    }
}

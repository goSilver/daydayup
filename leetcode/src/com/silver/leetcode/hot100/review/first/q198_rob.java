package com.silver.leetcode.hot100.review.first;

/**
 * 打家劫舍
 *
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

    public int rob2(int[] nums) {
        if (nums.length == 0) return 0;
        int[][] rob = new int[nums.length][2];
        rob[0][0] = 0;
        rob[0][1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            rob[i][0] = Math.max(rob[i - 1][0], rob[i - 1][1]);
            rob[i][1] = Math.max(rob[i - 1][0] + nums[i], rob[i - 1][1]);
        }
        return Math.max(rob[nums.length - 1][0], rob[nums.length - 1][1]);
    }
}

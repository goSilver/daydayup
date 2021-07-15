package com.silver.labuladong.catalog.chapter2;

import java.util.Arrays;

/**
 * 最长递增子序列（Longest Increasing Subsequence）
 * 一维：
 * 二维：信封嵌套问题
 * 三维：
 *
 * @author csh
 * @date 2021/5/2
 **/
public class LengthOfLIS {
    /**
     * 最长递增子序列（一维）
     *
     * @param arr 数组
     * @return 最长递增子序列长度
     */
    private int lengthOfLIS(int[] arr) {
        // 定义dp数组：dp[i]表示以nums[i]这个数结尾的最长递增子序列的长度
        int[] dp = new int[arr.length];
        // dp[i]的初始值为1，因为以nums[i]结尾的的最长递增子序列起码要包含自己
        Arrays.fill(dp, 1);
        // 状态转移
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        int res = 0;
        // 重新遍历一遍dp数组，找到最长的长度
        for (int length : dp) {
            res = Math.max(res, length);
        }
        return res;
    }

    /**
     * 信封嵌套问题
     * 思路：
     * 先对宽度w进行升序排序，如果遇到w相同的情况，则按照高度h降序排序。
     * 之后，把所有h作为一个数组，在这个数组上计算出的LIS的长度就是答案。
     *
     * @param envelopes 二维数组
     * @return 最多能套层数
     */
    private int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        Arrays.sort(envelopes, (a, b) -> {
            // 按宽度升序排序，如果宽度一样，则按高度降序排序
            return a[0] == b[0] ? b[1] - a[1] : a[0] - b[0];
        });
        // 对高度数组寻找LIS
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = envelopes[i][1];
        }
        return lengthOfLIS(heights);
    }
}

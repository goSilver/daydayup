package com.silver.labuladong.catalog.chapter2;

/**
 * 最长公共子序列（Longest Common Subsequence）
 *
 * @author csh
 * @date 2021/5/2
 **/
public class LengthOfLCS {

    private int longestCommonSubsequence(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        // 定义：dp[i][j]表示s1[0..i]和s[0..j]中最长公共子序列的长度
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[m][n];
    }
}

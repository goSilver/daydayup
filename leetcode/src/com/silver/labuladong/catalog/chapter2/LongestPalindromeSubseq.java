package com.silver.labuladong.catalog.chapter2;

import java.util.Arrays;

/**
 * 最长回文子序列
 *
 * @author csh
 * @date 2021/5/2
 **/
public class LongestPalindromeSubseq {

    private int LongestPalindromeSubseq(String s) {
        int n = s.length();
        // dp数组：在字串s[i..j]中，最长回文子序列的长度为dp[i][j]
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++)
            dp[i][i] = 1;

        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                // 状态转移方程
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        // 整个s的最长回文子序列长度
        return dp[0][n - 1];
    }

    // 对二维dp数组进行状态压缩的解法
    private int longestPalindromeSubseqZip(String s) {
        int n = s.length();
        int[] dp = new int[n];
        // base case;一维数组全部初始化为1
        Arrays.fill(dp, 1);
        for (int i = n - 2; i >= 0; i--) {
            // 存储dp[i+1][j-1]的变量
            int pre = 0;
            for (int j = i + 1; j < n; j++) {
                int temp = dp[j];
                if (s.charAt(i) == s.charAt(j))
                    // dp[i][j] = dp[i+1][j-1]+2
                    dp[j] = pre + 2;
                else
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                // 到下一轮循环，pre就是dp[i+1][j-1]了
                pre = temp;
            }
        }
        return dp[n - 1];
    }
}

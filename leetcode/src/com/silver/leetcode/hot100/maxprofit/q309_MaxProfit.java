package com.silver.leetcode.hot100.maxprofit;

/**
 * @author csh
 * @date 2021/7/1
 **/
public class q309_MaxProfit {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;
        int[][] dp = new int[n][3];
        // 0：持有一支股票；1：未持有股票且处于冷冻期；2：未持有股票且没有处于冷冻期
        dp[0][0] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]);
            dp[i][1] = dp[i - 1][0] + prices[i];
            dp[i][2] = Math.max(dp[i - 1][1], dp[i - 1][2]);
        }
        return Math.max(dp[n - 1][1], dp[n - 2][2]);
    }

    public int maxProfit2(int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;
        int f0 = -prices[0], f1 = 0, f2 = 0;
        for (int i = 1; i < n; i++) {
            int newF0 = Math.max(f0, f2 - prices[i]);
            int newF1 = f0 + prices[i];
            int newF2 = Math.max(f1, f2);
            f0 = newF0;
            f1 = newF1;
            f2 = newF2;
        }
        return Math.max(f1, f2);
    }
}

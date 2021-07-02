package com.silver.leetcode.hot100.q51_q60;

import java.util.Arrays;

/**
 * 凑零钱
 * @author csh
 * @date 2021/7/2
 **/
public class q322_CoinChange {
    public int coinChange(int[] coins, int amount) {
        int max = amount+1;
        int[] dp = new int[max];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i < max; i++) {
            for (int coin : coins) {
                if (coin <= i)
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] == max ? -1 : dp[amount];
    }
}

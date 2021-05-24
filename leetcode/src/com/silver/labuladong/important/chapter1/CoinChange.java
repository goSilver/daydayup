package com.silver.labuladong.important.chapter1;

import java.util.Arrays;

/**
 * 凑零钱
 * @author csh
 * @date 2021/5/24
 **/
public class CoinChange {
    private int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[max];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 0; i < amount; i++) {
            for (int coin : coins) {
                if (i - coin < 0) continue;
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}

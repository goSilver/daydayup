package com.silver.labuladong.dynamic;

import java.util.Arrays;

/**
 * 凑零钱
 *
 * @author csh
 * @date 2021/01/31
 **/
public class CoinChange {
    public static void main(String[] args) {
        int[] coins = new int[]{1,2,5};
        CoinChange main = new CoinChange();
        main.coinChange(coins, 11);
    }
    /**
     * 凑零钱
     *
     * @param coins 硬币
     * @param amount  目标数
     * @return 凑齐目标数的最少硬币数
     */
    private int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        // dp数组的定义：当目标金额为i时，至少需要dp[i]枚硬币凑出
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];

    }
}

package com.silver.labuladong.catalog.chapter1;

import java.util.Arrays;
import java.util.List;

/**
 * 凑零钱
 *
 * @author csh
 * @date 2021/5/1
 */
public class CoinChange {

    private int coinChange(List<Integer> coins, Integer amount) {
        int max = coins.size() + 1;
        int[] dpTable = new int[max];
        Arrays.fill(dpTable, max);
        dpTable[0] = 0;
        for (int i = 1; i < dpTable.length; i++) {
            for (Integer coin : coins) {
                if (i - coin < 0) continue;
                dpTable[i] = Math.min(dpTable[i], 1 + dpTable[i - coin]);
            }
        }
        return dpTable[amount] == max ? -1 : dpTable[amount];
    }
}

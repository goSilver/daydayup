package com.silver.leetcode.array;

/**
 * 股票买卖的最佳时机
 *
 * @author csh
 * @date 2021/4/6
 */
public class Q121MaxProfit {

    /**
     * 只有两次交易时机
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice) {
                minprice = prices[i];
            } else if (prices[i] - minprice > maxprofit) {
                maxprofit = prices[i] - minprice;
            }
        }
        return maxprofit;
    }


}

package com.silver.sword4offer.q61_q70;

/**
 * 股票买卖
 * @author csh
 * @date 2021/6/21
 **/
public class q63_MaxProfit {

    public int maxProfit(int[] prices) {
        int cost = Integer.MAX_VALUE, profit= 0;
        for (int price : prices) {
            cost = Math.min(price, cost);
            profit = Math.max(profit, price - cost);
        }
        return profit;
    }
}

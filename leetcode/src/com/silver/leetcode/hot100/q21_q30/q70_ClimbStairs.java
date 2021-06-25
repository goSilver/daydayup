package com.silver.leetcode.hot100.q21_q30;

/**
 * 爬楼梯
 * @author csh
 * @date 2021/6/25
 **/
public class q70_ClimbStairs {
    public int climbStairs(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;

        int pre = 1, cur = 2;
        for (int i = 3; i <= n; i++) {
            int sum = pre + cur;
            pre = cur;
            cur = sum;
        }
        return cur;
    }
}

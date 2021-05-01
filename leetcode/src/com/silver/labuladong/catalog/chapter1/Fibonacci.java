package com.silver.labuladong.catalog.chapter1;

import java.util.HashMap;

/**
 * 斐波那契数列
 *
 * @author csh
 * @date 2021/5/1
 */
public class Fibonacci {

    private int fibRecurse(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        return fibRecurse(n - 1) + fibRecurse(n - 2);
    }

    private HashMap<Integer, Integer> memo = new HashMap<>();

    private int fibMap(int n) {
        if (n == 1 || n == 2) return 1;
        if (memo.containsKey(n)) {
            return memo.get(n);
        } else {
            int fib = fibMap(n - 1) + fibMap(n - 2);
            memo.put(n, fib);
            return fib;
        }
    }

    private int fibDp(int n) {
        if (n == 1 || n == 2) return 1;
        int[] dp = new int[n];
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
    }

    private int bestFib(int n) {
        if (n == 1 || n == 2) return 1;
        int pre = 1;
        int cur = 1;
        for (int i = 2; i < n; i++) {
            int sum = pre + cur;
            pre = cur;
            cur = sum;
        }
        return cur;
    }
}

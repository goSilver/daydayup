package com.silver.dynamic;

import java.util.HashMap;

/**
 * 斐波那契数列
 * 求数列的第n项
 *
 * @author csh
 * @date 2021/01/31
 **/
public class Fibonacci {

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        int test = 7;
        int a = fibonacci.basicFib(test);
        int i = fibonacci.mapFib(test);
        int dp = fibonacci.dpFib(test);
        int best = fibonacci.bestFib(test);
        System.out.println("basic：" + a);
        System.out.println("map：" + i);
        System.out.println("dp：" + dp);
        System.out.println("best：" + best);
    }

    /**
     * 暴力递归解法
     * 时间复杂度 O(2^n)
     *
     * @param n 入参
     * @return 第n项
     */
    private int basicFib(int n) {
        // base case
        if (n == 1 || n == 2) return 1;
        return basicFib(n - 1) + basicFib(n - 2);
    }


    private HashMap<Integer, Integer> map = new HashMap<>();

    /**
     * 带备忘录的递归解法
     * 时间复杂度O(n)
     *
     * @param n 入参
     * @return 第n项
     */
    private int mapFib(int n) {
        if (n == 1 || n == 2) return 1;
        if (map.containsKey(n)) {
            return map.get(n);
        } else {
            int fib = mapFib(n - 1) + mapFib(n - 2);
            map.put(n, fib);
            return fib;
        }
    }

    /**
     * dp 数组的迭代解法
     * 时间复杂度O(n)
     *
     * @param n 入参
     * @return 第n项
     */
    private int dpFib(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
    }

    /**
     * 优化dp数组解法
     * 无需一个数组来保存记录，只需记录前两位即可
     *
     * @param n 入参
     * @return 第n项
     */
    private int bestFib(int n) {
        if (n == 1 || n == 2) return 1;
        int pre = 1;
        int cur = 1;
        for (int i = 3; i <= n; i++) {
            int sum = pre + cur;
            pre = cur;
            cur = sum;
        }
        return cur;
    }
}

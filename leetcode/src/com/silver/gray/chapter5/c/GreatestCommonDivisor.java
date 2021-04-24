package com.silver.gray.chapter5.c;

/**
 * 最大公约数
 * 暴力法：时间复杂度是O(min(a,b))
 * 辗转相除法：时间复杂度近似O(log(max(a,b)))，但是取模运算性能较差
 * 更相减损法：避免了取模运算，但是算法性能不稳定，最坏时间复杂度为O(max(a,b))
 *
 * @author csh
 * @date 2021/4/24
 */
public class GreatestCommonDivisor {

    /**
     * 暴力法
     *
     * @param a
     * @param b
     * @return
     */
    private int gcdFirst(int a, int b) {
        int big = a > b ? a : b;
        int small = a < b ? a : b;
        // base case
        if (big % small == 0) return small;

        // 从较小的数的一半开始，找到一个能同时被a，b整除的数
        for (int i = small / 2; i > 1; i--) {
            if (small % i == 0 && big % i == 0) return i;
        }
        return 1;
    }

    /**
     * 辗转相除法
     * 定理：两个正整数a和b（a>b）,它们的最大公约数等于a除以b的余数c和b之间的最大公约数
     *
     * @param a
     * @param b
     * @return
     */
    private int gcdSecond(int a, int b) {
        int big = a > b ? a : b;
        int small = a < b ? a : b;
        if (big % small == 0) return small;
        return gcdSecond(big % small, small);
    }

    /**
     * 更相减损法
     *
     * @param a
     * @param b
     * @return
     */
    private int gcdThird(int a, int b) {
        if (a == b) return a;
        int big = a > b ? a : b;
        int small = a < b ? a : b;
        return gcdThird(big - small, small);
    }
}

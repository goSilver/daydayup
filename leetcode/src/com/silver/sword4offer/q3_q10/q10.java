package com.silver.sword4offer.q3_q10;

/**
 * 青蛙跳台阶问题
 * https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/
 *
 * @author csh
 * @date 2021/2/1
 **/
public class q10 {
    public static void main(String[] args) {
        q10 q = new q10();
        int ways = q.Recursive(4);
        System.out.println(ways);
    }


    /**
     * 暴力递归解法
     * 时间复杂度O(n^2)
     *
     * @param n 台阶数
     * @return 多少种跳法
     */
    public int Recursive(int n) {
        if (n==0 || n ==1) return 1;
        return (Recursive(n-1)+Recursive(n-2)) % 1000000007;
    }

    /**
     * 动态规划
     * 时间复杂度O(n)
     *
     * @param n 台阶数
     * @return 多少种跳法
     */
    public int numWays(int n) {
        int a = 1, b = 1, sum;
        for(int i = 0; i < n; i++){
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return a;
    }

}

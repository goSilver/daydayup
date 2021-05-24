package com.silver.labuladong.important.chapter1;

/**
 * 斐波那契数列
 * @author csh
 * @date 2021/5/24
 **/
public class Fib {
    private int fib(int n) {
        if (n == 0 )return 0;
        if (n == 1 || n==2) return 1;
        int pre = 1, cur = 1;
        for (int i = 3; i < n; i++) {
            int sum = pre + cur;
            pre = cur;
            cur = sum;
        }
        return cur;
    }
}

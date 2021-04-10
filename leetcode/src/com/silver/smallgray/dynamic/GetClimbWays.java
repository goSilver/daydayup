package com.silver.smallgray.dynamic;

/**
 * 跳台阶
 *
 * @author csh
 * @date 2021/4/10
 */
public class GetClimbWays {

    public int getClimbWays(int n) {
        // base case
        if (n < 1) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;

        // 自底向上
        int a = 1;
        int b = 2;
        int temp = 0;
        for (int i = 3; i <= n; i++) {
            temp = a + b;
            a = b;
            b = temp;
        }
        return temp;
    }
}

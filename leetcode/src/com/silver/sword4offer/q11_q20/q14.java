package com.silver.sword4offer.q11_q20;

/**
 * 剪绳子
 *
 * @author csh
 * @date 2021/2/14
 **/
public class q14 {
    public int cuttingRope(int n) {
        if(n <= 3) return n - 1;
        int a = n / 3, b = n % 3;
        if(b == 0) return (int)Math.pow(3, a);
        if(b == 1) return (int)Math.pow(3, a - 1) * 4;
        return (int)Math.pow(3, a) * 2;
    }

}

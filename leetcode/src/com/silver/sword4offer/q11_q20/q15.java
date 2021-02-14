package com.silver.sword4offer.q11_q20;

/**
 * 二进制中1的个数
 *
 * @author csh
 * @date 2021/2/14
 **/
public class q15 {

    public static void main(String[] args) {
        q15 q = new q15();
        q.hammingWeight(00000000000000000000000000001011);
    }

    public int hammingWeight(int n) {
        int res = 0;
        while(n != 0) {
            res += n & 1;
            n >>>= 1;
        }
        return res;
    }
}

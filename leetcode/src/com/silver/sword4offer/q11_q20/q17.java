package com.silver.sword4offer.q11_q20;

/**
 * 打印从1到最大的n位数
 *
 * @author csh
 * @date 2021/2/14
 **/
public class q17 {

    public static void main(String[] args) {
        q17 q = new q17();
        q.printNumbers(1);
    }

    public int[] printNumbers(int n) {
        int max = (int)Math.pow(10, n) - 1;
        int[] res = new int[max];
        for (int i = 0; i < max; i++) {
            res[i] = i +1;
        }
        return res;
    }
}

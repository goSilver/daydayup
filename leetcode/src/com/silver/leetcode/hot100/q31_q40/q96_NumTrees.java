package com.silver.leetcode.hot100.q31_q40;

/**
 * 不同的二叉搜索树
 *
 * @author csh
 * @date 2021/6/26
 **/
public class q96_NumTrees {
    public int numTrees(int n) {
        int[] arr = new int[n + 1];
        arr[0] = 1;
        arr[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                arr[i] += arr[j - 1] * arr[i - j];
            }
        }
        return arr[n];
    }
}

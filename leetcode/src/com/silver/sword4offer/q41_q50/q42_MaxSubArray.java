package com.silver.sword4offer.q41_q50;

/**
 * 连续子数组的最大和
 * @author csh
 * @date 2021/6/20
 **/
public class q42_MaxSubArray {

    private int maxSubArray(int[] arr) {
        int res = arr[0];
        for (int i = 1; i < arr.length; i++) {
            arr[i] += Math.max(arr[i - 1], 0);
            res += Math.max(res, arr[i]);
        }
        return res;
    }
}

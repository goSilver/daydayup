package com.silver.leetcode.hot100.q31_q40;

/**
 * @author csh
 * @date 2021/6/26
 **/
public class q136_SingleNumber {
    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }
}

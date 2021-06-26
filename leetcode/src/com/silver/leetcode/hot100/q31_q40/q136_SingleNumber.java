package com.silver.leetcode.hot100.q31_q40;

/**
 * 只出现一次的数字
 *
 * @author csh
 * @date 2021/6/26
 **/
public class q136_SingleNumber {
    /**
     * 异或运算有以下三个性质。
     * <p>
     * 任何数和 00 做异或运算，结果仍然是原来的数，即 a⊕0=a。
     * 任何数和其自身做异或运算，结果是 0，即 a⊕a=0。
     * 异或运算满足交换律和结合律，即 a⊕b⊕a=b⊕a⊕a=b⊕(a⊕a)=b⊕0=b。
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }

}

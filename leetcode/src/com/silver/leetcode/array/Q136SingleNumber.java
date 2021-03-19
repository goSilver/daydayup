package com.silver.leetcode.array;

/**
 * 只出现一次的数字
 *
 * @author csh
 * @date 2021/3/19
 */
public class Q136SingleNumber {

    /**
     * 思路：
     * 本题可以借助额外空间来做。
     * 也可以借助异或运算思想来做，题目描述中重点有2m+1个元素
     *
     * 异或运算有以下三个性质。
     *
     * 任何数和 00 做异或运算，结果仍然是原来的数，即 a \oplus 0=aa⊕0=a。
     * 任何数和其自身做异或运算，结果是 00，即 a \oplus a=0a⊕a=0。
     * 异或运算满足交换律和结合律，即 a \oplus b \oplus a=b \oplus a \oplus a=b \oplus (a \oplus a)=b \oplus0=ba⊕b⊕a=b⊕a⊕a=b⊕(a⊕a)=b⊕0=b。
     *
     *
     * @param nums 数组
     * @return 只出现一次的元素
     */
    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }
}

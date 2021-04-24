package com.silver.gray.chapter5.d;

/**
 * 判断一个数是否是2的整数次幂
 * @author csh
 * @date 2021/4/24
 */
public class Power {

    /**
     * 判断一个数是否是2的整数次幂
     * 思路：
     * 如果一个数是2的整数次幂，则这个数的二进制一定是10000...
     * 且这个数减1的数的二进制一定是11111...
     * 两个二进制数做一个与运算，结果为0时，表示这个数是2的整数次幂
     *
     * @param num
     * @return
     */
    private boolean isPowerOf2(int num) {
        return (num & num - 1) == 0;
    }
}

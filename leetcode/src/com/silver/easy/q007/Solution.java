package com.silver.easy.q007;

/**
 * 整数反转
 *
 * @author csh
 * @date 2019/9/22 14:35
 */
public class Solution {

    public int reverse(int x) {
        long res = 0;
        // 十进制
        int decimal = 10;
        for (; x != 0; x /= decimal){
            res = res * 10 + x % 10;
        }
        return res > Integer.MAX_VALUE || res < Integer.MIN_VALUE ? 0 : (int) res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.reverse(123));
        System.out.println(solution.reverse(-123));
        System.out.println(solution.reverse(100));
        System.out.println(solution.reverse(1000000003));
        System.out.println(123%10);
    }
}

package com.silver.easy.q009;

/**
 * @author csh
 * @date 2019/9/26 11:42
 */
public class Solution {
    private boolean isPalindrome(int x) {
        // 保存参数x
        int temp = x;
        // 作反转后的数值
        int res = 0;
        // 如果小于零，直接返回false；大于零才作反转
        if (x >= 0) {
            for (; x != 0; x /= 10) {
                res = res * 10 + x % 10;
            }
            return res == temp;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean res = solution.isPalindrome(121);
        System.out.print(res);
    }
}

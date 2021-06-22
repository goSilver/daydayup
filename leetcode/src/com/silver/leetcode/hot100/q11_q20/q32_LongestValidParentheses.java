package com.silver.leetcode.hot100.q11_q20;

/**
 * 最长有效括号
 * @author csh
 * @date 2021/6/22
 **/
public class q32_LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int left = 0, right = 0, res = 0;
        // 先从左往右遍历，遇到左括号left加1，遇到右括号right加1
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            // 当left等于right时，计算长度
            if (left == right) {
                res = Math.max(res, 2 * right);
                // 如果right大于left时，left、right归零
            } else if (right > left) {
                left = right = 0;
            }
        }
        // 计数归零，从尾到头再来一遍，处理边界情况
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                res = Math.max(res, 2 * right);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return res;
    }
}

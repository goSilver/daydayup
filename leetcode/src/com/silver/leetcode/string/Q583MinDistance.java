package com.silver.leetcode.string;

/**
 * 两个字符串的删除操作
 * 思路：
 * 题目让我们计算将两个字符串变得相同的最少删除次数，那我们可以思考一下，最后这两个字符串会被删成什么样子？
 * 删除的结果不就是它俩的最长公共子序列嘛！
 *
 * @author csh
 * @date 2021/4/5
 */
public class Q583MinDistance {

    private Q1143LongestCommonSubsequence helper = new Q1143LongestCommonSubsequence();

    public int minDistance(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        // 复用前文计算 lcs 长度的函数
        int lcs = helper.longestCommonSubsequence(s1, s2);
        return m - lcs + n - lcs;
    }
}

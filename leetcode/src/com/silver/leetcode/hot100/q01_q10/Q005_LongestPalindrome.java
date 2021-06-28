package com.silver.leetcode.hot100.q01_q10;

/**
 * @author csh
 * @date 2021/5/30
 **/
public class Q005_LongestPalindrome {

    /**
     * 最长回文字串
     * 思路：中心扩展算法
     * 时间复杂度：O(n^2)
     *
     * @param s 字符串
     * @return 最长回文串
     */
    public String longestPalindrome(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            String s1 = palindrome(s, i, i);
            String s2 = palindrome(s, i, i + 1);

            res = s1.length() > res.length() ? s1 : res;
            res = s2.length() > res.length() ? s2 : res;
        }
        return res;
    }

    private String palindrome(String str, int l, int r) {
        while (l >= 0 && r < str.length() && str.charAt(l) == str.charAt(r)) {
            l--;
            r++;
        }
        return str.substring(l + 1, r);
    }

    public static void main(String[] args) {
        String s = "babad";
        Q005_LongestPalindrome main = new Q005_LongestPalindrome();
        String s1 = main.longestPalindrome(s);
        System.out.println(s1);
    }
}

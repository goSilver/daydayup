package com.silver.leetcode.hot100.review.first;

/**
 * @author csh
 * @date 2021/7/3
 **/
public class q05_longestPalindrome {
    public String longestPalindrome(String s) {
        String res = "";
        if (s.length() == 0) return res;
        for (int i = 0; i < s.length(); i++) {
            String a = palindrome(s, i, i);
            String b = palindrome(s, i, i + 1);
            res = res.length() > a.length() ? res : a;
            res = res.length() > b.length() ? res : b;
        }
        return res;
    }

    private String palindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left + 1, right);
    }
}

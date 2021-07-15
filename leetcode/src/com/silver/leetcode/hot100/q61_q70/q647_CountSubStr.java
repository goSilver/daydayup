package com.silver.leetcode.hot100.q61_q70;

/**
 * 回文子串
 *
 * @author csh
 * @date 2021/7/3
 **/
public class q647_CountSubStr {
    private int res;

    public int countSubstrings(String s) {
        if (s.length() == 0) return 0;
        for (int i = 0; i < s.length(); i++) {
            palindrome(s, i, i);
            palindrome(s, i, i + 1);
        }
        return res;
    }

    private void palindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            res++;
            left--;
            right++;
        }
    }

    public static void main(String[] args) {
        q647_CountSubStr main = new q647_CountSubStr();
        int abc = main.countSubstrings("aaa");
        System.out.println(abc);
    }
}

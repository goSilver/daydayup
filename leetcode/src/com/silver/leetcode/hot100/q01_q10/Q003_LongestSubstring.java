package com.silver.leetcode.hot100.q01_q10;

import java.util.HashMap;

/**
 * @author csh
 * @date 2021/5/30
 **/
public class Q003_LongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> window = new HashMap<>();
        int res = Integer.MIN_VALUE;
        int left = 0, right = 0;
        while (right < s.length()) {
            char c1 = s.charAt(right);
            right++;
            window.put(c1, window.getOrDefault(c1, 0) + 1);
            // while!!! 判断是否应该收缩左边窗口
            while (window.get(c1) > 1) {
                char c2 = s.charAt(left);
                left++;
                window.put(c2, window.getOrDefault(c2, 0) - 1);
            }
            res = Math.max(res, right - left);
        }
        return res;
    }
}

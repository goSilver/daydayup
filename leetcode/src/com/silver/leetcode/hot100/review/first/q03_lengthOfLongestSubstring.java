package com.silver.leetcode.hot100.review.first;

import java.util.HashMap;

/**
 * @author csh
 * @date 2021/7/3
 **/
public class q03_lengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0;
        int res = 0;
        while (right < s.length()) {
            char c1 = s.charAt(right);
            right++;
            window.put(c1, window.getOrDefault(c1, 0) + 1);
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

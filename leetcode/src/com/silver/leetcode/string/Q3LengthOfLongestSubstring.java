package com.silver.leetcode.string;

import java.util.HashMap;

/**
 * 最长无重复字串
 *
 * @author csh
 * @date 2021/3/12
 **/
public class Q3LengthOfLongestSubstring {

    private int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0;
        int res = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            window.put(c, window.getOrDefault(c, 0) + 1);
            right++;
            // 判断是否应该收缩左边窗口
            while (window.get(c) > 1) {
                char c1 = s.charAt(left);
                left++;
                window.put(c1, window.getOrDefault(c1, 0) - 1);
            }
            res = Math.max(res, right - left);
        }
        return res;
    }
}

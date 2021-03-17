package com.silver.leetcode.string;

import java.util.HashMap;

/**
 * 最长无重复字串
 *
 * @author csh
 * @date 2021/3/12
 **/
public class Q3LengthOfLongestSubstring {

    /**
     * 滑动窗口算法，不断向右尝试，寻找所有符合条件的窗口，并记录最大窗口
     *
     * @param s 字符串s
     * @return 最长无重复的长度
     */
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

package com.silver.labuladong.catalog.chapter1.doublepointer.window;

import java.util.HashMap;

/**
 * 最长无重复子串
 * @author csh
 * @date 2021/5/1
 */
public class LengthOfLongestSubstring {
    private Integer lengthOfLongestSubstring(String s) {
        // 定义窗口
        HashMap<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0;
        int res = 0;
        while (right < s.length()) {
            // 向右扩大窗口
            char c = s.charAt(right);
            right++;
            window.put(c, window.getOrDefault(c, 0) + 1);
            // 当出现重复字符，缩小窗口
            if (window.get(c) > 1) {
                char c1 = s.charAt(left);
                left++;
                window.put(c1, window.getOrDefault(c, 0) - 1);
            }
            // 在这里更新答案
            res = Math.max(res, right - left);
        }
        return res;
    }
}

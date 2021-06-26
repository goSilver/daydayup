package com.silver.leetcode.hot100.q21_q30;

import java.util.HashMap;

/**
 * 最小覆盖子串
 *
 * @author csh
 * @date 2021/6/26
 **/
public class q76_MinWindow {
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> window = new HashMap<>();
        HashMap<Character, Integer> memo = new HashMap<>();
        char[] chars = t.toCharArray();
        for (char aChar : chars) {
            memo.put(aChar, memo.getOrDefault(aChar, 0) + 1);
        }
        int left = 0, right = 0;
        int valid = 0;
        int start = 0, len = Integer.MAX_VALUE;
        while (right < s.length()) {
            char c1 = s.charAt(right);
            right++;
            if (memo.containsKey(c1)) {
                window.put(c1, window.getOrDefault(c1, 0) + 1);
                if (memo.get(c1).equals(window.get(c1))) valid++;
            }

            while (valid == memo.size()) {
                if (len > right - left) {
                    start = left;
                    len = right - left;
                }

                char c2 = s.charAt(left);
                left++;
                if (memo.containsKey(c2)) {
                    if (memo.get(c1).equals(window.get(c1))) valid--;
                    window.put(c2, window.getOrDefault(c2, 0) - 1);
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, len);
    }
}

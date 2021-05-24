package com.silver.labuladong.important.chapter1.window;

import java.util.HashMap;

/**
 * 最小覆盖字串
 *
 * @author csh
 * @date 2021/5/25
 **/
public class MinSubString {
    private String minWindow(String s, String t) {
        HashMap<Character, Integer> window = new HashMap<>();
        HashMap<Character, Integer> need = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            need.put(t.charAt(i), need.getOrDefault(t.charAt(i), 0) + 1);
        }

        int left = 0, right = 0;
        int start = 0, len = Integer.MAX_VALUE;
        int valid = 0;
        while (right < s.length()) {
            char ch = s.charAt(right);
            right++;
            if (need.containsKey(ch)) {
                window.put(ch, window.getOrDefault(ch, 0) + 1);
                if (window.get(ch).equals(need.get(ch))) valid++;
            }

            while (valid == need.size()) {
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }

                char ch2 = s.charAt(left);
                left++;
                if (need.containsKey(ch2)) {
                    // 和上面put的时候顺序相反
                    if (window.get(ch2).equals(need.get(ch2))) valid--;
                    window.put(ch2, window.get(ch2) - 1);
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }
}

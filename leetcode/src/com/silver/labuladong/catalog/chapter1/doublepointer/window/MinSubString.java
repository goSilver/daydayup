package com.silver.labuladong.catalog.chapter1.doublepointer.window;

import java.util.HashMap;

/**
 * @author csh
 * @date 2021/5/1
 */
public class MinSubString {

    /**
     * 最小覆盖子串
     *
     * @param s 串s
     * @param t 串t
     * @return 最小覆盖子串
     */
    private String minWindow(String s, String t) {
        // 窗口和need
        HashMap<Character, Integer> window = new HashMap<>();
        HashMap<Character, Integer> need = new HashMap<>();
        // 把串t中的字符都放入need
        for (char c : t.toCharArray())
            need.put(c, need.getOrDefault(c, 0) + 1);

        // 定义窗口用的左右指针
        int left = 0, right = 0;
        // window中满need条件的字符个数
        int valid = 0;
        // 记录最小覆盖子串的起始索引和长度
        int start = 0, len = Integer.MAX_VALUE;
        while (right < s.length()) {
            // 1、向右扩大窗口
            char c = s.charAt(right);
            right++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) valid++;
            }
            // 2、当valid等于need的size时，缩小左侧窗口
            if (valid == need.size()) {
                // 寻找最优解的控制
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }

                // 左侧缩小窗口
                char c1 = s.charAt(left);
                left++;
                if (need.containsKey(c1)) {
                    if (need.get(c1).equals(window.get(c1))) valid--;
                    window.put(c1, window.getOrDefault(c1, 0) - 1);
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }
}

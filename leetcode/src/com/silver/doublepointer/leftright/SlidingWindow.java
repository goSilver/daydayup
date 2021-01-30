package com.silver.doublepointer.leftright;

import java.util.HashMap;

/**
 * 双指针之滑动窗口技巧
 *
 * @author csh
 * @date 2021/01/30
 **/
public class SlidingWindow {

    public static void main(String[] args) {
        SlidingWindow slidingWindow = new SlidingWindow();
        String s = slidingWindow.minWindow("ADOBECODEBANC", "ABC");
        System.out.println(s);
    }

    /**
     * 最小覆盖字串
     * https://leetcode-cn.com/problems/minimum-window-substring/
     *
     * @param s 字符串s
     * @param t 字符串t
     * @return 最小覆盖字串
     */
    private String minWindow(String s, String t) {
        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();
        for (char c :  t.toCharArray()) need.put(c, need.getOrDefault(c, 0) + 1);

        int left = 0, right = 0;
        int valid = 0;
        // 记录最小覆盖字串的起始索引及长度
        int start = 0, len = Integer.MAX_VALUE;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            // 判断取出的字符是否在字串中
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c,0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }

            // 判断是否需要收缩（已经找到合适的覆盖串）
            while (valid == need.size()) {
                // 寻找最优解的控制
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }

                char c1 = s.charAt(left);
                left++;
                if (need.containsKey(c1)) {
                    if (window.get(c1).equals(need.get(c1))) {
                        valid--;
                    }
                    window.put(c1, window.getOrDefault(c1, 0) - 1);
                }

            }
        }

        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

    /**
     * 字符串排列
     * https://leetcode-cn.com/problems/permutation-in-string/
     *
     * @param s 字符串s
     * @param t 字符串t
     * @return s中是否存在t的排列
     */
    private boolean checkInclusion(String s, String t) {
        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int valid = 0;

        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            // 进行窗口内一系列数据的更新
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);

                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }

            // 判断左侧窗口是否需要收缩
            while (right - left >= t.length()) {

                // 判断是否找到合法字串
                if (valid == need.size()) return true;

                char c1 = s.charAt(left);
                left++;
                // 进行窗口内一系列数据更新
                if (need.containsKey(c1)) {
                    window.put(c1, window.getOrDefault(c1, 0)-1);
                    if (window.get(c1).equals(need.get(c1))) {
                        valid--;
                    }
                }
            }
        }
        // 未找到合法字串
        return false;
    }
}

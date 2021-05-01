package com.silver.labuladong.catalog.chapter1.doublepointer.window;

import java.util.HashMap;

/**
 * 字符串的排列
 *
 * @author csh
 * @date 2021/5/1
 */
public class CheckInclusion {
    private boolean checkInclusion(String s, String t) {
        // 定义窗口和need
        HashMap<Character, Integer> window = new HashMap<>();
        HashMap<Character, Integer> need = new HashMap<>();
        // 把t中的字符都放入need
        for (char c : t.toCharArray())
            need.put(c, need.getOrDefault(c, 0) + 1);
        // 定义控制窗口大小的变量
        int left =0 ,right =0;
        // window中符合need条件的字符个数
        int valid = 0;
        while (right < s.length()) {
            // 向右扩大窗口
            char c = s.charAt(right);
            right++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) valid++;
            }

            // 注意循环条件。缩小窗口
            if (right - left >= t.length()) {
                // 已找到符合条件的字符个数
                if (valid == t.length()) return true;
                // 缩小窗口
                char c1 = s.charAt(left);
                left++;
                if (need.containsKey(c1)) {
                    if (window.get(c1).equals(need.get(c1))) valid--;
                    window.put(c1, window.getOrDefault(c1, 0) - 1);
                }
            }
        }
        return false;
    }
}

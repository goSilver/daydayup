package com.silver.labuladong.catalog.chapter1.doublepointer.window;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 寻找所有字母的异位词
 *
 * @author csh
 * @date 2021/5/1
 */
public class FindAnagrams {
    private List<Integer> findAnagrams(String s, String t) {
        HashMap<Character, Integer>
                window = new HashMap<>(),
                need = new HashMap<>();
        for (char c : t.toCharArray())
            need.put(c, need.getOrDefault(c, 0) + 1);

        int left = 0, right = 0;
        int valid = 0;
        // 存储结果集
        List<Integer> res = new LinkedList<>();
        while (right < s.length()) {
            // 向右扩大窗口
            char c = s.charAt(right);
            right++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (need.get(c).equals(window.get(c))) valid++;
            }

            // 注意循环条件。缩小窗口
            while (right - left > t.length()) {
                // 找到符合条件的串，记录索引
                if (valid == need.size()) res.add(left);
                char c1 = s.charAt(left);
                left++;
                if (need.containsKey(c1)) {
                    if (need.get(c1).equals(window.get(c1))) valid--;
                    window.put(c1, window.getOrDefault(c1, 0) - 1);
                }
            }
        }
        return res;
    }
}

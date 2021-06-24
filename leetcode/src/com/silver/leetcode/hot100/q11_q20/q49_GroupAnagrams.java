package com.silver.leetcode.hot100.q11_q20;

import java.util.*;

/**
 * 字母异位词分组
 * @author csh
 * @date 2021/6/24
 **/
public class q49_GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            // 排序后，异位词都会变成一样
            Arrays.sort(chars);
            String key = new String(chars);
            // 放入map
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }
}

package com.silver.leetcode.hot100.q01_q10;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author csh
 * @date 2021/5/30
 **/
public class Q020_IsValid {

    private static final Map<Character, Character> map = new HashMap<Character, Character>() {{
        put('{', '}');
        put('[', ']');
        put('(', ')');
        put('?', '?');
    }};

    public boolean isValid(String s) {
        if (s.length() > 0 && !map.containsKey(s.charAt(0))) return false;
        LinkedList<Character> list = new LinkedList<Character>() {{
            add('?');
        }};
        for (Character c : s.toCharArray()) {
            if (map.containsKey(c)) {
                list.addLast(c);
            } else if (map.get(list.removeLast()) != c) {
                return false;
            }
        }
        return list.size() == 1;
    }

}

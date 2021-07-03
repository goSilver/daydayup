package com.silver.leetcode.hot100.review.first;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author csh
 * @date 2021/7/3
 **/
public class q20_isValid {

    HashMap<Character, Character> map = new HashMap<Character, Character>() {
        {
            put('(', ')');
            put('{', '}');
            put('[', ']');
            put('?', '?');
        }
    };

    public boolean isValid(String s) {
        if (!map.containsKey(s.charAt(0))) return false;
        LinkedList<Character> list = new LinkedList<Character>(){{add('?');}};
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                list.add(c);
            } else if (map.get(list.removeLast()) != c){
                return false;
            }
        }
        return list.size() == 1;
    }
}

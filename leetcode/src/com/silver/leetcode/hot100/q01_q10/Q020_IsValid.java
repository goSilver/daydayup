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

    /*
     * 解决边界问题：
     * 栈 stack 为空： 此时 stack.pop() 操作会报错；因此，我们采用一个取巧方法，给 stack 赋初值 ? ，并在哈希表 dic 中建立 key: '?'，value:'?'的对应关系予以配合。此时当 stack 为空且 c 为右括号时，可以正常提前返回 false；
     * 字符串 s 以左括号结尾：此情况下可以正常遍历完整个 s，但 stack 中遗留未出栈的左括号；因此，最后需返回 len(stack) == 1，以判断是否是有效的括号组合。
     */
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

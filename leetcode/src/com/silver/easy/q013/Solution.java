package com.silver.easy.q013;

import java.util.HashMap;
import java.util.Map;

/**
 * 罗马数字转整数
 *
 * @author csh
 * @date 2019/9/26 15:13
 */
public class Solution {

    private int romanToInt(String s) {
        // 用hashMap来存储罗马字符与整型数字的关系
        Map<Character, Integer> map = new HashMap<>(7);
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            int currentVal = map.get(s.charAt(i));
            int nextVal = i < s.length() - 1 ? map.get(s.charAt(i + 1)) : 0;
            // 如果当前下标的值小于下一个下标的值，则对当前值做减法，如果当前值大于下一个值，则直接加法
            res += currentVal < nextVal ? -currentVal : currentVal;
        }
        return res;
    }

    public static void main(String[] args) {
        // LVIII = 58
        String roman = "LVIII";
        Solution solution = new Solution();
        int res = solution.romanToInt(roman);
        System.out.print(roman + "转换为整形的结果为：" + res);
    }
}

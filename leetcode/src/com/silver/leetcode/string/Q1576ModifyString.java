package com.silver.leetcode.string;

/**
 * 替换所有的问号
 *
 * @author csh
 * @date 2021/3/16
 **/
public class Q1576ModifyString {

    public String modifyString(String s) {
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            // 找到问号的位置
            if (chars[i] == '?'){
                // 如果问号是第一个字符，则ahead为空
                char ahead = i == 0 ? ' ' : chars[i-1];
                // 如果问号是最后一个字符，则behind为空
                char behind = i == chars.length - 1 ? ' ' : chars[i+1];

                // 从a开始比较  如果等于前面或者后面的话 就+1
                char temp = 'a';
                while (temp == ahead || temp == behind) {
                    temp++;
                }
                chars[i] = temp;
            }
        }
        return new String(chars);
    }

}

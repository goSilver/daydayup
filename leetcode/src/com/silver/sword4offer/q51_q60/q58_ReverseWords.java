package com.silver.sword4offer.q51_q60;

/**
 * 翻转单词顺序
 * @author csh
 * @date 2021/6/20
 **/
public class q58_ReverseWords {

    public String reverseWords(String s) {
        s = s.trim(); // 删除首尾空格
        int j = s.length() - 1, i = j;
        StringBuilder res = new StringBuilder();
        while(i >= 0) {
            while(i >= 0 && s.charAt(i) != ' ') i--; // 搜索首个空格
            res.append(s, i + 1, j + 1).append(" "); // 添加单词
            while(i >= 0 && s.charAt(i) == ' ') i--; // 跳过单词间空格
            j = i; // j 指向下个单词的尾字符
        }
        return res.toString().trim(); // 转化为字符串并返回
    }

    /**
     * 左旋转字符串
     * @param s
     * @param n
     * @return
     */
    public String reverseLeftWords(String s, int n) {
        return s.substring(n, s.length()) + s.substring(0, n);
    }

}

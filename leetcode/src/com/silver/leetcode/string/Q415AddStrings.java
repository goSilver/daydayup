package com.silver.leetcode.string;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 字符串相加
 *
 * @author csh
 * @date 2021/3/13
 **/
public class Q415AddStrings {
    public static void main(String[] args) {
        Q415AddStrings main = new Q415AddStrings();
        String s = main.addStrings("6", "6");
        System.out.println(s);
    }

    public String addStrings(String num1, String num2) {
        Deque<Integer> num1Stack = new LinkedList<>();
        Deque<Integer> num2Stack = new LinkedList<>();
        for (int i = 0; i < num1.length(); i++) {
            num1Stack.push(Character.getNumericValue(num1.charAt(i)));
        }
        for (int i = 0; i < num2.length(); i++) {
            num2Stack.push(Character.getNumericValue(num2.charAt(i)));
        }

        StringBuilder res = new StringBuilder();
        int carry = 0;

        // 注意：用“或”和carry!=0
        while (!num1Stack.isEmpty() || !num2Stack.isEmpty() || carry != 0) {
            int a = num1Stack.isEmpty() ? 0 : num1Stack.pop();
            int b = num2Stack.isEmpty() ? 0 : num2Stack.pop();
            int sum = a + b + carry;

            carry = sum / 10;

            sum = sum % 10;
            res.append(sum);
        }

        // 反转一下
        return res.reverse().toString();
    }
}

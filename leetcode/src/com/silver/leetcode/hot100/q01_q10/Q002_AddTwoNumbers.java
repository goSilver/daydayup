package com.silver.leetcode.hot100.q01_q10;

import com.silver.labuladong.temp.ListNode;

import java.util.Stack;

/**
 * @author csh
 * @date 2021/5/30
 **/
public class Q002_AddTwoNumbers {

    private ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }
        ListNode res = new ListNode(0);
        ListNode cur = res;
        int carry = 0;
        while (!s1.isEmpty() || !s2.isEmpty() || carry != 0) {
            int a = s1.isEmpty() ? 0 : s1.pop();
            int b = s2.isEmpty() ? 0 : s2.pop();
            int sum = a + b + carry;
            carry = sum / 10;
            sum %= 10;
            cur.next = new ListNode(sum);
            cur = cur.next;
        }
        return res.next;
    }
}

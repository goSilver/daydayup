package com.silver.leetcode.hot100.q01_q10;

import com.silver.labuladong.temp.ListNode;

import java.util.Stack;

/**
 * @author csh
 * @date 2021/5/30
 **/
public class Q002_AddTwoNumbers {
    public static void main(String[] args) {
        ListNode a = new ListNode(2);
        ListNode b = new ListNode(4);
        ListNode c = new ListNode(9);
        ListNode x = new ListNode(5);
        ListNode y = new ListNode(6);
        ListNode z = new ListNode(4);
        ListNode z1 = new ListNode(9);
        a.next = b;
        b.next = c;

        x.next = y;
        y.next = z;
        z.next = z1;
        Q002_AddTwoNumbers add = new Q002_AddTwoNumbers();
        ListNode res = add.addTwoNumbersCycle(a, x);
        while (res != null) {
            System.out.print(res.val);
            res = res.next;
        }
    }

    /**
     * 栈
     * 该解法只适用于两个链表是 *正序* 存储的数字的情况
     *
     * @param l1 链表1
     * @param l2 链表2
     * @return 和
     */
    private ListNode addTwoNumbersStack(ListNode l1, ListNode l2) {
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
        ListNode res = null;
        int carry = 0;
        while (!s1.isEmpty() || !s2.isEmpty() || carry != 0) {
            int a = s1.isEmpty() ? 0 : s1.pop();
            int b = s2.isEmpty() ? 0 : s2.pop();
            int sum = a + b + carry;
            carry = sum / 10;
            sum %= 10;
            ListNode cur = new ListNode(sum);
            cur.next = res;
            res = cur;
        }
        return res;
    }

    /**
     * 循环
     * 该解法适用于 *逆序* 存储的链表
     *
     * @param l1 链表1
     * @param l2 链表2
     * @return 和
     */
    private ListNode addTwoNumbersCycle(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        ListNode cur = res;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int a = l1 == null ? 0 : l1.val;
            int b = l2 == null ? 0 : l2.val;
            int sum = a + b + carry;
            carry = sum / 10;
            sum %= 10;
            cur.next = new ListNode(sum);
            cur = cur.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return res.next;
    }
}

package com.silver.leetcode.linkedlist;

import com.silver.labuladong.temp.ListNode;

/**
 * 两数相加
 * 此题和445题的不同之处在于，这道题给的两个链表是正序的；
 * 而445题给的链表是倒序的，所以需要借助栈，或者先执行一边反转链表来协助解题
 *
 * @author csh
 * @date 2021/2/24
 **/
public class q2AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int a = l1 == null ? 0 : l1.val;
            int b = l2 == null ? 0 : l2.val;
            int sum = a + b + carry;
            carry = sum / 10;
            sum %= 10;

            if (head == null) {
                head = tail = new ListNode(sum);
            } else {
                tail.next = new ListNode(sum);
                tail = tail.next;
            }

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }
}

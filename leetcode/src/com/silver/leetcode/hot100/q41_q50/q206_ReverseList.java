package com.silver.leetcode.hot100.q41_q50;

import com.silver.labuladong.temp.ListNode;

/**
 * @author csh
 * @date 2021/6/27
 **/
public class q206_ReverseList {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode last = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    public ListNode reverse(ListNode head) {
        if (head == null) return null;
        ListNode pre = null;
        ListNode cur = head, nxt = head;
        while (cur != null) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

}

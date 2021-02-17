package com.silver.sword4offer.q21_q30;

import com.silver.labuladong.temp.ListNode;

/**
 * 反转链表
 *
 * @author csh
 * @date 2021/2/17
 **/
public class q24 {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode last = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

}

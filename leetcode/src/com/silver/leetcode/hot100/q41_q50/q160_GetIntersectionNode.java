package com.silver.leetcode.hot100.q41_q50;

import com.silver.labuladong.temp.ListNode;

/**
 * 相交链表
 *
 * @author csh
 * @date 2021/6/27
 **/
public class q160_GetIntersectionNode {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode a = headA, b = headB;
        while (a != b) {
            a = a != null ? a.next : b.next;
            b = b != null ? b.next : a.next;
        }
        return a;
    }
}

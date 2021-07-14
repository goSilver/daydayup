package com.silver.leetcode.hot100.review.first;

import com.silver.labuladong.temp.ListNode;

/**
 * 相交链表
 *
 * @author csh
 * @date 2021/7/14
 **/
public class q160_getIntersectionNode {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode a = headA, b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }
}

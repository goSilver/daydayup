package com.silver.sword4offer.q51_q60;

import com.silver.labuladong.temp.ListNode;

/**
 * 两个链表的第一个公共节点
 * @author csh
 * @date 2021/6/20
 **/
public class q52_GetIntersectionNode {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode A = headA, B = headB;
        while (A != B) {
            A = A != null ? A.next : headB;
            B = B != null ? B.next : headA;
        }
        return A;
    }

}

package com.silver.review.linkedlist;

import com.silver.labuladong.temp.ListNode;

/**
 * 合并两个有序链表
 *
 * @author csh
 * @date 2021/3/28
 */
public class MergeTwoList {

    public ListNode merge(ListNode n1, ListNode n2) {
        ListNode pre, cur;
        ListNode a = new ListNode(0);
        pre = cur = a;
        while (n1 != null && n2 != null) {
            if (n1.val > n2.val) {
                cur.next = n2;
                n2 = n2.next;
            } else {
                cur.next = n1;
                n1 = n1.next;
            }
            cur = cur.next;
        }

        // 处理剩下节点
        cur.next = n1 == null ? n2 : n1;
        return pre.next;
    }
}

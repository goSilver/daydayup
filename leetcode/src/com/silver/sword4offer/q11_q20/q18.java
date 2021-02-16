package com.silver.sword4offer.q11_q20;

import com.silver.labuladong.temp.ListNode;

/**
 * 删除链表的节点
 *
 * @author csh
 * @date 2021/2/16
 **/
public class q18 {
    public ListNode deleteNode(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = head;
        ListNode prev = dummy;
        while (curr != null) {
            if (curr.val == val) {
                prev.next = curr.next;
                break;
            }
            prev = curr;
            curr = curr.next;
        }
        return dummy.next;
    }
}

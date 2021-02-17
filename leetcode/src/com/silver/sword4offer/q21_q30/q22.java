package com.silver.sword4offer.q21_q30;

import com.silver.labuladong.temp.ListNode;

/**
 * 链表中倒数第k个节点
 *
 * @author csh
 * @date 2021/2/17
 **/
public class q22 {
    public ListNode getKthFromEnd(ListNode head, int k) {
        int length = 0;
        ListNode second = head;
        while (head != null) {
            length++;
            head = head.next;
        }
        int index = length - k;
        while (index > 0) {
            second = second.next;
            index--;
        }
        return second;
    }
}

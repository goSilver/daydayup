package com.silver.review.linkedlist;

import com.silver.labuladong.temp.ListNode;

/**
 * 两两交换，同K个一组反转当K=2时的情况
 *
 * @author csh
 * @date 2021/3/28
 */
public class SwapPairs {

    public ListNode swapPairs(ListNode head) {
        if (head == null) return null;
        ListNode res = new ListNode(0);
        res.next = head;
        ListNode pre = res;
        while (head != null && head.next != null) {

            ListNode temp = head.next;
            pre.next = head.next;
            head.next = head.next.next;
            temp.next = head;

            pre = head;
            head = head.next;

        }
        return res.next;
    }
}

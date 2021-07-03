package com.silver.leetcode.hot100.review.first;

import com.silver.labuladong.temp.ListNode;

/**
 * @author csh
 * @date 2021/7/3
 **/
public class q19_removeN {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy, fast = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

}

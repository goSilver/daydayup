package com.silver.leetcode.hot100.q41_q50;

import com.silver.labuladong.temp.ListNode;

/**
 * 寻找环起点
 *
 * @author csh
 * @date 2021/6/26
 **/
public class q142_Cycle {

    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        do {
            if (fast == null || fast.next == null) return null;
            slow = slow.next;
            fast = fast.next.next;
        } while (slow != fast);

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}

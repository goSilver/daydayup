package com.silver.labuladong.catalog.chapter1.doublepointer;

import com.silver.labuladong.temp.ListNode;

/**
 * @author csh
 * @date 2021/5/1
 */
public class SlowFast {
    private boolean hasCycle(ListNode head) {
        ListNode slow, fast;
        slow = fast = head;
        // 注意，是两个判断fast指针
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    private ListNode detectCycle(ListNode head) {
        ListNode slow, fast;
        slow = fast = head;
        // 注意，是两个判断fast指针
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) break;
        }
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}

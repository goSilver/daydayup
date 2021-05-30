package com.silver.leetcode.hot100.q01_q10;

import com.silver.labuladong.temp.ListNode;

/**
 * @author csh
 * @date 2021/5/30
 **/
public class Q019_RemoveNthFromEnd {

    /**
     * 删除链表倒数第k个节点
     * 思路：快慢指针，慢指针需要从-1开始，
     *
     * @param head
     * @param n
     * @return
     */
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

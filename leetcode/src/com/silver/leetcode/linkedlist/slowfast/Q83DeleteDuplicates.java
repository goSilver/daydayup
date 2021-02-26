package com.silver.leetcode.linkedlist.slowfast;

import com.silver.labuladong.temp.ListNode;

/**
 * 删除排序链表中的重复元素
 *
 * @author csh
 * @date 2021/2/26
 **/
public class Q83DeleteDuplicates {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;

        ListNode fast = head, slow = head;
        while (fast != null) {
            if (fast.val != slow.val) {
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }
        slow.next = null;
        return head;
    }
}

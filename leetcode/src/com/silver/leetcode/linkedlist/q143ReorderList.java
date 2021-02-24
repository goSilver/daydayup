package com.silver.leetcode.linkedlist;

import com.silver.labuladong.temp.ListNode;


/**
 * 重排链表
 *
 * @author csh
 * @date 2021/2/24
 **/
public class q143ReorderList {
    public void reorderList(ListNode head) {
        // base case
        if (head == null) return;

        // 1、找到中点
        ListNode middle = findMiddle(head);

        // 2、切短成前、后两个链表
        ListNode middleNext = middle.next;
        middle.next = null;

        // 3、反转后链表
        ListNode last = reverse(middleNext);

        // 4、合并两个链表
        merge(head, last);
    }

    private void merge(ListNode l1, ListNode l2) {
        // 注意
        ListNode l1Temp;
        ListNode l2Temp;
        while (l1 != null && l2 != null) {
            l1Temp = l1.next;
            l2Temp = l2.next;

            l1.next = l2;
            l1 = l1Temp;

            l2.next = l1;
            l2 = l2Temp;
        }

    }

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        // 注意
        ListNode last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    private ListNode findMiddle(ListNode head) {
        ListNode fast = head, slow = head;
        // 注意
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}

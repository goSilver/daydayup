package com.silver.leetcode.linkedlist;

import com.silver.labuladong.temp.ListNode;

/**
 * 两两交换链表中的节点：
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * @author csh
 * @date 2021/2/23
 **/
public class q24SwapPairs {
    public ListNode swapPairs(ListNode head) {
        if (head == null) return null;
        ListNode ans = new ListNode(0);
        ans.next =head;
        // 辅助节点，用来链接两对节点
        ListNode pre = ans;
        while (head != null && head.next != null) {
            ListNode temp = head.next;
            pre.next = head.next;
            head.next = head.next.next;
            temp.next = head;

            pre = head;
            head = head.next;
        }
        return ans.next;
    }
}

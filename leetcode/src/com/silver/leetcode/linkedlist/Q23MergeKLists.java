package com.silver.leetcode.linkedlist;

import com.silver.labuladong.temp.ListNode;

/**
 * 合并k个有序链表
 * 思路1：暴力循环，两个两个合并
 * 思路2：分治合并
 *
 * @author csh
 * @date 2021/4/5
 **/
public class Q23MergeKLists {

    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    /**
     * 归并
     *
     * @param lists
     * @param l
     * @param r
     * @return
     */
    private ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) return lists[l];
        if (l > r) return null;
        int mid = (l + r) >> 1;
        return mergeTwoList(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    private ListNode mergeTwoList(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a == null ? b : a;
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (a != null && b != null) {
            if (a.val > b.val) {
                tail.next = b;
                b = b.next;
            } else {
                tail.next = a;
                a = a.next;
            }
            tail = tail.next;
        }
        tail.next = a == null ? b : a;
        return head.next;
    }
}

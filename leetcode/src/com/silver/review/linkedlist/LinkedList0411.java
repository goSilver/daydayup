package com.silver.review.linkedlist;

import com.silver.labuladong.temp.ListNode;

/**
 * @author csh
 * @date 2021/4/11
 */
public class LinkedList0411 {
    public ListNode reverse(ListNode head) {
        if (head == null) return null;
        ListNode last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    public ListNode reverseByInteration(ListNode head) {
        if (head == null) return null;
        ListNode pre = null;
        ListNode cur = head, nxt;
        while (cur != null) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

    private ListNode successor;

    public ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            successor = head.next;
            return successor;
        }
        ListNode last = reverseN(head.next, n - 1);
        head.next.next = head;
        head.next = successor;
        return last;
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == 1) {
            reverseN(head, n);
        }
        reverseBetween(head.next, m - 1, n - 1);
        return head;
    }

    public ListNode reverseBetween(ListNode a, ListNode b) {
        if (a == null) return null;
        ListNode pre = null;
        ListNode cur = a, nxt;
        while (cur != b) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode a = head, b = head;
        for (int i = 0; i < k; i++) {
            if (b == null) return null;
            b = b.next;
        }
        ListNode newHead = reverseBetween(a, b);
        a.next = reverseKGroup(b, k);
        return newHead;
    }

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

    public ListNode rotateRight(ListNode head, int k) {
        ListNode tail = head;
        int n = 1;
        while (tail.next != null) {
            tail = tail.next;
            n++;
        }
        tail.next = head;

        for (int i = 0; i < n - k % n - 1; i++) {
            head = head.next;
        }
        ListNode newTail = head;
        ListNode newHead = head.next;
        newTail.next = null;
        return newHead;
    }

    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        ListNode res = new ListNode(0);
        while (a != null && b != null) {
            if (a.val < b.val) {
                res.next = a;
                a = a.next;
            } else {
                res.next = b;
                b = b.next;
            }
            res = res.next;
        }

        res.next = a != null ? a : b;
        return res.next;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) return lists[l];
        if (l > r) return null;
        int mid = (l + r) / 2;
        return mergeTwoLists(merge(lists, 0, mid), merge(lists, mid + 1, r));
    }
}

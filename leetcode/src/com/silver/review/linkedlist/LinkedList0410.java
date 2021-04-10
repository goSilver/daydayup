package com.silver.review.linkedlist;

import com.silver.labuladong.temp.ListNode;

/**
 * 链表操作
 *
 * @author csh
 * @date 2021/4/10
 */
public class LinkedList0410 {
    public ListNode reverseByRecurse(ListNode head) {
        if (head == null || head.next == null) return head;
        // 递归
        ListNode last = reverseByRecurse(head.next);
        // 反转
        head.next.next = head;
        head.next = null;
        return last;
    }

    public ListNode reverseByIteration(ListNode head) {
        if (head == null) return null;
        ListNode pre = null;
        ListNode cur = head, next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    private ListNode successor;

    public ListNode reverseN(ListNode head, int n) {
        if (head == null) return null;
        if (n == 1) {
            successor = head.next;
            return successor;
        }
        ListNode last = reverseN(head.next, n - 1);
        head.next.next = head;
        // 注意，链接前后两部分链表
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
        ListNode pre, cur, nxt;
        pre = null;
        cur = nxt = a;
        while (cur != b) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        ListNode a, b;
        a = b = head;
        for (int i = 0; i < k; i++) {
            // 不足k个，无须反转
            if (b == null) return null;
            b = b.next;
        }

        ListNode newHead = reverseBetween(a, b);
        a.next = reverseKGroup(newHead, k);
        return newHead;
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null) return null;
        ListNode ans = new ListNode(0);
        ans.next = head;
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

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;
        // 先连成环
        ListNode tail = head;
        int n = 1;
        while (tail.next != null) {
            tail = tail.next;
            n++;
        }
        tail.next = head;

        // 寻找新的头节点、尾节点
        ListNode newTail = head;
        for (int i = 0; i < n - k % n - 1; i++) {
            newTail = newTail.next;
        }
        // 新的头节点
        ListNode newHead = newTail.next;
        // 切断链表
        newTail.next = null;
        return newHead;
    }

    public ListNode mergeTwoList(ListNode a, ListNode b) {
        ListNode res = new ListNode(0);
        ListNode cur = res;
        while (a != null && b != null) {
            if (a.val > b.val) {
                res.next = b;
                b = b.next;
            } else {
                res.next = a;
                a = a.next;
            }
            cur = cur.next;
        }

        cur.next = a == null ? b : a;
        return res.next;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) return lists[l];
        if (l > r) return null;
        int mid = (l + r) / 2;
        return mergeTwoList(merge(lists, 0, mid), merge(lists, mid + 1, r));
    }
}

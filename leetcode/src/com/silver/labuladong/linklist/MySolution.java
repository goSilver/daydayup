package com.silver.labuladong.linklist;

import com.silver.labuladong.temp.ListNode;

/**
 * @author csh
 * @date 2021/3/28
 */
public class MySolution {

    /**
     * 完全反转链表（递归）
     *
     * @param head
     * @return
     */
    public ListNode reverseAll(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode last = reverseAll(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    /**
     * 完全反转链表（迭代）
     *
     * @param head
     * @return
     */
    public ListNode reverseAll_2(ListNode head) {
        ListNode pre, cur, nxt;
        pre = null;
        cur = nxt = head;
        while (cur != null) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }



    private ListNode successor;

    /**
     * 反转前n个节点（利用前驱节点）
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            successor = head.next;
            return head;
        }
        ListNode last = reverseN(head, n - 1);
        head.next.next = head;
        head.next = successor;
        return last;
    }

    /**
     * 反转[m,n)之间的节点
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == 1) {
            reverseN(head, n);
        }
        reverseBetween(head, m - 1, n - 1);
        return head;
    }

    /**
     * 反转[a,b)之间的节点
     *
     * @param a
     * @param b
     * @return
     */
    public ListNode reverseBetween(ListNode a, ListNode b) {
        ListNode pre, cur, nxt;
        pre = null;
        cur = nxt = null;
        while (cur != b) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

    /**
     * k个一组反转
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        ListNode a, b;
        a = b = head;
        for (int i = 0; i < k; i++) {
            if (b == null) return head;
            b = b.next;
        }
        ListNode newHead = reverseBetween(a, b);
        a.next = reverseKGroup(b,k);
        return newHead;
    }
}

package com.silver.review.linkedlist;

import com.silver.labuladong.temp.ListNode;

/**
 * @author csh
 * @date 2021/5/23
 **/
public class LinkedList0523 {

    /**
     * 递归反转链表
     *
     * @param head
     * @return
     */
    public ListNode reverse(ListNode head) {
        if (head == null) return null;
        ListNode last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    /**
     * 迭代反转链表
     *
     * @param head
     * @return
     */
    public ListNode reverseByInteration(ListNode head) {
        if (head == null) return null;
        ListNode pre = null, cur = head, next = head;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    private ListNode successor;

    /**
     * 反转前n个节点
     * 思路：
     * 递归反转，每次递归n-1
     * 利用前驱节点successor记录衔接位置，当n=1时，head.next就是前驱节点
     * 反转完了，连接两个链表
     *
     * @param head
     * @param n
     * @return
     */
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

    /**
     * 区间反转
     * 思路：
     * 递归，m和n同时减一，减到m=1时，就相当于要反转从当前节点开始的前n个节点
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
        reverseBetween(head.next, m - 1, n - 1);
        return head;
    }

    /**
     * 区间反转
     *
     * @param a
     * @param b
     * @return
     */
    public ListNode reverseBetween(ListNode a, ListNode b) {
        if (a == null) return null;
        ListNode pre = null;
        ListNode cur = a, nxt = a;
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
        ListNode a = head, b = head;
        for (int i = 0; i < k; i++) {
            if (b == null) return null;
            b = b.next;
        }
        ListNode newHead = reverseBetween(a, b);
        a.next = reverseKGroup(b, k);
        return newHead;
    }

    // 两两交换
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

    /**
     * 旋转链表
     * 思路：
     * 先利用迭代，获取节点个数，并把链表首尾连起来
     * 利用取模来计算移动步数
     * 得到新的头尾节点，然后断开连接
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;
        int n = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            n++;
        }
        tail.next = head;

        for (int i = 0; i < n - k % n - 1; i++) {
            head = head.next;
        }
        ListNode newHead = head.next;
        ListNode newTail = head;
        newTail.next = null;
        return newHead;
    }

    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        ListNode res = new ListNode(0);
        while (a != null && b !=null) {
            if (a.val > b.val) {
                res.next = a;
                a=a.next;
            } else {
                res.next = b;
                b = b.next;
            }
            res = res.next;
        }
        res.next = a != null ? a:b;
        return res.next;
    }

    // 合并k个链表
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

package com.silver.labuladong.linklist;

import com.silver.labuladong.temp.ListNode;

/**
 * @author Chen ShaoHua
 * @date 2021/1/22
 */
public class Solution {

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        ListNode cur = a;
        while (cur != null) {
            System.out.println("before:" + cur.val);
            cur = cur.next;
        }
//        cur = reverseAll(a);
//        cur = reverseN(a, 2);
        cur = reverseBetween(a, 2,4);
        while (cur != null) {
            System.out.println("after:" + cur.val);
            cur = cur.next;
        }
    }

    /**
     * 从头到尾反转链表所有节点（递归）
     *
     * @param head 最初的头节点
     * @return 反转后的头节点
     */
    private static ListNode reverseAll(ListNode head) {
        if (head.next == null) return head;
        ListNode last = reverseAll(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    // 后驱节点
    private static ListNode successor;

    /**
     * 反转链表的前n个节点
     *
     * @param head 最初的头节点
     * @param n    要反转的节点的个数
     * @return 反转后的头节点
     */
    private static ListNode reverseN(ListNode head, int n) {
        // base case
        if (n == 1) {
            // 记录后驱节点
            successor = head.next;
            return head;
        }
        // 递归反转前n-1个节点
        ListNode last = reverseN(head.next, n - 1);
        // 反转
        head.next.next = head;
        // 链接新旧两个链表
        head.next = successor;
        return last;
    }

    /**
     * 反转链表的一部分
     *
     * @param head 原链表头节点
     * @param m    需要反转的部分的起始节点下标
     * @param n    需要反转的部分的结束节点下标
     * @return 反转后链表的头节点
     */
    private static ListNode reverseBetween(ListNode head, int m, int n) {
        // base case
        if (m == 1) {
            return reverseN(head, n);
        }
        // 前进到反转的起点触发 base case
        head.next = reverseBetween(head.next, m - 1, n - 1);
        return head;
    }
}

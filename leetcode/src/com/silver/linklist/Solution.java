package com.silver.linklist;

import com.silver.medium.q002.ListNode;

/**
 * @author Chen ShaoHua
 * @date 2021/1/22
 */
public class Solution {

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        a.next = b;
        b.next = c;
        ListNode cur = a;
        while (cur != null) {
            System.out.println("before:" + cur.val);
            cur = cur.next;
        }
        cur = reverseAll(a);
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

    private static ListNode reverseK(ListNode head) {

        return null;
    }

}

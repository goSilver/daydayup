package com.silver.review.linkedlist;

import com.silver.labuladong.temp.ListNode;

import java.util.Stack;

/**
 * 链表相加
 *
 * @author csh
 * @date 2021/3/28
 */
public class AddTwoNumber {

    /**
     * 两个正序链表相加
     *
     * @param n1 链表1
     * @param n2 链表2
     * @return 相加后的链表头节点
     */
    public ListNode addByRightOrder(ListNode n1, ListNode n2) {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        int carry = 0;
        while (n1 != null || n2 != null) {
            int a = n1 == null ? 0 : n1.val;
            int b = n2 == null ? 0 : n2.val;

            int sum = a + b + carry;
            carry = sum / 10;
            sum = sum % 10;

            cur.next = new ListNode(sum);
            cur = cur.next;

            if (n1 != null) n1 = n1.next;
            if (n2 != null) n2 = n2.next;
        }
        if (carry > 0) cur.next = new ListNode(carry);
        return head.next;
    }

    /**
     * 两个逆序链表相加
     *
     * @param n1 链表1
     * @param n2 链表2
     * @return 相加后的链表头节点
     */
    public ListNode addByLeftOrder(ListNode n1, ListNode n2) {
        Stack<Integer> stack1 = new Stack<>();
        while (n1 != null) {
            stack1.push(n1.val);
            n1 = n1.next;
        }
        Stack<Integer> stack2 = new Stack<>();
        while (n2 != null) {
            stack2.push(n2.val);
            n2 = n2.next;
        }

        ListNode cur = null;
        int carry = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
            int a = stack1.isEmpty() ? 0 : stack1.pop();
            int b = stack2.isEmpty() ? 0 : stack2.pop();

            int sum = a + b + carry;
            carry = sum / 10;
            sum = sum % 10;

            // 注意，因为是逆序的两个链表，所以这里操作有所不同
            ListNode newNode = new ListNode(sum);
            newNode.next = cur;
            cur = newNode;
        }
        // if (carry > 0) cur.next = new ListNode(carry);
        return cur;
    }

}
